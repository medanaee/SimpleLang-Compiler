package main.ast.nodes.ComponentItem.LoopStatement;

import main.visitor.IVisitor;

public class ForeverLoop extends LoopStatement {
    private StatementOrNull statementOrNull;

    public ForeverLoop() {
    }

    public void setStatementOrNull(StatementOrNull statement) {
        this.statementOrNull = statement;
    }

    public StatementOrNull getStatementOrNull() {
        return this.statementOrNull;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
