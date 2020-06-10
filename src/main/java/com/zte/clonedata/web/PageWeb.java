package com.zte.clonedata.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zte.clonedata.dao.TaskLogMapper;
import com.zte.clonedata.job.douban.JobDouban;
import com.zte.clonedata.model.TaskLog;
import com.zte.clonedata.model.TaskLogExample;
import com.zte.clonedata.util.ResponseUtils;
import com.zte.clonedata.util.SpringContextUtil;
import com.zte.clonedata.web.dto.TaskDTO;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName: clonedata-com.zte.clonedata.web
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 12:22 2020/6/1
 * @Description:
 */
@RestController
@RequestMapping("/page")
public class PageWeb {

    @Autowired
    private TaskLogMapper taskLogMapper;
    @Autowired
    private JobDouban jobDouban;
    private ThreadPoolTaskExecutor taskExecutor;
    private PoolingHttpClientConnectionManager connManager;
    @Value("${cron.down.douban}")
    private String doubanCron;

    @GetMapping("/execute")
    public ResponseUtils execute(String id, String token) throws Exception {
        if (token.equals("LXM_123!")) {
            if ("douban".equals(id)) {
                jobDouban.execute();
            }
        }
        Thread.sleep(1000);
        return ResponseUtils.success("执行成功");
    }

    @GetMapping("/list")
    public ResponseUtils list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) throws Exception {
        List<TaskDTO> result = Lists.newArrayList();
        List<Map<String, Object>> maps = taskLogMapper.groupbyTypeLog();
        TaskDTO taskDTO = TaskDTO.builder().name("豆瓣").cron(doubanCron).id("douban").type(1).build();
        for (Map<String, Object> map : maps) {
            if (((int) map.get("type")) == 1) {
                taskDTO.setExecutecount(String.valueOf((long)map.get("ing"))
                        .concat("  /  ").concat(String.valueOf((long)map.get("success")))
                        .concat("  /  ").concat(String.valueOf((long)map.get("fail"))));
            }
        }
        result.add(taskDTO);
        return ResponseUtils.success(result.size(), result);
    }
    @GetMapping("/detail")
    public ResponseUtils detail(@RequestParam("type") Integer type
            ,@RequestParam("page") Integer page
            , @RequestParam("limit") Integer limit
    ){
        TaskLogExample taskLogExample = new TaskLogExample();
        TaskLogExample.Criteria criteria = taskLogExample.createCriteria();
        criteria.andTypeEqualTo(type);
        taskLogExample.setOrderByClause("begintime desc");
        PageHelper.startPage(page,limit);
        List<TaskLog> taskLogs = taskLogMapper.selectByExample(taskLogExample);
        PageInfo<TaskLog> pages = new PageInfo<>(taskLogs);
        return ResponseUtils.success((int) pages.getTotal(),taskLogs);
    }
    @GetMapping("/del")
    public ResponseUtils del(@RequestParam("id") String id){
        try {
            taskLogMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            return ResponseUtils.success("删除失败: "+ e.getMessage());
        }
        return ResponseUtils.success("删除成功");
    }

    /**
     * 定时任务池信息
     *
     * @return
     */
    @RequestMapping("/TaskPoolDetail")
    public Map<String, Object> TaskPoolDetail() {
        if (taskExecutor == null) {
            taskExecutor = SpringContextUtil.getBean("taskExecutor");
        }
        ThreadPoolExecutor threadPoolExecutor = taskExecutor.getThreadPoolExecutor();
        Map<String, Object> result = Maps.newLinkedHashMap();
        result.put("正在运行", threadPoolExecutor.getActiveCount());
        result.put("已完成", threadPoolExecutor.getCompletedTaskCount());
        result.put("线程池曾经创建过的最大线程数量", threadPoolExecutor.getLargestPoolSize());
        result.put("最大同时运行", threadPoolExecutor.getMaximumPoolSize());
        result.put("当前线程池的线程数量", threadPoolExecutor.getPoolSize());
        result.put("任务总个数", threadPoolExecutor.getTaskCount());
        result.put("空闲时间(秒)", threadPoolExecutor.getKeepAliveTime(TimeUnit.SECONDS));
        return result;
    }

    @RequestMapping("/httpclientPoolDetail")
    public Map<String, Object> httpclientPoolDetail() {
        if (connManager == null) {
            connManager = SpringContextUtil.getBean("httpClientConnectionManager");
        }
        PoolStats poolStats = connManager.getTotalStats();
        Map<String, Object> map = Maps.newHashMap();
        map.put("最大线程数", String.valueOf(poolStats.getMax()));
        map.put("空闲的线程数", String.valueOf(poolStats.getAvailable()));
        map.put("租用的线程数", String.valueOf(poolStats.getLeased()));
        map.put("即将启动的线程数", String.valueOf(poolStats.getPending()));
        return map;
    }

}
