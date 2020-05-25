package com.ghf.mybatisdiy.frame;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: ghf
 */
public class GpMapperPorxy implements InvocationHandler{

    private GPSqlSessioin sqlSessioin;

    public GpMapperPorxy(GPSqlSessioin gpSqlSessioin) {
        this.sqlSessioin = gpSqlSessioin;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class methodDeclaringClass = method.getDeclaringClass();//方法所属对象的类
        Class methodClass = method.getClass();//Method类对应的方法

        String methodName = method.getName();
        String methodFullName = methodDeclaringClass.getName() + "." + methodName;

        //这里写死，只调用selectById()
        return sqlSessioin.selectOne(methodFullName, args[0]);
    }
}
