package main.symbolTable.item;

import main.ast.nodes.*;
import main.ast.nodes.ComponentItem.ParamAssignment;

public class ParameterDecSymbolTable extends SymbolTableItem{
    public static final String START_KEY = "Dec_";
    private ParamAssignment declaration;


    public ParamAssignment getDeclaration() {
        return declaration;
    }

    public void setDeclaration(ParamAssignment declaration) {
        this.declaration = declaration;
    }

    public ParameterDecSymbolTable(ParamAssignment declaration) {
        this.declaration = declaration;
    }

    @Override
    public String getKey() {
//        return declaration.getName();
        return START_KEY + this.declaration.getName();
    }

}
