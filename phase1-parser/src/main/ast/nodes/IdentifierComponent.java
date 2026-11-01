package main.ast.nodes;


import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.ComponentItem.*;
import main.visitor.IVisitor;

import java.util.List;

public class IdentifierComponent extends ComponentDeclaration {
    public IdentifierComponent() {
    }

    public void setComponentName(ComponentName componentName) {
        this.componentName = componentName;
    }

    public ComponentName getComponentName() {
        return componentName;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    private ComponentName componentName;
    private List<ComponentItem> componentItemList;
    public void  addComponentItem(ComponentItem componentItem){componentItemList.add(componentItem);}

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    private Identifier identifier;
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
