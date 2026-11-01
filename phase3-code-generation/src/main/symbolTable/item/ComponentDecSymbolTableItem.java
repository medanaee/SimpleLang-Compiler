package main.symbolTable.item;


import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.PortDecl.PortDecl;

import java.util.ArrayList;
import java.util.List;

public class ComponentDecSymbolTableItem extends SymbolTableItem {
    public static final String START_KEY = "CompoDec_";
    private ItemHeaderDeclaration componentDec;
    private final List<String> portNames = new ArrayList<>();

    public ComponentDecSymbolTableItem(ItemHeaderDeclaration componentDec) {
        this.componentDec = componentDec;
        if (componentDec.getComponentHeader() == null || componentDec.getComponentHeader().getListOfPortDeclarations() == null)
            return;
        for (PortDecl portDecl: componentDec.getComponentHeader().getListOfPortDeclarations()){
            portNames.add(portDecl.getName());
        }
    }

    public List<String> getPortNames() {
        return portNames;
    }

    @Override
    public String getKey() {
//        return componentDec.getName();
        return START_KEY + this.componentDec.getNumOfPorts() + "_" + this.componentDec.getName();
    }
}
