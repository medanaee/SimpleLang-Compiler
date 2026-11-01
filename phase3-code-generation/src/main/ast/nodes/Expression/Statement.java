package main.ast.nodes.Expression;

import main.ast.nodes.BlockLabel;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Base class for all concrete statements.
 * `statement` rule should return a subclass of this.
 */
public  class Statement extends Node {

    private BlockLabel blockLabel;   // may be null
    private StatementItem innerStatement;

    public BlockLabel getBlockLabel() {
        return blockLabel;
    }

    public void setBlockLabel(BlockLabel blockLabel) {
        this.blockLabel = blockLabel;
    }

    public StatementItem geStatementItem() {
        return innerStatement;
    }

    public void setStatementItem(StatementItem innerStatement) {
        this.innerStatement = innerStatement;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
