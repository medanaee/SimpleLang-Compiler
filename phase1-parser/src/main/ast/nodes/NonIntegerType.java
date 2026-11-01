package main.ast.nodes;

import main.visitor.IVisitor;

public class NonIntegerType extends DataType {
    private String type;

    public void setNonIntegerType(String type) {
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
