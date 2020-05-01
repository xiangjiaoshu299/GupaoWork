//package com.gupaoedu.vip.pattern.interpreter.calculate;
//
//import java.util.Stack;
//
//public class GPCalculator {
//    private Stack<IArithmeticInterpreter> stack = new Stack<IArithmeticInterpreter>();
//
//    public GPCalculator(String expression) {
//        this.parse(expression);
//    }
//
//    public void parse(String expression) {
//        String [] elements = expression.split(" ");
//        IArithmeticInterpreter leftExpr, rightExpr;
//
//        for (int i = 0; i < elements.length ; i++) {
//            String operator = elements[i];
//            if (OperatorUtil.isOperator(operator)){
//                leftExpr = this.stack.pop();
//                rightExpr = new NumInterpreter(Integer.valueOf(elements[++i]));
//                int leftInterpret = leftExpr.interpret();
//                int rightInterpret = rightExpr.interpret();
//                this.stack.push(OperatorUtil.getInterpreter(leftExpr, rightExpr,operator));
//                System.out.println("出栈: " + leftInterpret + " 和 " + rightInterpret + ", 应用运算符: " + operator );
//            }
//            else{
//                NumInterpreter numInterpreter = new NumInterpreter(Integer.valueOf(elements[i]));
//                this.stack.push(numInterpreter);
//                System.out.println("入栈: " + numInterpreter.interpret());
//            }
//        }
//    }
//
//    public int calculate() {
//        return this.stack.pop().interpret();
//    }
//}