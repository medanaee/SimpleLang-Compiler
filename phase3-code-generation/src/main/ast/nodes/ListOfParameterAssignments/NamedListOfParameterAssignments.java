package main.ast.nodes.ListOfParameterAssignments;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.Expression.NamedParameterAssignment;
import main.visitor.IVisitor;

public class NamedListOfParameterAssignments extends ListOfParameterAssignments {

    private List<NamedParameterAssignment> namedParameterAssignments = new ArrayList<>();

    public NamedListOfParameterAssignments() {
    }

    public void addNamedParameterAssignment(NamedParameterAssignment assignment) {
        if (assignment != null) {
            this.namedParameterAssignments.add(assignment);
        }
    }

    public List<NamedParameterAssignment> getNamedParameterAssignments() {
        return namedParameterAssignments;
    }

    public void setNamedParameterAssignments(List<NamedParameterAssignment> namedParameterAssignments) {
        this.namedParameterAssignments = namedParameterAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
