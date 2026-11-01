package main.ast.nodes.ComponentItem;

import main.ast.nodes.Identifier;
import main.ast.nodes.Expression.Operators.AssignmentOperator;
import main.visitor.IVisitor;

/**
 * identifier assignment_operator genvar_expression
 */
public class AssignGenvarIteration extends GenvarIteration {

    private Identifier identifier;
    private AssignmentOperator assignmentOperator;
    private GenvarExpression genvarExpression;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public AssignmentOperator getAssignmentOperator() {
        return assignmentOperator;
    }

    public void setAssignmentOperator(AssignmentOperator assignmentOperator) {
        this.assignmentOperator = assignmentOperator;
    }

    public GenvarExpression getGenvarExpression() {
        return genvarExpression;
    }

    public void setGenvarExpression(GenvarExpression genvarExpression) {
        this.genvarExpression = genvarExpression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
