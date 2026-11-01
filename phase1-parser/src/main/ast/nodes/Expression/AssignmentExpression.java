package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class AssignmentExpression extends Expression {
    private Expression lvalue;
    private Expression rvalue;
    private String operator;

    public AssignmentExpression(Expression lvalue, Expression rvalue, String operator) {
        this.lvalue = lvalue;
        this.rvalue = rvalue;
        this.operator = operator;
    }

    public Expression getLvalue() {
        return lvalue;
    }

    public Expression getRvalue() {
        return rvalue;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this); // Need to add this to IVisitor
    }

    @Override
    public String toString() {
        return "AssignmentExpression{" +
                "lvalue=" + lvalue +
                ", rvalue=" + rvalue +
                ", operator='" + operator + '\'' +
                '}';
    }
}
