package main.ast.nodes.ComponentDeclaration;


import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.ComponentName;
import main.ast.nodes.Headers.ComponentHeader;
import main.visitor.IVisitor;
import main.ast.nodes.ComponentItem.*;
import java.util.ArrayList;
import java.util.List;

public class ItemHeaderDeclaration extends ComponentDeclaration {
    public ItemHeaderDeclaration() {
        this.componentItemList= new ArrayList<>();
    }

    public void setComponentHeader(ComponentHeader componentHeader) {
        this.componentHeader = componentHeader;
    }
    public void setComponentName(ComponentName componentName) {
        this.componentName = componentName;
    }

    public ComponentHeader getComponentHeader() {
        return componentHeader;
    }

    public List<ComponentItem> getComponentItemList() {
        return componentItemList;
    }

    public ComponentName getComponentName() {
        return componentName;
    }

    private ComponentHeader componentHeader;
    private List<ComponentItem> componentItemList;


    public void  addComponentItem(ComponentItem componentItem){componentItemList.add(componentItem);}
    private ComponentName componentName;
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
