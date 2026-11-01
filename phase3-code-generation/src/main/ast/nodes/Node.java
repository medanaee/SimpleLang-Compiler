package main.ast.nodes;

import main.visitor.IVisitor;

import java.util.ArrayList;

public abstract class Node {
    private int line;

    public void setLine(int line){this.line = line;}
    public int getLine(){return this.line;}
//
//    public int getStartLine() {
//        return startLine;
//    }
//
//    public void setStartLine(int startLine) {
//        this.startLine = startLine;
//    }
//
//    public int getEndLine() {
//        return endLine;
//    }
//
//    public void setEndLine(int endLine) {
//        this.endLine = endLine;
//    }

//    private int startLine=0;
//    private int endLine=0;
    public abstract <T> T accept(IVisitor<T> visitor);

}
