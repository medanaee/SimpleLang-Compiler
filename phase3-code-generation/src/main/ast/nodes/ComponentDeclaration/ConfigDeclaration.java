package main.ast.nodes.ComponentDeclaration;

import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;
import main.ast.nodes.ComponentItem.*;
import java.util.List;

public class ConfigDeclaration extends ComponentDeclaration {
    private Identifier identifier;
    private List<ComponentItem> componentItemList;
    private Identifier componentName;

    public ConfigDeclaration() {
    }


    public Identifier getIdentifier() {
        return identifier;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public Identifier getComponentName() {
        return componentName;
    }


    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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
