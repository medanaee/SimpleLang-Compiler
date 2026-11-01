package main.ast.nodes.Headers;

import main.ast.nodes.Identifier;

import main.ast.nodes.Node;
import main.ast.nodes.*;
import main.ast.nodes.PortDecl.PortDecl;
import main.visitor.IVisitor;
import main.ast.nodes.ParameterPortList.*;

import java.util.List;

public class ComponentHeader extends Node {

    private Identifier identifier;
    private ParameterPortList parameterPortList;
    private List<PortDecl> listOfPortDeclarations;

    public String getName()
    {
        return identifier.getName();
    }

    public int getNumOfPorts()
    {
        return listOfPortDeclarations.size();
    }

    public ComponentHeader() {
        super();
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setParameterPortList(ParameterPortList parameterPortList) {
        this.parameterPortList = parameterPortList;
    }

    public void setListOfPortDeclarations(List<PortDecl> listOfPortDeclarations) {
        this.listOfPortDeclarations = listOfPortDeclarations;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ParameterPortList getParameterPortList() {
        return parameterPortList;
    }

    public List<PortDecl> getListOfPortDeclarations() {
        return listOfPortDeclarations;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
