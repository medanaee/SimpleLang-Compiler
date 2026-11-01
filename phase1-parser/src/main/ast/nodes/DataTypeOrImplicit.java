package main.ast.nodes;

import main.visitor.IVisitor;

import main.ast.nodes.Expression.Expression;

public class DataTypeOrImplicit extends Expression {
    public DataTypeOrImplicit() {

    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
