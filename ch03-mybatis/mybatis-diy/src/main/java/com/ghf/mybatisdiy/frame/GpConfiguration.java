package com.ghf.mybatisdiy.frame;

import com.sun.deploy.nativesandbox.NativeSandboxBroker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * @Author: ghf
 */
public class GpConfiguration {


    public static ResourceBundle sqlMapping;

    static {
        sqlMapping = ResourceBundle.getBundle("sql");//读取sql.properties
    }

    public <T> T getMapper(Class<T> clazz, GPSqlSessioin gpSqlSessioin) {
        GpMapperPorxy gpMapperPorxy = new GpMapperPorxy(gpSqlSessioin);
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, gpMapperPorxy);
    }
}
