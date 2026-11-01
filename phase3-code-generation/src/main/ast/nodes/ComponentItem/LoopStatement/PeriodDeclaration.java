package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Expression.Expression;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * period_declaration:
 *   period = expression
 */
public class PeriodDeclaration extends Node {

    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
