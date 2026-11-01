package main.ast.nodes;

import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.visitor.IVisitor;

import java.util.List;
import java.util.ArrayList;

public class SourceText extends Node {
    public List<ComponentDeclaration> getComponentDeclarationList() {
        return componentDeclarationList;
    }

    public List<ComponentDeclaration> getComponentDeclarations() {
        return componentDeclarationList;
    }

    List<ComponentDeclaration> componentDeclarationList;

    public SourceText() {
        this.componentDeclarationList = new ArrayList<>();
    }

    public void addComponentDeclaration(ComponentDeclaration componentDeclaration) {
        componentDeclarationList.add(componentDeclaration);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
