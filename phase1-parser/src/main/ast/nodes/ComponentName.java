package main.ast.nodes;

import main.visitor.IVisitor;

public class ComponentName extends  Node{
    public ComponentName() {

    }

    public ComponentName(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    private Identifier identifier;

}
