package main.ast.nodes.ComponentItem;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class UnpackedDimension extends Node {

    public UnpackedDimension() {
    }
    private int startLine=0;

    public UnpackedDimension(int startLine, int endLine) {
        this.startLine = startLine;
        this.endLine = endLine;
    }

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
}
