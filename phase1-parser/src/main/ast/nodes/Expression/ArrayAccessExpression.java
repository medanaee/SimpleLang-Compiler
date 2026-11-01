package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class ArrayAccessExpression extends Expression {
    private Expression array;
    private Expression index;

    public ArrayAccessExpression(Expression array, Expression index) {
        this.array = array;
        this.index = index;
    }

    public Expression getArray() {
        return array;
    }

    public Expression getIndex() {
        return index;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
