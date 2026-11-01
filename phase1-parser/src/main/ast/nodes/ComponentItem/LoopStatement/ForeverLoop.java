package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Statement.Statement;
import main.visitor.IVisitor;

public class ForeverLoop extends LoopStatement {
    private Statement statement;

    public ForeverLoop() {
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return this.statement;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
