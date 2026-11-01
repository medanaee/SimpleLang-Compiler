package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class MintypmaxExpression extends Expression {
    private Expression min;
    private Expression typ;
    private Expression max;

    public MintypmaxExpression(Expression min, Expression typ, Expression max) {
        this.min = min;
        this.typ = typ;
        this.max = max;
    }

    public Expression getMin() {
        return min;
    }

    public Expression getTyp() {
        return typ;
    }

    public Expression getMax() {
        return max;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
