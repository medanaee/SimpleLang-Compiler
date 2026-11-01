package main.ast.nodes.Expression;

import main.ast.nodes.Expression.Operators.UnaryOperator;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * Represents: unary_operator primary
 */
public class UnaryExpression extends Expression {

    private UnaryOperator operator;
    private Primary expression;   // operand

    public UnaryExpression() {
    }

    public UnaryExpression(UnaryOperator operator, Primary expression) {
        this.operator = operator;
        this.expression = expression;
    }

    public UnaryOperator getOperator() {
        return operator;
    }

    public void setOperator(UnaryOperator operator) {
        this.operator = operator;
    }

    public Primary getExpression() {
        return expression;
    }

    public void setPrimary(Primary expression) {
        this.expression = expression;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return expression.getIdentifiers();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
