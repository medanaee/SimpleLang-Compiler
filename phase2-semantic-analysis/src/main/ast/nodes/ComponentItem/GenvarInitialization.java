package main.ast.nodes.ComponentItem;

import main.ast.nodes.Node;
import main.ast.nodes.Identifier;
import main.ast.nodes.Expression.ConstantExpression;
import main.visitor.IVisitor;

public class GenvarInitialization extends Node {

    private boolean hasIndexKeyword;
    private Identifier identifier;
    private ConstantExpression constantExpression;

    public GenvarInitialization() {
        super();
    }

    public void setHasIndexKeyword(boolean hasIndexKeyword) {
        this.hasIndexKeyword = hasIndexKeyword;
    }

    public boolean hasIndexKeyword() {
        return hasIndexKeyword;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setConstantExpression(ConstantExpression constantExpression) {
        this.constantExpression = constantExpression;
    }

    public ConstantExpression getConstantExpression() {
        return constantExpression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
