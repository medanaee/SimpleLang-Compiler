package main.ast.nodes.ComponentItem.Dimension;

import main.ast.nodes.ComponentItem.ConstantRange;
import main.visitor.IVisitor;

public class RangePackedDimension extends PackedDimension {

    private ConstantRange constantRange;

    public RangePackedDimension() {
        super();
    }

    public void setConstantRange(ConstantRange constantRange) {
        this.constantRange = constantRange;
    }

    public ConstantRange getConstantRange() {
        return constantRange;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}