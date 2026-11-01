package main.ast.nodes.ParameterPortList;

import main.visitor.IVisitor;

public class EmptyBased extends ParameterPortList {

    public EmptyBased() {

    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
