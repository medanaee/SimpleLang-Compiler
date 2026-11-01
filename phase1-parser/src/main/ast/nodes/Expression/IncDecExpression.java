package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class IncDecExpression extends Expression {
    private Expression operand; // lvalue
    private String operator;
    private boolean isPrefix; // true for ++i, false for i++

    public IncDecExpression(Expression operand, String operator, boolean isPrefix) {
        this.operand = operand;
        this.operator = operator;
        this.isPrefix = isPrefix;
    }

    public Expression getOperand() {
        return operand;
    }

    public String getOperator() {
        return operator;
    }

    public boolean isPrefix() {
        return isPrefix;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this); // Need to add this to IVisitor
    }

    @Override
    public String toString() {
        return "IncDecExpression{" +
                "operand=" + operand +
                ", operator='" + operator + '\'' +
                ", isPrefix=" + isPrefix +
                '}';
    }
}
