package main.ast.nodes;

import main.visitor.IVisitor;

public class IntegerAtomType extends IntegerType {
    private String type;

    public IntegerAtomType() {
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
