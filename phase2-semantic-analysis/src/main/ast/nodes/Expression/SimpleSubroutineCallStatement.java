package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * subroutine_call ';'
 */
public class SimpleSubroutineCallStatement extends SubroutineCallStatement {

    private SubroutineCall subroutineCall;

    public SubroutineCall getSubroutineCall() {
        return subroutineCall;
    }

    public void setSubroutineCall(SubroutineCall subroutineCall) {
        this.subroutineCall = subroutineCall;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
