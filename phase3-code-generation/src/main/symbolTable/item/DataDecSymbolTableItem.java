package main.symbolTable.item;

import main.ast.nodes.*;
import main.ast.nodes.ComponentItem.NetDeclaration.NetDeclAssignment;

public class DataDecSymbolTableItem extends SymbolTableItem{
    public static final String START_KEY = "Dec_";
    private NetDeclAssignment declaration;


    public NetDeclAssignment getDeclaration() {
        return declaration;
    }

    public void setDeclaration(NetDeclAssignment declaration) {
        this.declaration = declaration;
    }

    public DataDecSymbolTableItem(NetDeclAssignment declaration) {
        this.declaration = declaration;
    }

    @Override
    public String getKey() {
//        return declaration.getName();
        return START_KEY + this.declaration.getName();
    }

}
