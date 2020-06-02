package com.zte.clonedata.util;

import com.zte.clonedata.dao.DoubanDAO;
import com.zte.clonedata.model.Douban;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 15:24 2020/5/29
 * @Description:
 */
@Slf4j
@Component
public class JDBCUtils {

    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Autowired
    private DoubanDAO doubanDAO;

    public Connection getCon() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,username,password);
        return con;
    }



    public void delete() throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String yyyyMMdd = DateTime.now().minusDays(7).toString("yyyyMMdd");
        //删除豆瓣
        doubanDAO.delete(con,yyyyMMdd);
        con.close();
    }


}
