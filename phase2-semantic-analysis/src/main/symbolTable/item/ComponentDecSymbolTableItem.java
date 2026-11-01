package main.symbolTable.item;


import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.PortDecl.PortDecl;
import main.TypeCheck.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComponentDecSymbolTableItem extends SymbolTableItem {
    public static final String START_KEY = "CompoDec_";
    private ItemHeaderDeclaration componentDec;
    private final List<String> portNames = new ArrayList<>();
    private final HashMap<String, Type> portTypes = new HashMap<>();

    public ComponentDecSymbolTableItem(ItemHeaderDeclaration componentDec) {
        this.componentDec = componentDec;
        if (componentDec.getComponentHeader() == null || componentDec.getComponentHeader().getListOfPortDeclarations() == null)
            return;
        for (PortDecl portDecl: componentDec.getComponentHeader().getListOfPortDeclarations()){
            portNames.add(portDecl.getName());
        }
    }

    public Type getTypebyPortName(String portName){
        return portTypes.get(portName);
    }

    public void setTypebyPortName(String portName, Type type){
        portTypes.put(portName, type);
    }

    public void addPortType(PortDecSymbolTableItem portDecSymbolTableItem){
        portTypes.put(portDecSymbolTableItem.getDeclaration().getName(), portDecSymbolTableItem.getType());
    }

    public List<String> getPortNames() {
        return portNames;
    }

    @Override
    public String getKey() {
//        return componentDec.getName();
        return START_KEY + this.componentDec.getName();
    }
}
