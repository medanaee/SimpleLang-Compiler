package main.ast.nodes;

import main.visitor.IVisitor;

public abstract class IntegerType extends DataType {
    public abstract void setType(String type);

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
