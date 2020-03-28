package com.gupaoedu.vip.pattern.interpreter.calculate;

/**
 * Created by Tom.
 */
public class Test {

    public static void main(String[] args) {
        //System.out.println("result: " + new GPCalculator("10 + 30").calculate());
//        System.out.println("result: " + new GPCalculator("10 + 30 - 20").calculate());

        System.out.println("result: " + new GPCalculator("10 * ( 30 + 2 ) - 20").calculate());

    }

}
