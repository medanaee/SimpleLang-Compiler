package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class PortConnection extends Node {
    private Identifier portName; 
    private Expression expression; 
    private boolean isWildcard;

    public PortConnection() {
        this.isWildcard = false;
    }

    public void setPortName(Identifier portName) {
        this.portName = portName;
    }

    public Identifier getPortName() {
        return portName;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setWildcard(boolean wildcard) {
        isWildcard = wildcard;
    }

    public boolean isWildcard() {
        return isWildcard;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this); // Need to add to Visitor
    }
}
