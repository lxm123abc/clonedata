package com.zte.clonedata;

import com.zte.clonedata.dao.TaskLogMapper;
import com.zte.clonedata.model.TaskLog;
import com.zte.clonedata.model.TaskLogExample;
import com.zte.clonedata.util.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication(scanBasePackages = {"com.zte.clonedata"})
@EnableScheduling
@MapperScan("com.zte.clonedata.dao")
@ServletComponentScan
@Controller
public class Application {

    /**
     * druid监控页面 >>>        http://localhost:8090/druid/
     * maven配置 >>>           mybatis-generator:generate
     * 页面执行定时任务接口 >>>   http://localhost:8090/page/execute?id=&token=LXM_123!
     * 任务Web页面  >>>         http://localhost:8090/admin/taskList
     */


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private FTPUtils ftpUtils;
    @Autowired
    private JDBCUtils jdbcUtils;
    @Autowired
    private TaskLogMapper taskLogMapper;
    @PostConstruct
    public void init() throws Exception {
        ftpUtils.connect();
        ftpUtils.disconnect();
        Connection con = jdbcUtils.getCon();
        jdbcUtils.check1(con);
        jdbcUtils.check2(con);
        jdbcUtils.check3(con);
        con.close();
        updateTaskLogStatusIs0();//修改任务表状态为0的为失败
    }

    private void updateTaskLogStatusIs0() {
        TaskLogExample example = new TaskLogExample();
        TaskLogExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(0);
        TaskLog taskLog = new TaskLog();
        taskLog.setExecuteResult("失败,可能原因: 系统关闭时关闭了正在进行的任务");
        taskLog.setStatus(2);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        taskLog.setEndtime(sdf2.format(new Date()));
        taskLogMapper.updateByExampleSelective(taskLog,example);
    }


    @RequestMapping("/admin/taskList")
    public ModelAndView taskList(){
        ModelAndView modelAndView = new ModelAndView("taskList.html");
        return modelAndView;
    }
    @RequestMapping("/admin/detail")
    public ModelAndView detail(@RequestParam("type") Integer type){
        ModelAndView modelAndView = new ModelAndView("detail.html");
        modelAndView.addObject("type",type);
        return modelAndView;
    }
}
