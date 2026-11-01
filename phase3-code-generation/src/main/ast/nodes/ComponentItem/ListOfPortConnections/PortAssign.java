package main.ast.nodes.ComponentItem.ListOfPortConnections;



import main.ast.nodes.Expression.Expression;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public class PortAssign extends Node {

    private Expression expression; // may be null

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    private int startLine=0;
    private int endLine=0;
    public PortAssign() {
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
