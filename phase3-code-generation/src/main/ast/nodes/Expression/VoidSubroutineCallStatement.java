package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * void '(' subroutine_call ')' ';'
 */
public class VoidSubroutineCallStatement extends SubroutineCallStatement {

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
