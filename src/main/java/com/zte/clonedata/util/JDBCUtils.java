package com.zte.clonedata.util;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

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

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    public Connection getCon() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);
        return con;
    }



    public void delete() throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String yyyyMMdd = DateTime.now().minusDays(7).toString("yyyyMMdd");
        //删除豆瓣
        //doubanDAO.delete(con,yyyyMMdd);
        con.close();
    }




    public void check1() throws Exception {
        Connection con = getCon();
        PreparedStatement ps = con.prepareStatement("select count(*) as c from clone_douban");
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            rs.close();
            log.info("-->jdbc测试连接 >>>成功<<<");
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("未找到表: {} 将创建","clone_douban");
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE `clone_douban` (\n" +
                    "  `cover` varchar(255) DEFAULT NULL,\n" +
                    "  `cover_x` varchar(255) DEFAULT NULL,\n" +
                    "  `cover_y` varchar(255) DEFAULT NULL,\n" +
                    "  `filepath` varchar(255) DEFAULT NULL,\n" +
                    "  `id` varchar(255) NOT NULL,\n" +
                    "  `is_new` int(1) DEFAULT NULL,\n" +
                    "  `p_date` varchar(8) DEFAULT NULL,\n" +
                    "  `playable` int(1) DEFAULT NULL,\n" +
                    "  `rate` varchar(255) DEFAULT NULL,\n" +
                    "  `sort` int(10) DEFAULT NULL,\n" +
                    "  `title` varchar(255) DEFAULT NULL,\n" +
                    "  `url` varchar(255) DEFAULT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;");
            statement.close();
        }
        ps.close();
        con.close();
    }





    public void check2() throws Exception {
        Connection con = getCon();
        ResultSet rs = null;

        PreparedStatement ps2 = con.prepareStatement("select count(*) as c from clone_douban_moviedetail");
        try {
            rs = ps2.executeQuery();
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.error("未找到表: {} 将创建","clone_douban_moviedetail");
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE `clone_douban_moviedetail` (\n" +
                    "  `movieid` varchar(32) NOT NULL,\n" +
                    "  `moviename` varchar(255) DEFAULT NULL,\n" +
                    "  `director` varchar(255) DEFAULT '' COMMENT '导演',\n" +
                    "  `scenarist` varchar(255) DEFAULT NULL COMMENT '编剧',\n" +
                    "  `actors` varchar(255) DEFAULT NULL COMMENT '主演',\n" +
                    "  `type` varchar(255) DEFAULT NULL COMMENT '类型',\n" +
                    "  `country` varchar(255) DEFAULT NULL COMMENT '制片国家/地区',\n" +
                    "  `language` varchar(255) DEFAULT NULL COMMENT '语言',\n" +
                    "  `releasedata` varchar(255) DEFAULT NULL COMMENT '上映日期',\n" +
                    "  `runtime` varchar(255) DEFAULT NULL COMMENT '片长',\n" +
                    "  `ratingnum` varchar(255) DEFAULT NULL COMMENT '豆瓣评分',\n" +
                    "  `tags` varchar(255) DEFAULT NULL COMMENT '标签',\n" +
                    "  `moviedesc` varchar(3000) DEFAULT NULL COMMENT '简介',\n" +
                    "  `p_date` varchar(8) DEFAULT NULL COMMENT '日期',\n" +
                    "  `aka` varchar(255) DEFAULT NULL COMMENT '又名',\n" +
                    "  PRIMARY KEY (`movieid`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            statement.close();
        }
        con.close();
    }

}
