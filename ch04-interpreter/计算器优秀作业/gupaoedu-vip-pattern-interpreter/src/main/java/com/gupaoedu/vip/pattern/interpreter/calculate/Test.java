package com.gupaoedu.vip.pattern.interpreter.calculate;

/**
 * Created by Tom.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("result: " + new GPAdvancedCalculator("10+30/((6-4)*2-2)").calculate());
    }

}
