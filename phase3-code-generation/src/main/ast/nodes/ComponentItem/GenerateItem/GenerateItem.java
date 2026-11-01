package main.ast.nodes.ComponentItem.GenerateItem;

import main.ast.nodes.*;
import main.ast.nodes.ComponentItem.ComponentCommonItem;
import main.ast.nodes.ComponentItem.ComponentItemDeclaration;
import main.ast.nodes.ComponentItem.GenerateBlock;
import main.visitor.IVisitor;

public class GenerateItem extends GenerateBlock {

    public GenerateItem() {
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
