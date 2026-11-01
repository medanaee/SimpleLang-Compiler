package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * Wrapper expression for:
 *   ( operator_assignment )
 *
 * The actual assignment logic lives in the OperatorAssignment node.
 */
public class AssignmentExpression extends Expression {

    private OperatorAssignment operatorAssignment;

    public OperatorAssignment getOperatorAssignment() {
        return operatorAssignment;
    }

    public void setOperatorAssignment(OperatorAssignment operatorAssignment) {
        this.operatorAssignment = operatorAssignment;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return operatorAssignment.getIdentifiers();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
