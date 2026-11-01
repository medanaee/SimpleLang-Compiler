package main.ast.nodes.ComponentDeclaration;

import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.ComponentName;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;
import main.ast.nodes.ComponentItem.*;
import java.util.List;

public class ConfigDeclaration extends ComponentDeclaration {

    public ConfigDeclaration() {
    }

    private Identifier identifier;

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public ComponentName getComponentName() {
        return componentName;
    }

    private List<ComponentItem> componentItemList;
    private ComponentName componentName;

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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
