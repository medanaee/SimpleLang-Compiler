package main.symbolTable.item;

import main.ast.nodes.*;
import main.ast.nodes.ComponentItem.ParamAssignment;

public class GenvarDecSymbolTable extends SymbolTableItem{
    public static final String START_KEY = "Dec_";
    private Identifier declaration;


    public Identifier getDeclaration() {
        return declaration;
    }

    public void setDeclaration(Identifier declaration) {
        this.declaration = declaration;
    }

    public GenvarDecSymbolTable(Identifier declaration) {
        this.declaration = declaration;
    }

    @Override
    public String getKey() {
//        return declaration.getName();
        return START_KEY + this.declaration.getName();
    }

}
