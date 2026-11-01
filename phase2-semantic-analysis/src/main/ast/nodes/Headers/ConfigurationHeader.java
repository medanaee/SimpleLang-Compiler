package main.ast.nodes.Headers;
import main.ast.nodes.Identifier;

import main.ast.nodes.Node;
import main.ast.nodes.ParameterPortList.ParameterPortList;
import main.ast.nodes.PortDecl.PortDecl;
import main.visitor.IVisitor;

import java.util.List;

public class ConfigurationHeader extends Node {


    public String getName()
    {
        return identifier.getName();
    }

    public int getNumOfPorts()
    {
        return listOfPortDeclarations == null ? 0 : listOfPortDeclarations.size();
    }
    private Identifier identifier;
    private ParameterPortList parameterPortList;
    private List<PortDecl> listOfPortDeclarations;
    private int startLine=0;

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    private int endLine=0;

    public ConfigurationHeader() {
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
