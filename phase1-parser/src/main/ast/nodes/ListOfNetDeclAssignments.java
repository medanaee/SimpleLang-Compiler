package main.ast.nodes;

import main.ast.nodes.ComponentItem.NetDeclAssignment;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ListOfNetDeclAssignments extends Node {
    private List<NetDeclAssignment> netDeclAssignments = new ArrayList<>();

    public void addNetDeclAssignment(NetDeclAssignment netDeclAssignment) {
        this.netDeclAssignments.add(netDeclAssignment);
    }

    public List<NetDeclAssignment> getNetDeclAssignments() {
        return netDeclAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
