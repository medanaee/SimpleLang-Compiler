package main.ast.nodes.ListOfPortDeclarations;

import main.visitor.IVisitor;
import main.ast.nodes.*;
import java.util.ArrayList;
import java.util.List;

public class ListOfPortDeclarations extends Node {

    private List<PortDecl> portDeclList;

    public ListOfPortDeclarations() {
        super();
        this.portDeclList = new ArrayList<>();
    }

    public void addPortDecl(PortDecl portDecl) {
        this.portDeclList.add(portDecl);
    }

    public List<PortDecl> getPortDeclList() {
        return portDeclList;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
