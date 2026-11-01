package main.ast.nodes.ComponentItem.ListOfPortConnections;



import main.visitor.IVisitor;

public class WildcardNamedPortConnection extends NamedPortConnection {

    public WildcardNamedPortConnection() {
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
