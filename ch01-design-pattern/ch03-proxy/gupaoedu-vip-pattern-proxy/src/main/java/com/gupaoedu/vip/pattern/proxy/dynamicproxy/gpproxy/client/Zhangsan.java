package com.gupaoedu.vip.pattern.proxy.dynamicproxy.gpproxy.client;

/**
 * Created by Tom.
 */
public class Zhangsan implements IPerson {

    public void findLove() {
        System.out.println("张三要求：肤白貌美大长腿");
    }

    public void buyInsure() {
        System.out.println("30万");
    }

    public void registerBaby(Integer year, String name) {
        System.out.println("登记小孩出生，出生年:" + year + ", 姓名：" + name);
    }

}
