package main.ast.nodes.ParameterPortDeclaration;

import main.ast.nodes.Node;
import main.ast.nodes.ComponentItem.ComponentItemDeclaration;
import main.visitor.IVisitor;

public class ParameterPortDeclaration extends ComponentItemDeclaration {
    public ParameterPortDeclaration() {
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
