package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class Expression extends Node {
    @Override
    public <T> T accept(IVisitor<T> visitor) {

        return null;
    }
}
