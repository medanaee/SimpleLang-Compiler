package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class NetAssignment extends Node {
    private Expression lvalue;
    private Expression expression;

    public NetAssignment() {
    }

    public void setLValue(Expression lvalue) {
        this.lvalue = lvalue;
    }

    public Expression getLValue() {
        return lvalue;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
