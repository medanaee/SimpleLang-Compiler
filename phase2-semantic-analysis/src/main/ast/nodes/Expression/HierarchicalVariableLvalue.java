package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * hierarchical_identifier select_?
 */
public class HierarchicalVariableLvalue extends VariableLvalue {

    private Identifier hierarchicalIdentifier;
    private Select_ select_;  // may be null

    public Identifier getHierarchicalIdentifier() {
        return hierarchicalIdentifier;
    }

    public void setHierarchicalIdentifier(Identifier hierarchicalIdentifier) {
        this.hierarchicalIdentifier = hierarchicalIdentifier;
    }

    public Select_ getSelect_() {
        return select_;
    }

    public void setSelect_(Select_ select_) {
        this.select_ = select_;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return List.of(hierarchicalIdentifier);
    }

    @Override
    public String getName() {
        return hierarchicalIdentifier.getName();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
