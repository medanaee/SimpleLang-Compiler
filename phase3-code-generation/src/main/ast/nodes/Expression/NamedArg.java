package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * named_arg:
 *   '.' identifier '(' expression? ')'
 */
public class NamedArg extends Node {

    private Identifier identifier;
    private Expression expression; // may be null

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

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
