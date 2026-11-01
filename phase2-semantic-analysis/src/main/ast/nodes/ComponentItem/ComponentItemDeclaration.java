package main.ast.nodes.ComponentItem;

import main.ast.nodes.NetAssignment;
import main.visitor.IVisitor;

import java.util.List;

/**
 * AST node for a continuous set statement:
 *     set <list_of_net_assignments> ;
 *
 * Follows the same conventions as the other AST nodes in your project.
 */
public abstract class ComponentItemDeclaration extends ComponentCommonItem {

    private int line;

    public ComponentItemDeclaration() {
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
    public String toString() {
        return "ContinuousSet{" +
                ", line=" + line +
                '}';
    }
}
