package main.ast.nodes.ComponentItem.NetDeclaration;

import main.ast.nodes.Identifier;

import main.visitor.IVisitor;

import java.util.List;

public class IdentifierNetDeclaration extends NetDeclaration {

    private Identifier identifier;
    private List<NetDeclAssignment> listOfNetDeclAssignments;

    public IdentifierNetDeclaration() {
        super();
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setListOfNetDeclAssignments(List<NetDeclAssignment> listOfNetDeclAssignments) {
        this.listOfNetDeclAssignments = listOfNetDeclAssignments;
    }

    public List<NetDeclAssignment> getListOfNetDeclAssignments() {
        return listOfNetDeclAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
