package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class TernaryExpression extends Expression {
    private Expression condition;
    private Expression truePart;
    private Expression falsePart;

    public TernaryExpression(Expression condition, Expression truePart, Expression falsePart) {
        this.condition = condition;
        this.truePart = truePart;
        this.falsePart = falsePart;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getTruePart() {
        return truePart;
    }

    public Expression getFalsePart() {
        return falsePart;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "TernaryExpression{" +
                "condition=" + condition +
                ", truePart=" + truePart +
                ", falsePart=" + falsePart +
                '}';
    }
}
