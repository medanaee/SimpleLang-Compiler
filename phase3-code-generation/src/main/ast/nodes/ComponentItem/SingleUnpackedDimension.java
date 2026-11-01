package main.ast.nodes.ComponentItem;

import main.ast.nodes.Expression.ConstantExpression;
import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class SingleUnpackedDimension extends UnpackedDimension {

    private ConstantExpression constantExpression;

    public SingleUnpackedDimension() {
    }

    public SingleUnpackedDimension(int startLine, int endLine) {
        super(startLine, endLine);
    }

    public void setConstantExpression(ConstantExpression constantExpression) {
        this.constantExpression = constantExpression;
    }

    public ConstantExpression getConstantExpression(){
        return this.constantExpression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
