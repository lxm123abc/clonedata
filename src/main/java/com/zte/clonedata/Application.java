package com.zte.clonedata;

import com.zte.clonedata.util.FTPUtils;
import com.zte.clonedata.util.JDBCUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

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
    @PostConstruct
    public void init() throws Exception {
        ftpUtils.connect();
        ftpUtils.disconnect();
        jdbcUtils.check1();
        jdbcUtils.check2();
    }


    @RequestMapping("/admin/taskList")
    public ModelAndView taskList(){
        ModelAndView modelAndView = new ModelAndView("taskList.html");
        return modelAndView;
    }
}
