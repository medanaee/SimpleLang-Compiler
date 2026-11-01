package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class CastExpression extends Expression {
    private Expression targetType;
    private Expression expression;

    public CastExpression(Expression targetType, Expression expression) {
        this.targetType = targetType;
        this.expression = expression;
    }

    public Expression getTargetType() {
        return targetType;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
