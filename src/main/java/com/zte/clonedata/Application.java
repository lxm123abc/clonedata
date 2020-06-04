package com.zte.clonedata;

import com.zte.clonedata.util.FTPUtils;
import com.zte.clonedata.util.JDBCUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = {"com.zte.clonedata"})
@EnableScheduling
@MapperScan("com.zte.clonedata.dao")
@ServletComponentScan
public class Application {

    // http://localhost:8090/druid/  >>> druid监控页面

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
}
