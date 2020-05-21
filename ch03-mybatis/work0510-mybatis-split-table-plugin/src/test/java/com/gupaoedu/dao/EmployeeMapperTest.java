package com.gupaoedu.dao;

import com.gupaoedu.crud.bean.Employee;
import com.gupaoedu.crud.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: ghf
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeMapperTest {

    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    public void selectList(){
        Employee employee = employeeMapper.selectByPrimaryKey(1);
        System.out.println(employee);
    }
}
