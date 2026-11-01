package main.ast.nodes;

import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ListOfParamAssignments extends Node {
    private List<ParamAssignment> paramAssignments;

    public ListOfParamAssignments() {
        this.paramAssignments = new ArrayList<>();
    }

    public void addParamAssignment(ParamAssignment paramAssignment) {
        this.paramAssignments.add(paramAssignment);
    }

    public List<ParamAssignment> getParamAssignments() {
        return paramAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
