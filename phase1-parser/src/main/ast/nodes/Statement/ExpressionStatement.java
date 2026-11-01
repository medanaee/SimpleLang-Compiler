package main.ast.nodes.Statement;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class ExpressionStatement extends Statement {
    private Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
