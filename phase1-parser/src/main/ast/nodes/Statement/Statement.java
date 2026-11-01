package main.ast.nodes.Statement;

import main.ast.nodes.ComponentItem.ComponentItem;
import main.ast.nodes.BlockLabel;
import main.visitor.IVisitor;

public abstract class Statement extends ComponentItem {
    private BlockLabel blockLabel;

    public void setBlockLabel(BlockLabel blockLabel) {
        this.blockLabel = blockLabel;
    }

    public BlockLabel getBlockLabel() {
        return blockLabel;
    }

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
