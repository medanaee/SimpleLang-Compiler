package main.ast.nodes.ComponentItem;

import main.ast.nodes.*;
import main.visitor.IVisitor;

public abstract class ComponentItem extends Node {

    public ComponentItem() {

    }

    public ComponentItem(int line) {

    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
