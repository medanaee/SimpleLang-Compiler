package main.ast.nodes.ComponentItem;

import main.ast.nodes.Node;
import main.ast.nodes.Identifier;
import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;


public class NetDeclAssignment extends Node {
    private Identifier identifier;
    private Expression expression;


    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
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
