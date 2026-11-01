package main.symbolTable.item;


import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ComponentItem.ComponentProgramInterfaceInstantiation;
import main.ast.nodes.ComponentItem.HierarchicalInstance;

public class InstanceDecSymbolTableItem extends SymbolTableItem {
    public static final String START_KEY = "Dec_";
    private HierarchicalInstance dec;


    public InstanceDecSymbolTableItem(HierarchicalInstance decc) {
        this.dec = decc;
    }

    @Override
    public String getKey() {
//        return dec.getInsName();
//        return START_KEY + this.dec.getNumOfPorts() + "_" + this.dec.getInsName();
          return START_KEY + dec.getName();
    }
}
