package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Statement.Statement;
import main.visitor.IVisitor;

public abstract class LoopStatement extends Statement {

    public LoopStatement() {
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
