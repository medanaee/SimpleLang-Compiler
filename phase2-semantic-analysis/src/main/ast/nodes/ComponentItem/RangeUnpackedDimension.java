package main.ast.nodes.ComponentItem;

import main.ast.nodes.ComponentItem.UnpackedDimension;
import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class RangeUnpackedDimension extends UnpackedDimension {
    public RangeUnpackedDimension(int startLine, int endLine) {
        super(startLine, endLine);
    }

    private ConstantRange constantRange;

    public RangeUnpackedDimension() {
    }

    public void setConstantRange(ConstantRange constantRange) {
        this.constantRange = constantRange;
    }

    public ConstantRange getConstantRange() {
        return this.constantRange;
    }


    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
