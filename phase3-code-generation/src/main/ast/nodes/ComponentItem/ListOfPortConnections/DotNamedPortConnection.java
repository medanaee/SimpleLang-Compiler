package main.ast.nodes.ComponentItem.ListOfPortConnections;

import main.ast.nodes.ComponentItem.ListOfPortConnections.NamedPortConnection;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

public class DotNamedPortConnection extends NamedPortConnection {

    private Identifier identifier;
    private PortAssign portAssign; // can be null if no "( ... )"

    public DotNamedPortConnection() {
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public PortAssign getPortAssign() {
        return portAssign;
    }

    public void setPortAssign(PortAssign portAssign) {
        this.portAssign = portAssign;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
