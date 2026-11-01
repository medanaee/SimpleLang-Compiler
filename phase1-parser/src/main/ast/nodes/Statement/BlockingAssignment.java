package main.ast.nodes.Statement;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class BlockingAssignment extends Statement {
    private Expression lValue;
    private Expression rValue;

    public BlockingAssignment(Expression lValue, Expression rValue) {
        this.lValue = lValue;
        this.rValue = rValue;
    }

    public void setLValue(Expression lValue) {
        this.lValue = lValue;
    }

    public Expression getLValue() {
        return lValue;
    }

    public void setRValue(Expression rValue) {
        this.rValue = rValue;
    }

    public Expression getRValue() {
        return rValue;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
