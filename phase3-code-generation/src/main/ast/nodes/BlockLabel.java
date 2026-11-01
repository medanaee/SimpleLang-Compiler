package main.ast.nodes;

import main.visitor.IVisitor;


public class BlockLabel extends Node {
    private Identifier identifier;
    private int line;

    public BlockLabel() {

    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }


}
