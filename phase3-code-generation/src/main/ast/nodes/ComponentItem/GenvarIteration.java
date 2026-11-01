package main.ast.nodes.ComponentItem;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class GenvarIteration extends Node {

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
