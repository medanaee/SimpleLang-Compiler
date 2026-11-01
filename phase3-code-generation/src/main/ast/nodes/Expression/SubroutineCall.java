package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * subroutine_call:
 *   identifier arg_list?
 */
public class SubroutineCall extends Node {

    private Identifier identifier;
    private ArgList argList;   // may be null

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
