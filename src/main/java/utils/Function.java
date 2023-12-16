package utils;

import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {

    private final net.objecthunter.exp4j.Expression functionExpression;

    public Function(String expressionString) {
        functionExpression = new ExpressionBuilder(expressionString)
                .variables("x", "y")
                .build();
    }

    public double evaluate(double x, double y) {
        functionExpression.setVariable("x", x);
        functionExpression.setVariable("y", y);
        return functionExpression.evaluate();
    }

}
