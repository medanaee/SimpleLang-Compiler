package main.ast.nodes.ParameterPortDeclaration;

import main.ast.nodes.ComponentItem.ParameterDeclaration;
import main.visitor.IVisitor;

public class ParameterDeclarationBased extends ParameterPortDeclaration {

    private ParameterDeclaration parameterDeclaration;

    public ParameterDeclarationBased() {
        super();
    }

    public void setParameterDeclaration(ParameterDeclaration parameterDeclaration) {
        this.parameterDeclaration = parameterDeclaration;
    }

    public ParameterDeclaration getParameterDeclaration() {
        return parameterDeclaration;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
