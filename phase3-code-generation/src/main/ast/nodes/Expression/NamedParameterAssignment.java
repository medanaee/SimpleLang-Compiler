package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public class NamedParameterAssignment extends Node {

    // .IDENTIFIER
    private Identifier identifier;

    // The argument inside parentheses: can be any subclass of ParamExpression
    // (MintypmaxExpression, DataType, etc.), or null if the parens are empty.
    private ParamExpression paramExpression;

    public NamedParameterAssignment() {
    }

    // -----------------------------
    // Identifier
    // -----------------------------
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    // -----------------------------
    // ParamExpression (optional)
    // -----------------------------
    public void setParamExpression(ParamExpression paramExpression) {
        this.paramExpression = paramExpression;
    }

    public ParamExpression getParamExpression() {
        return this.paramExpression;
    }

    // -----------------------------
    // Visitor
    // -----------------------------
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
