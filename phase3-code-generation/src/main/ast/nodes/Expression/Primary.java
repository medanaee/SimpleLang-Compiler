package main.ast.nodes.Expression;

import main.ast.nodes.Expression.Operators.UnaryOperator;
import main.visitor.IVisitor;

/**
 * Represents: unary_operator primary
 */
public abstract class Primary extends Expression {

    private UnaryOperator operator;
    private Primary expression;   // operand

    public Primary() {
    }

    public Primary(UnaryOperator operator, Primary expression) {
        this.operator = operator;
        this.expression = expression;
    }


    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
