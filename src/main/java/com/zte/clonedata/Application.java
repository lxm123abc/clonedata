package com.zte.clonedata;

import com.zte.clonedata.dao.DoubanDAO;
import com.zte.clonedata.util.FTPUtils;
import com.zte.clonedata.util.JDBCUtils;
import com.zte.clonedata.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private DoubanDAO doubanDAO;
    @Autowired
    private FTPUtils ftpUtils;
    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        doubanDAO.check();
        ftpUtils.connect();
        ftpUtils.disconnect();
    }
}
