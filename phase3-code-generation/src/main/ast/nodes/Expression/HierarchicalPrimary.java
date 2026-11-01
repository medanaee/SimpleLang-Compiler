package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * primary : hierarchical_identifier select_?
 */
public class HierarchicalPrimary extends Primary {

    private Identifier hierarchicalIdentifier;
    private Select_ select; // may be null

    public Identifier getHierarchicalIdentifier() {
        return hierarchicalIdentifier;
    }

    public void setHierarchicalIdentifier(Identifier hierarchicalIdentifier) {
        this.hierarchicalIdentifier = hierarchicalIdentifier;
    }

    public Select_ getSelect() {
        return select;
    }

    public void setSelect(Select_ select) {
        this.select = select;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return List.of(hierarchicalIdentifier);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
