package main.ast.nodes;


import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class NetLvalue extends Node {

    public NetLvalue() {
        super();
    }
    public abstract String getName();
}
