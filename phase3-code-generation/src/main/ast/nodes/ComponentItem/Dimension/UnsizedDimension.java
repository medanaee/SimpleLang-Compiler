package main.ast.nodes.ComponentItem.Dimension;


import main.visitor.IVisitor;

public class UnsizedDimension extends PackedDimension {

    public UnsizedDimension() {
        super();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
