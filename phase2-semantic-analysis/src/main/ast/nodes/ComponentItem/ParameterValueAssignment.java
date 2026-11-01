package main.ast.nodes.ComponentItem;

import main.ast.nodes.ListOfParameterAssignments.ListOfParameterAssignments;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public class ParameterValueAssignment extends Node {

    private ListOfParameterAssignments listOfParameterAssignments;

    public ParameterValueAssignment() {
    }

    public void setListOfParameterAssignments(ListOfParameterAssignments listOfParameterAssignments) {
        this.listOfParameterAssignments = listOfParameterAssignments;
    }

    public ListOfParameterAssignments getListOfParameterAssignments() {
        return this.listOfParameterAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
