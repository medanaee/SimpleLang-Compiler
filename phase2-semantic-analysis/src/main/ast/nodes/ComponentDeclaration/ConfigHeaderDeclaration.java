package main.ast.nodes.ComponentDeclaration;

import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.Headers.ConfigurationHeader;
import main.ast.nodes.Identifier;
import main.symbolTable.SymbolTable;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;
import main.ast.nodes.ComponentItem.*;
public class ConfigHeaderDeclaration extends ComponentDeclaration {
    private SymbolTable symbolTable;
    private ConfigurationHeader configurationHeader;
    private List<ComponentItem> componentItemList;
    private Identifier componentName;



    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public String getName()
    {
        return configurationHeader.getName();
    }

    public int getNumOfPorts()
    {
        return configurationHeader.getNumOfPorts();
    }

    public ConfigHeaderDeclaration() {
        componentItemList=new ArrayList<>();
    }


    public ConfigurationHeader getConfigurationHeader() {
        return configurationHeader;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public Identifier getComponentName() {
        return componentName;
    }


    public void setConfigurationHeader(ConfigurationHeader configurationHeader) {
        this.configurationHeader = configurationHeader;
    }

    public void addComponentItem(ComponentItem componentItem) {
        componentItemList.add(componentItem);

    }

    public void setComponentName(Identifier componentName) {
        this.componentName = componentName;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
