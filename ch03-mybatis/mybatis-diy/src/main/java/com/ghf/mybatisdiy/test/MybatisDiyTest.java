package com.ghf.mybatisdiy.test;

import com.ghf.mybatisdiy.frame.GPSqlSessioin;
import com.ghf.mybatisdiy.frame.GpConfiguration;
import com.ghf.mybatisdiy.frame.GpExecutor;
import com.ghf.mybatisdiy.test.bean.Blog;
import com.ghf.mybatisdiy.test.mapper.BlogMapper;

/**
 * @Author: ghf
 */
public class MybatisDiyTest {

    public static void main(String[] args) {
        GPSqlSessioin sqlSessioin = new GPSqlSessioin(new GpConfiguration(), new GpExecutor());
        BlogMapper blogMapper = sqlSessioin.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectById(1);
        System.out.println(blog);

    }
}
