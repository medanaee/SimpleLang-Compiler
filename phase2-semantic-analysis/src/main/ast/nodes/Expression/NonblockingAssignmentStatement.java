package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * statement_item:
 *   nonblocking_assignment ';'
 */
public class NonblockingAssignmentStatement extends StatementItem {

    private NonblockingAssignment nonblockingAssignment;

    public NonblockingAssignment getNonblockingAssignment() {
        return nonblockingAssignment;
    }

    public void setNonblockingAssignment(NonblockingAssignment nonblockingAssignment) {
        this.nonblockingAssignment = nonblockingAssignment;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
