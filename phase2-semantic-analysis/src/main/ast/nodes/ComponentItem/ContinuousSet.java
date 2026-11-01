package main.ast.nodes.ComponentItem;

import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;
import main.ast.nodes.NetAssignment;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

import java.util.List;

/**
 * AST node for a continuous set statement:
 *     set <list_of_net_assignments> ;
 *
 * Follows the same conventions as the other AST nodes in your project.
 */
public class ContinuousSet extends GenerateItem {

    private List<NetAssignment> listOfNetAssignments;
    private int line;

    public ContinuousSet() {
        // no-op
    }

    public void setListOfNetAssignments(List<NetAssignment> listOfNetAssignments) {
        this.listOfNetAssignments = listOfNetAssignments;
    }

    public List<NetAssignment> getListOfNetAssignments() {
        return listOfNetAssignments;
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
