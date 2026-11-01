package main.ast.nodes.Statement;

import main.visitor.IVisitor;

public class EmptyStatement extends Statement {
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "EmptyStatement";
    }
}
