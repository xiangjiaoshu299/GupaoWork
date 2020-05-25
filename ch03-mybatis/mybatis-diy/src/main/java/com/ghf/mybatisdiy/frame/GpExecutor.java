package com.ghf.mybatisdiy.frame;

import com.ghf.mybatisdiy.test.bean.Blog;

import java.sql.*;

/**
 * @Author: ghf
 */
public class GpExecutor {


    public <T> T query(String sql, Object parameter) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            //建立连接
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db1";
            conn = DriverManager.getConnection(url, "root", "123123");

            //创建语句集
            stat = conn.createStatement();

            //查询
            String sqlWithParameter = String.format(sql, parameter);
            rs = stat.executeQuery(sqlWithParameter);

            //解析结果集
            while (rs.next()) {
                Integer id = rs.getInt("bid");
                String name = rs.getString("name");
                String authId = rs.getString("author_id");

                Blog blog = new Blog();
                blog.setBid(id);
                blog.setName(name);
                blog.setAuthorId(authId);
                return (T) blog;
            }

        } catch (Exception e) {


            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
