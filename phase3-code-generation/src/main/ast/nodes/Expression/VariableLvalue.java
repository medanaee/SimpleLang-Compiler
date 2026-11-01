package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

import java.util.List;

public abstract class VariableLvalue extends Node {

    public abstract List<Identifier> getIdentifiers();
    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
