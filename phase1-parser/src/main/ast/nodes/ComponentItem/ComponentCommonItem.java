package main.ast.nodes.ComponentItem;

import main.visitor.IVisitor;

public class ComponentCommonItem extends ComponentItem {

    public ComponentCommonItem() {
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
