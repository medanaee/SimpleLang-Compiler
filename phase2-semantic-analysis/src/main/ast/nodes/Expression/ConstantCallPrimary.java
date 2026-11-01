package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

/**
 * constant_primary : identifier arg_list
 */
public class ConstantCallPrimary extends ConstantPrimary {

    private Identifier identifier;
    private ArgList argList;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public ArgList getArgList() {
        return argList;
    }

    public void setArgList(ArgList argList) {
        this.argList = argList;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
