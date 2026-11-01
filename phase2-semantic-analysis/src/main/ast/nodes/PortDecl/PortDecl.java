package main.ast.nodes.PortDecl;

import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.DataDec;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class PortDecl extends Node {
    List<ComponentDeclaration> componentDeclarationList;

    public PortDecl() {
        this.componentDeclarationList = new ArrayList<>();
    }

    public abstract String getName();
}
