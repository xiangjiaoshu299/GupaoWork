package com.example.dubboserviceconsumer;

import org.springframework.stereotype.Service;

@Service
public class MyNoticeService implements Notify {

    //@Override
    //public void oninvoke(String name) {
    //    System.out.println("onInvoke: " + name);
    //}
    //
    //@Override
    //public void onreturnWithoutParam(String result) {
    //    System.out.println("onreturnWithoutParam: " + result);
    //}

    @Override
    public void onreturn(String result, int param) {
        System.out.println("onReturn, param: " + param);
    }

    //@Override
    //public void onthrow(Throwable ex, String name) {
    //    System.out.println("onThrow: " + name);
    //}
}
