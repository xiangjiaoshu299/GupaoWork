package com.example.dubboserviceconsumer;

public interface Notify {

    //void oninvoke(int name); // 调用之前

    //void onreturnWithoutParam(String result); // 调用之后

    void onreturn(String result,  int param); // 调用之后

    // onthrow(Throwable ex, int param);  // 出现异常
}
