package com.zte.clonedata.job.douban;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zte.clonedata.contanst.Contanst;
import com.zte.clonedata.dao.DoubanMapper;
import com.zte.clonedata.dao.DoubanMovieMapper;
import com.zte.clonedata.model.Douban;
import com.zte.clonedata.model.DoubanMovie;
import com.zte.clonedata.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
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
@Async
public class JobDouban {

    @Autowired
    private DoubanMapper doubanMapper;
    @Autowired
    private DoubanMovieMapper doubanMovieDAO;
    private volatile int c = 0;
    /**
     * 切割集合并发访问因子
     */
    private static final int spList = 25;

    /**
     *
     */
    @Scheduled(cron = "${cron.down.douban}")
    public void execute() throws Exception {
        long start = System.currentTimeMillis();
        String nowYYYYMMDD = DateUtils.getNowYYYYMMDD();
        log.info("{} 豆瓣开始执行任务 =================", nowYYYYMMDD);
        List<DoubanMovie> movies = Lists.newArrayList();
        int i = 0;
        int j = 0;
        PicDownUtils picDownUtils = new PicDownUtils();
        synchronized (this) {
            try {
                while (true) {
                    String url = "https://movie.douban.com/j/search_subjects?type=movie&tag=热门&page_limit="
                            .concat(String.valueOf(i + 1000))
                            .concat("&page_start=")
                            .concat(String.valueOf(i));
                    String result = HttpUtils.getJson(url, Contanst.DOUBAN_HOST1);
                    String data = JSONObject.parseObject(result, Map.class).get(Contanst.JSON_KEY_DOUBAN).toString();
                    i = i + 1000;
                    List<Douban> doubans = JSONUtils.parseArray(data, Douban.class);
                    for (Douban douban : doubans) {
                        douban.setSort(j++);
                        douban.setPDate(nowYYYYMMDD);
                        String imageurl = douban.getCover();
                        String name = imageurl.substring(imageurl.lastIndexOf("/") + 1);
                        String path = Contanst.TYPE_DOUBAN.concat(String.valueOf(i)).concat(File.separator).concat(name);
                        douban.setFilepath(path);
                        picDownUtils.urls.add(douban.getCover());
                        picDownUtils.paths.add(path);
                        doubanMapper.insert(douban);
                    }
                    movies.addAll(
                            doubans.stream().map(douban -> {
                                return new DoubanMovie(douban.getId(), douban.getTitle(),douban.getRate(),nowYYYYMMDD);
                            }).collect(Collectors.toList())
                    );
                    log.info("豆瓣第{}页加载完毕 =============", i);
                    if (doubans.size() < 1000) break;
                }
                log.info("豆瓣执行任务结束,用时:{} =================", System.currentTimeMillis() - start);
                Thread t1 = new Thread(picDownUtils);
                t1.start();
                c = 0;
            } catch (Exception e) {
                log.error(e.getMessage());
                if (c++ < 10) {
                    log.error("三秒后再次尝试连接  >>>{}<<<", c);
                    Thread.sleep(3000);
                    execute();
                } else {
                    c = 0;
                }
            }

            saveMovice(movies);
        }
    }

    private void saveMovice(List<DoubanMovie> movies) {
        int size = movies.size()/spList;
        for (int i = 0; i < size; i++) {
            List<DoubanMovie> m1 = new ArrayList<>(movies.subList((i*spList), (i+1)*spList));
            new JobDoubanMovie(m1,doubanMovieDAO);
        }
        if (movies.size()%spList != 0 ){
            List<DoubanMovie> m1 = new ArrayList<>(movies.subList(size*spList, movies.size()));
            new JobDoubanMovie(m1,doubanMovieDAO);
        }
    }

}