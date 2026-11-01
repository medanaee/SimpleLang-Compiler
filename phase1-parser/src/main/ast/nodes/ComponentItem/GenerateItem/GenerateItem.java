package main.ast.nodes.ComponentItem.GenerateItem;

import main.ast.nodes.*;
import main.visitor.IVisitor;

public class GenerateItem extends Node {

    public GenerateItem() {
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
