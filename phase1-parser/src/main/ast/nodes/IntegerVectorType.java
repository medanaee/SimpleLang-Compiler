package main.ast.nodes;

import main.visitor.IVisitor;

public class IntegerVectorType extends IntegerType {
    private String type;

    public IntegerVectorType() {
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
