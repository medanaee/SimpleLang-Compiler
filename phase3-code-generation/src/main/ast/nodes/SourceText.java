package main.ast.nodes;

import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.symbolTable.SymbolTable;
import main.visitor.IVisitor;

import java.util.List;
import java.util.ArrayList;

public class SourceText extends Node{

    private SymbolTable symbolTable;

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public List<ComponentDeclaration> getComponentDeclarationList() {
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
