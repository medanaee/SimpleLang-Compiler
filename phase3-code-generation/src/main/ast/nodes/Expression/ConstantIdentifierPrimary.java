package main.ast.nodes.Expression;

import main.ast.nodes.ConstantSelect;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

/**
 * constant_primary : identifier constant_select?
 */
public class ConstantIdentifierPrimary extends ConstantPrimary {

    private Identifier identifier;
    private ConstantSelect constantSelect; // may be null

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public ConstantSelect getConstantSelect() {
        return constantSelect;
    }

    public void setConstantSelect(ConstantSelect constantSelect) {
        this.constantSelect = constantSelect;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
