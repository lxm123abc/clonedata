package com.zte.clonedata.dao;

import com.zte.clonedata.model.Douban;
import com.zte.clonedata.util.JDBCUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * ProjectName: clonedata-com.zte.clonedata.dao
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 10:23 2020/6/2
 * @Description:
 */
@Repository
@Slf4j
public class DoubanDAO {

    @Autowired
    private JDBCUtils jdbcUtils;

    public void save(List<Douban> list){
        long start = System.currentTimeMillis();
        log.info("执行数据库新增操作 ===============");
        //数据库连接
        Connection con = null;
        try {
            con = jdbcUtils.getCon();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into clone_douban(did,cover,cover_x,cover_y,filepath,id," +
                            "is_new,p_date,playable,rate,sort,title,url) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            list.forEach(douban->{
                try {
                    preparedStatement.setString(1,douban.getDid());
                    preparedStatement.setString(2,douban.getCover());
                    preparedStatement.setString(3,douban.getCover_x());
                    preparedStatement.setString(4,douban.getCover_y());

                    preparedStatement.setString(5,douban.getFilepath());
                    preparedStatement.setString(6,douban.getId());
                    preparedStatement.setBoolean(7,douban.is_new());
                    preparedStatement.setString(8,douban.getPDate());

                    preparedStatement.setBoolean(9,douban.isPlayable());
                    preparedStatement.setString(10,douban.getRate());
                    preparedStatement.setInt(11,douban.getSort());
                    preparedStatement.setString(12,douban.getTitle());

                    preparedStatement.setString(13,douban.getUrl());

                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            preparedStatement.executeBatch();//执行
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            log.error("新增时发生错误 ===============");
        }finally {
            if (con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("数据库新增操作结束,用时: {} ===============",System.currentTimeMillis()-start);
    };


    public void check() throws SQLException, ClassNotFoundException {
        Connection con = jdbcUtils.getCon();
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
                    "`did`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,\n" +
                    "`cover`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`cover_x`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`cover_y`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`filepath`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`is_new`  int(1) NULL DEFAULT NULL ,\n" +
                    "`p_date`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`playable`  int(1) NULL DEFAULT NULL ,\n" +
                    "`rate`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`sort`  int(10) NULL DEFAULT NULL ,\n" +
                    "`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,\n" +
                    "PRIMARY KEY (`did`)\n" +
                    ")\n" +
                    "ENGINE=InnoDB\n" +
                    "DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci\n" +
                    "ROW_FORMAT=DYNAMIC\n" +
                    ";\n");
            statement.close();
        }
        ps.close();
        con.close();
    }


    public void delete(Connection con,String yyyyMMdd) throws SQLException {
        PreparedStatement ps = con.prepareStatement("delete from clone_douban where p_date < ?");
        ps.setString(1,yyyyMMdd);
        ps.execute();
        ps.close();
    }

}
