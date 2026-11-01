package main.ast.nodes;

import main.ast.nodes.ParameterPortDeclaration.ParameterPortDeclaration;
import main.visitor.IVisitor;

public class ParameterDeclaration extends ParameterPortDeclaration {
    private DataTypeOrImplicit type;
    private ListOfParamAssignments assignments;

    public ParameterDeclaration() {
    }

    public void setType(DataTypeOrImplicit type) {
        this.type = type;
    }

    public DataTypeOrImplicit getType() {
        return type;
    }

    public void setAssignments(ListOfParamAssignments assignments) {
        this.assignments = assignments;
    }

    public ListOfParamAssignments getAssignments() {
        return assignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
