package com.gupaoedu.vip.pattern.interpreter.calculatewithlevel;

import com.gupaoedu.vip.pattern.interpreter.calculate.IArithmeticInterpreter;

import java.util.Stack;

/**
 * Created by Tom.
 */
public class Test {

    public static void main(String[] args) {

        GPCalculator gpCalculator = new GPCalculator();
        Stack<IArithmeticInterpreter> stack = gpCalculator.checkPriorityAndParse("10 * ( 30 + 2 ) - 20");
        int res = gpCalculator.calculate(stack);

        System.out.println("result: " + res);

    }

}
