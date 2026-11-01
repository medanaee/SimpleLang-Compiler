package main.ast.nodes.ComponentItem;

import main.ast.nodes.ComponentItem.ListOfPortConnections.NamedPortConnection;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

import java.util.List;

public class HierarchicalInstance extends Node {

    private NameOfInstance nameOfInstance;
    private List<NamedPortConnection> listOfPortConnections;


    public String getName()
    {
        return nameOfInstance.getIdentifier().getName();
    }

    public int getNumOfPorts(){
        return listOfPortConnections.size();
    }
    public HierarchicalInstance() {
    }
    private int startLine=0;  // line of '('

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    private int endLine=0;
    // -----------------------------
    // NameOfInstance
    // -----------------------------
    public void setNameOfInstance(NameOfInstance nameOfInstance) {
        this.nameOfInstance = nameOfInstance;
    }

    public NameOfInstance getNameOfInstance() {
        return this.nameOfInstance;
    }

    // -----------------------------
    // ListOfPortConnections
    // -----------------------------
    public void setListOfPortConnections(List<NamedPortConnection> listOfPortConnections) {
        this.listOfPortConnections = listOfPortConnections;
    }

    public List<NamedPortConnection> getListOfPortConnections() {
        return this.listOfPortConnections;
    }

    // -----------------------------
    // Visitor
    // -----------------------------
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
