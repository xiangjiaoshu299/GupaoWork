package com.gupaoedu.vip.pattern.interpreter.calculate;

public class AddInterpreter extends Interpreter {

    public AddInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        super(left, right);
    }

    public int interpret() {
        //System.out.println("调用AddInterpreter.interpret");
        return this.left.interpret() + this.right.interpret();
    }
}