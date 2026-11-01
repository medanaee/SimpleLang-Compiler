package main.ast.nodes.Expression;

import main.ast.nodes.Expression.OperatorAssignment;
import main.visitor.IVisitor;

/**
 * statement_item:
 *   operator_assignment ';'
 */
public class OperatorAssignmentStatement extends StatementItem {

    private OperatorAssignment operatorAssignment;

    public OperatorAssignment getOperatorAssignment() {
        return operatorAssignment;
    }

    public void setOperatorAssignment(OperatorAssignment operatorAssignment) {
        this.operatorAssignment = operatorAssignment;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
