package main.ast.nodes.ComponentItem;

import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public class GenerateBlockLabel extends Node {

    private Identifier identifier;

    public GenerateBlockLabel() {
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
