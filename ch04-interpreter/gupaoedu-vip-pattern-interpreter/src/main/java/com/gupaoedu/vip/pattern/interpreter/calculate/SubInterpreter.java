package com.gupaoedu.vip.pattern.interpreter.calculate;

public class SubInterpreter extends Interpreter {
    public SubInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        super(left, right);
    }

    public int interpret() {
        //System.out.println("调用 SubInterpreter.interpret");
        return this.left.interpret() - this.right.interpret();
    }
}