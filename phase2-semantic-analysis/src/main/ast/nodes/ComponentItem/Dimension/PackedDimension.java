package main.ast.nodes.ComponentItem.Dimension;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class PackedDimension extends Node {

    public PackedDimension() {
        super();
    }
    private int endLine=0;

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    private int startLine=0;
}