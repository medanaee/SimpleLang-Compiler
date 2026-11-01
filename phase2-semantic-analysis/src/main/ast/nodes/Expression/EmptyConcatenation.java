package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * '{' '}'
 */
public class EmptyConcatenation extends Concatenation {

    public EmptyConcatenation() {
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return List.of();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
