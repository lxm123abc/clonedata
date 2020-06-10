package com.zte.clonedata.job.douban;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zte.clonedata.contanst.Contanst;
import com.zte.clonedata.dao.DoubanMapper;
import com.zte.clonedata.dao.DoubanMovieMapper;
import com.zte.clonedata.dao.TaskLogMapper;
import com.zte.clonedata.model.Douban;
import com.zte.clonedata.model.DoubanMovie;
import com.zte.clonedata.model.TaskLog;
import com.zte.clonedata.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * ProjectName: clonedata-com.zte.clonedata.job
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 16:46 2020/5/28
 * @Description:
 */
@Slf4j
@Component
@Async("taskExecutor")
public class JobDouban {

    @Autowired
    private DoubanMapper doubanMapper;
    @Autowired
    private DoubanMovieMapper doubanMovieDAO;
    @Autowired
    private TaskLogMapper taskLogMapper;
    private volatile int c = 0;
    /**
     * 切割集合并发访问因子
     */
    private static final int spList = 25;

    /**
     *
     */
    @Scheduled(cron = "${cron.down.douban}")
    public void execute() throws InterruptedException {
        long start = System.currentTimeMillis();
        String taskid = UUIDUtils.get();
        insertTaskLog(taskid);

        ExecutorService exe = Executors.newCachedThreadPool();
        String nowYYYYMMDD = DateUtils.getNowYYYYMMDDHHMMSS();
        log.info("{} 豆瓣开始执行任务 =================", nowYYYYMMDD);
        List<DoubanMovie> movies = Lists.newArrayList();
        int i = 0;
        int j = 0;
        PicDownUtils picDownUtils = new PicDownUtils();
        synchronized (this) {
            while (true) {
                List<Douban> doubans = getDoubansAndSave(i, j, picDownUtils, nowYYYYMMDD);
                movies.addAll(
                        doubans.stream().map(douban -> {
                            return new DoubanMovie(douban.getId(), douban.getTitle(), douban.getRate(), nowYYYYMMDD);
                        }).collect(Collectors.toList())
                );
                c = 0;
                if (doubans.size() < 1000) break;
                i = i + 1000;
                j = j + 1000;
            }
            log.info("豆瓣 {} 条数据加载完毕 =============", movies.size());
            Thread t1 = new Thread(picDownUtils);
            exe.execute(t1);
            saveMovice(movies, exe);
            exe.shutdown();
            while (true) {
                if (exe.isTerminated()) {
                    break;
                }
                Thread.sleep(500);
            }
        }

        long time = System.currentTimeMillis() - start;
        updateTaskLog(taskid,time,movies.size() == 0?false:true);
        log.info("豆瓣执行任务结束,用时:{} =================", time);
    }

    private void updateTaskLog(String taskid, long time, boolean b) {
        TaskLog taskLog = new TaskLog();
        taskLog.setId(taskid);
        if (b){
            taskLog.setExecuteResult("成功");
            taskLog.setStatus(1);
        }else {
            taskLog.setExecuteResult("失败");
            taskLog.setStatus(2);
        }
        taskLog.setEndtime(DateUtils.getNowYYYYMMDDHHMMSS());
        taskLog.setTime(time);
        taskLogMapper.updateByPrimaryKeySelective(taskLog);
    }


    private List<Douban> getDoubansAndSave(int i, int j, PicDownUtils picDownUtils, String nowYYYYMMDD) throws InterruptedException {
        try {
            String url = "https://movie.douban.com/j/search_subjects?type=movie&tag=热门&page_limit="
                    .concat(String.valueOf(i + 1000))
                    .concat("&page_start=")
                    .concat(String.valueOf(i));
            String result = HttpUtils.getJson(url, Contanst.DOUBAN_HOST1);
            String data = JSONObject.parseObject(result, Map.class).get(Contanst.JSON_KEY_DOUBAN).toString();
            List<Douban> doubans = JSONUtils.parseArray(data, Douban.class);
            for (Douban douban : doubans) {
                douban.setSort(j++);
                douban.setPDate(nowYYYYMMDD);
                String imageurl = douban.getCover();
                String name = imageurl.substring(imageurl.lastIndexOf("/") + 1);
                String path = Contanst.BASEURL.concat(Contanst.TYPE_DOUBAN).concat(File.separator).concat(name);
                douban.setFilepath(path);
                doubanMapper.insert(douban);
                /**
                 * 1. 存在   不处理
                 * 2. 不存在 加入任务
                 */
                File file = new File(path);
                if (file.exists()) {
                    continue;
                } else {
                    picDownUtils.urls.add(douban.getCover());
                    picDownUtils.files.add(file);
                }
            }
            return doubans;
        } catch (Exception e) {
            log.error(e.getMessage());
            if (c++ < 10) {
                log.error("三秒后再次尝试连接  >>>{}<<<", c);
                Thread.sleep(3000);
                return getDoubansAndSave(i, j, picDownUtils, nowYYYYMMDD);
            } else {
                return Collections.EMPTY_LIST;
            }
        }
    }

    private void insertTaskLog(String id) {
        TaskLog taskLog = new TaskLog();
        taskLog.setId(id);
        taskLog.setStatus(0);
        taskLog.setType(1);
        taskLog.setBegintime(DateUtils.getNowYYYYMMDDHHMMSS());
        taskLogMapper.insertSelective(taskLog);
    }

    private void saveMovice(List<DoubanMovie> movies, ExecutorService exe) {
        int size = movies.size() / spList;
        int b = movies.size() % spList;
        log.info("计划创建: {}条任务  >>>", b == 0 ? size : (size + 1));
        log.info("开始执行计划任务项  >>>");
        for (int i = 0; i < size; i++) {
            List<DoubanMovie> m1 = new ArrayList<>(movies.subList((i * spList), (i + 1) * spList));
            exe.execute(new JobDoubanMovie(m1, doubanMovieDAO, (i + 1)));
        }
        if (b != 0) {
            List<DoubanMovie> m1 = new ArrayList<>(movies.subList(size * spList, movies.size()));
            exe.execute(new JobDoubanMovie(m1, doubanMovieDAO, size));
        }
    }

}