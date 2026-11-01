package main.ast.nodes.ParameterPortList;

import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

// Adjust package if needed
import main.ast.nodes.ParameterPortDeclaration.ParameterPortDeclaration;

public class DeclarationBased extends ParameterPortList {

    private List<ParameterPortDeclaration> parameterPortDeclarations;

    public DeclarationBased() {

        this.parameterPortDeclarations = new ArrayList<>();
    }

    public void addParameterPortDeclaration(ParameterPortDeclaration declaration) {
        this.parameterPortDeclarations.add(declaration);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
