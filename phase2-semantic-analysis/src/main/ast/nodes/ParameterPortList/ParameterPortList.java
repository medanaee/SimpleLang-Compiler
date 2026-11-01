package main.ast.nodes.ParameterPortList;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class ParameterPortList extends Node {

    public ParameterPortList() {

    }

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
