package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class UnsizedDimension extends Expression {
    public UnsizedDimension() {
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
