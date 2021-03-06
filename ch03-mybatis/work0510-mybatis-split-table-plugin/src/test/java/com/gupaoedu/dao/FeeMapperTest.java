package com.gupaoedu.dao;

import com.gupaoedu.crud.bean.Fee;
import com.gupaoedu.crud.dao.FeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: ghf
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FeeMapperTest {

    @Resource
    private FeeMapper feeMapper;


    @Test
    public void queryWithSplitCondition(){
        //sql中，如果是from t_fee，则分表
        LocalDateTime dateTime = LocalDateTime.of(2020, 2, 15, 0, 0, 0);
        List<Fee> list = feeMapper.selectList(dateTime);
        System.out.println(list.size());


        /**
         * 测试结果：
         * 信息: 执行分表逻辑
         * 信息: 查询的sql: select * from t_fee where fee_date = ?
         * 信息: 分表后的sql: select * from t_fee_202002 where fee_date = ?
         * 1
         */
    }

    @Test
    public void queryWithoutSplitCondition(){
        //sql中，如果是from t_fee_202001，则不分表
        LocalDateTime dateTime = LocalDateTime.of(2020, 1, 15, 0, 0, 0);
        List<Fee> list = feeMapper.selectList202001(dateTime);
        System.out.println(list.size());

        /**
         * 测试结果：
         * 信息: 执行分表逻辑
         * 信息: 查询的sql: select * from t_fee_202001 where fee_date = ?
         * 1
         */
    }

}
