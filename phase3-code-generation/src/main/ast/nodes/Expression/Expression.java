package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

import java.util.List;

public abstract class Expression extends Node {
    public Expression() {}
    public abstract List<Identifier> getIdentifiers();
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
