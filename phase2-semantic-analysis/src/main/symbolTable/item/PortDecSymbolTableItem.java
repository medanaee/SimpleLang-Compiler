package main.symbolTable.item;

import main.ast.nodes.*;
import main.ast.nodes.ComponentItem.NetDeclaration.NetDeclAssignment;
import main.ast.nodes.PortDecl.DataTypePortDecl;
import main.ast.nodes.PortDecl.PortDecl;

public class PortDecSymbolTableItem extends SymbolTableItem{
    public static final String START_KEY = "Dec_";
    private PortDecl declaration;


    public PortDecl getDeclaration() {
        return declaration;
    }

    public void setDeclaration(PortDecl declaration) {
        this.declaration = declaration;
    }

    public PortDecSymbolTableItem(PortDecl declaration) {
        this.declaration = declaration;
    }

    @Override
    public String getKey() {
//        return declaration.getName();
        return START_KEY + this.declaration.getName();
    }

}

