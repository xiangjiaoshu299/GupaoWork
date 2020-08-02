package org.example;

/**
 * @Author: ghf
 */
public class ExeCpu {

    int a = 0;
    int b = 0;


    public void executeCpu0(){
        a = 1;
        b = 1;
    }

    public void executeCpu1(){
        while(b == 1){
            assert (a == 1);
        }
    }
}
