package main.ast.nodes.ComponentItem.NetDeclaration;

import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;
import main.visitor.IVisitor;

/**
 * AST node for a continuous set statement:
 *     set <list_of_net_assignments> ;
 *
 * Follows the same conventions as the other AST nodes in your project.
 */
public abstract class NetDeclaration extends GenerateItem {
    private int line;

    public NetDeclaration() {
        // no-op
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
