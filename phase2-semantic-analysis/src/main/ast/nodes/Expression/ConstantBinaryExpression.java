package main.ast.nodes.Expression;

import main.ast.nodes.Expression.Operators.BinaryOperator;
import main.visitor.IVisitor;

/**
 * constant_expression op constant_expression
 * where op is any binary operator in constant_expression rule.
 */
public class ConstantBinaryExpression extends ConstantExpression {

    private ConstantExpression left;
    private ConstantExpression right;
    private BinaryOperator operator;

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

    public BinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(BinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
