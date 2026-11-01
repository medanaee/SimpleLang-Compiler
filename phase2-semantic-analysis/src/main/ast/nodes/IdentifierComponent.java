package main.ast.nodes;


import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.ComponentItem.*;
import main.visitor.IVisitor;

import java.util.List;

public class IdentifierComponent extends ComponentDeclaration {
    private Identifier componentName;
    private List<ComponentItem> componentItemList;
    private Identifier identifier;

    public IdentifierComponent() {
    }

    public void setComponentName(Identifier componentName) {
        this.componentName = componentName;
    }

    public Identifier getComponentName() {
        return componentName;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void  addComponentItem(ComponentItem componentItem){componentItemList.add(componentItem);}

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
