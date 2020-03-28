//package com.gupaoedu.vip.pattern.interpreter.calculatewithlevel;
//
//import com.gupaoedu.vip.pattern.interpreter.calculate.IArithmeticInterpreter;
//import com.gupaoedu.vip.pattern.interpreter.calculate.Interpreter;
//
//public class RightBracKetInterpreter implements IArithmeticInterpreter {
//
//    IArithmeticInterpreter left;
//    IArithmeticInterpreter right;
//    private Interpreter operatorInterperter;
//
//    public RightBracKetInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right, Interpreter operatorInterperter) {
//        this.left = left;
//        this.right = right;
//        this.operatorInterperter = operatorInterperter;
//    }
//
//    public int interpret() {
//        Interpreter interpreter = OperatorUtil.getInterpreter(left, right, operatorInterperter);
//        return interpreter.interpret();
//    }
//}
