package main.ast.nodes.ComponentItem;



import main.ast.nodes.Expression.ConstantExpression;
import main.ast.nodes.PartSelectRange;
import main.visitor.IVisitor;

public class ConstantRange extends PartSelectRange {

    private ConstantExpression left;   // msb / first
    private ConstantExpression right;  // lsb / second

    public ConstantExpression getLeft() {
        return left;
    }

    public void setLeft(ConstantExpression left) {
        this.left = left;
    }

    public ConstantExpression getRight() {
        return right;
    }

    public void setRight(ConstantExpression right) {
        this.right = right;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
