package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class BinaryExpression extends Expression {
    private Expression left;
    private Expression right;
    private String operator;

    public BinaryExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "BinaryExpression{" +
                "left=" + left +
                ", right=" + right +
                ", operator='" + operator + '\'' +
                '}';
    }
}
