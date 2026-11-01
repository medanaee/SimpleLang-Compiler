package main.ast.nodes.ComponentDeclaration;


import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.Headers.ComponentHeader;
import main.ast.nodes.Identifier;
import main.symbolTable.SymbolTable;
import main.visitor.IVisitor;
import main.ast.nodes.ComponentItem.*;
import java.util.ArrayList;
import java.util.List;

public class ItemHeaderDeclaration extends ComponentDeclaration {

    private SymbolTable symbolTable;
    private Identifier componentName;
    private ComponentHeader componentHeader;
    private List<ComponentItem> componentItemList = new ArrayList<>();;

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setComponentHeader(ComponentHeader componentHeader) {
        this.componentHeader = componentHeader;
    }
    public void setComponentName(Identifier componentName) {
        this.componentName = componentName;
    }

    public ComponentHeader getComponentHeader() {
        return componentHeader;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public Identifier getComponentName() {
        return componentName;
    }


    public String getName()
    {
        return componentHeader.getName();
    }

    public int getNumOfPorts()
    {
        return componentHeader.getNumOfPorts();
    }



    public void  addComponentItem(ComponentItem componentItem){componentItemList.add(componentItem);}
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
