package main.ast.nodes;

import main.visitor.IVisitor;

public class PortPrefix extends Node {

    private String text;

    public PortPrefix(String text, int line) {

        this.text = text;
        this.setLine(line);
    }



    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
