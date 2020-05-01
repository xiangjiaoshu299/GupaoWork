package com.gupaoedu.vip.pattern.interpreter.calculatewithlevel;

import com.gupaoedu.vip.pattern.interpreter.calculate.IArithmeticInterpreter;
import com.gupaoedu.vip.pattern.interpreter.calculate.NumInterpreter;
import com.gupaoedu.vip.pattern.interpreter.calculate.OperatorUtil;

import java.util.Stack;

public class GPCalculator {


    public GPCalculator() {
    }

    public Stack<IArithmeticInterpreter> checkPriorityAndParse(String expression) {
        expression = checkPriority(expression);
        Stack<IArithmeticInterpreter> stack = parse(expression);
        return stack;
    }

    public Stack<IArithmeticInterpreter> parse(String expression) {
        Stack<IArithmeticInterpreter> stack = new Stack<IArithmeticInterpreter>();

        String[] elements = expression.split(" ");
        IArithmeticInterpreter leftExpr, rightExpr;

        for (int i = 0; i < elements.length; i++) {
            String operator = elements[i];

            if (com.gupaoedu.vip.pattern.interpreter.calculate.OperatorUtil.isOperator(operator)) {
                leftExpr = stack.pop();
                rightExpr = new NumInterpreter(Integer.valueOf(elements[++i]));
                int leftInterpret = leftExpr.interpret();
                int rightInterpret = rightExpr.interpret();
                stack.push(OperatorUtil.getInterpreter(leftExpr, rightExpr, operator));
                System.out.println("出栈: " + leftInterpret + " 和 " + rightInterpret + ", 应用运算符: " + operator);
            } else {
                NumInterpreter numInterpreter = new NumInterpreter(Integer.valueOf(elements[i]));
                stack.push(numInterpreter);
                System.out.println("入栈: " + numInterpreter.interpret());
            }
        }

        return stack;
    }

    private String checkPriority(String expression) {
        int beginIndex = expression.indexOf("(");
        int endIndex = expression.indexOf(")");

        String totalHighPriorityExpression = expression.substring(beginIndex, endIndex + 1);
        String highPriorityExpression = expression.substring(beginIndex + 1, endIndex);
        System.out.println("优先级高的字符串：" + highPriorityExpression);

        Stack<IArithmeticInterpreter> resStack = parse(highPriorityExpression.trim());
        int res = calculate(resStack);
        System.out.println("优先级高的字符串的结果：" + res);

        String replace = expression.replace(totalHighPriorityExpression, res + "");
        System.out.println("字符串" + expression + ", 替换成：" + replace);

        return replace;
    }

    public int calculate(Stack<IArithmeticInterpreter> stack) {
        return stack.pop().interpret();
    }
}