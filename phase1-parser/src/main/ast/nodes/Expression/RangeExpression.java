package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class RangeExpression extends Expression {
    private Expression left;
    private Expression right;

    public RangeExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
