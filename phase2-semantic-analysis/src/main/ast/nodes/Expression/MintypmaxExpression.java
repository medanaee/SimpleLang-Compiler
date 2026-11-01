package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class MintypmaxExpression extends ParamExpression {

    // min:typ:max
    private Expression minExpression;
    private Expression typExpression; // may be null if only one expression was written
    private Expression maxExpression; // may be null if only one expression was written

    public MintypmaxExpression() {
    }

    // -----------------------------
    // Min
    // -----------------------------
    public void setMinExpression(Expression minExpression) {
        this.minExpression = minExpression;
    }

    public Expression getMinExpression() {
        return this.minExpression;
    }

    // -----------------------------
    // Typ
    // -----------------------------
    public void setTypExpression(Expression typExpression) {
        this.typExpression = typExpression;
    }

    public Expression getTypExpression() {
        return this.typExpression;
    }

    // -----------------------------
    // Max
    // -----------------------------
    public void setMaxExpression(Expression maxExpression) {
        this.maxExpression = maxExpression;
    }

    public Expression getMaxExpression() {
        return this.maxExpression;
    }

    // -----------------------------
    // Visitor
    // -----------------------------
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
