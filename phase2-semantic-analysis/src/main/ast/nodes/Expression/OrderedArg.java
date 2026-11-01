package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * ordered_arg:
 *   expression?
 */
public class OrderedArg extends Node {

    private Expression expression; // may be null

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
