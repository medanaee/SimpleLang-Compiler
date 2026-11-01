package main.ast.nodes.Statement;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class ConditionalStatement extends Statement {
    private Expression condition;
    private Statement thenBody;
    private Statement elseBody;

    public ConditionalStatement(Expression condition, Statement thenBody, Statement elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setThenBody(Statement thenBody) {
        this.thenBody = thenBody;
    }

    public Statement getThenBody() {
        return thenBody;
    }

    public void setElseBody(Statement elseBody) {
        this.elseBody = elseBody;
    }

    public Statement getElseBody() {
        return elseBody;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
