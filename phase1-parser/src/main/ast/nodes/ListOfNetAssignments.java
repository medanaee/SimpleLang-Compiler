package main.ast.nodes;

import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ListOfNetAssignments extends Node {
    private List<NetAssignment> netAssignments;

    public ListOfNetAssignments() {
        this.netAssignments = new ArrayList<>();
    }

    public void addNetAssignment(NetAssignment netAssignment) {
        this.netAssignments.add(netAssignment);
    }

    public List<NetAssignment> getNetAssignments() {
        return netAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
