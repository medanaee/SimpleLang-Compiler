package main.ast.nodes;

import main.visitor.IVisitor;

public class IdentifierNetLvalue extends NetLvalue {

    private Identifier identifier;
    private ConstantSelect constantSelect; // may be null

    public IdentifierNetLvalue() {
        super();
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public String getName() {
        return identifier.getName();
    }

    public void setConstantSelect(ConstantSelect constantSelect) {
        this.constantSelect = constantSelect;
    }

    public ConstantSelect getConstantSelect() {
        return constantSelect;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
