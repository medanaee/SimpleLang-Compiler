package main.symbolTable.item;


import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.PortDecl.PortDecl;
import main.TypeCheck.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigDecSymbolTableItem extends SymbolTableItem {
    public static final String START_KEY = "CompoDec_";
    private final ConfigHeaderDeclaration configHeaderDeclaration;
    private final List<String> portNames = new ArrayList<>();
    private final HashMap<String, Type> portTypes = new HashMap<>();


    public ConfigDecSymbolTableItem(ConfigHeaderDeclaration configHeaderDeclaration) {
        this.configHeaderDeclaration = configHeaderDeclaration;
        if (configHeaderDeclaration.getConfigurationHeader() == null || configHeaderDeclaration.getConfigurationHeader().getListOfPortDeclarations() == null)
            return;
        for (PortDecl portDecl: configHeaderDeclaration.getConfigurationHeader().getListOfPortDeclarations()){
            portNames.add(portDecl.getName());
        }
    }

    public void addPortType(PortDecSymbolTableItem portDecSymbolTableItem){
        portTypes.put(portDecSymbolTableItem.getDeclaration().getName(), portDecSymbolTableItem.getType());
    }

    public List<String> getPortNames() {
        return portNames;
    }

    @Override
    public String getKey() {
//            return configHeaderDeclaration.getName();
        return START_KEY + this.configHeaderDeclaration.getNumOfPorts() + "_" + this.configHeaderDeclaration.getName();
    }
}
