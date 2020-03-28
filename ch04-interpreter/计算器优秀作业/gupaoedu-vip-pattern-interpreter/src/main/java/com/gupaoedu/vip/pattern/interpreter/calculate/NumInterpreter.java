package com.gupaoedu.vip.pattern.interpreter.calculate;

public class NumInterpreter implements IArithmeticInterpreter {
    private int value;

    public NumInterpreter(int value) {
        this.value = value;
    }


    public int interpret() {
        //System.out.println("调用NumInterpreter.interpret");
        return this.value;
    }
}