package com.gupaoedu.vip.pattern.interpreter.calculatewithlevel;

import com.gupaoedu.vip.pattern.interpreter.calculate.*;

public class OperatorUtil {

    public static boolean isOperator(String symbol) {
        return (symbol.equals("+") || symbol.equals("-") || symbol.equals("*"));
    }

    public static Interpreter getInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right, String symbol) {
        if (symbol.equals("+")) {
            return new AddInterpreter(left, right);
        } else if (symbol.equals("-")) {
            return new SubInterpreter(left, right);
        } else if (symbol.equals("*")) {
            return new MultiInterpreter(left, right);
        } else if (symbol.equals("/")) {
            return new DivInterpreter(left, right);
        }
        return null;
    }

//    public static Interpreter getInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right, Interpreter symbol) {
//        if (symbol instanceof AddInterpreter) {
//            return new AddInterpreter(left, right);
//        } else if (symbol instanceof SubInterpreter) {
//            return new SubInterpreter(left, right);
//        } else if (symbol instanceof MultiInterpreter) {
//            return new MultiInterpreter(left, right);
//        } else if (symbol instanceof DivInterpreter) {
//            return new DivInterpreter(left, right);
//        }
//        return null;
//    }
}