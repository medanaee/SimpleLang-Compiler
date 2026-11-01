package main.ast.nodes.ComponentDeclaration;

import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.ComponentName;
import main.ast.nodes.Headers.ConfigurationHeader;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;
import main.ast.nodes.ComponentItem.*;
public class ConfigHeaderDeclaration extends ComponentDeclaration {

    public ConfigHeaderDeclaration() {
        componentItemList=new ArrayList<>();
    }

    private ConfigurationHeader configurationHeader;

    public ConfigurationHeader getConfigurationHeader() {
        return configurationHeader;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public ComponentName getComponentName() {
        return componentName;
    }

    private List<ComponentItem> componentItemList;
    private ComponentName componentName;

    public void setConfigurationHeader(ConfigurationHeader configurationHeader) {
        this.configurationHeader = configurationHeader;
    }

    public void addComponentItem(ComponentItem componentItem) {
        componentItemList.add(componentItem);

    }

    public void setComponentName(ComponentName componentName) {
        this.componentName = componentName;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
