package main.ast.nodes.Expression;

import main.ast.nodes.Expression.Operators.UnaryOperator;
import main.visitor.IVisitor;

/**
 * unary_operator constant_primary
 */
public class ConstantUnaryExpression extends ConstantExpression {

    private UnaryOperator operator;
    private ConstantExpression operand;

    public UnaryOperator getOperator() {
        return operator;
    }

    public void setOperator(UnaryOperator operator) {
        this.operator = operator;
    }

    public ConstantExpression getOperand() {
        return operand;
    }

    public void setOperand(ConstantExpression operand) {
        this.operand = operand;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
