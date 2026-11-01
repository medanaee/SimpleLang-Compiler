package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class HierarchicalInstance extends Node {
    private Identifier instanceName;
    private List<Expression> unpackedDimensions;
    private List<PortConnection> portConnections;

    public HierarchicalInstance() {
        this.unpackedDimensions = new ArrayList<>();
        this.portConnections = new ArrayList<>();
    }

    public void setInstanceName(Identifier instanceName) {
        this.instanceName = instanceName;
    }

    public Identifier getInstanceName() {
        return instanceName;
    }

    public void addUnpackedDimension(Expression dimension) {
        this.unpackedDimensions.add(dimension);
    }

    public void setUnpackedDimensions(List<Expression> unpackedDimensions) {
        this.unpackedDimensions = unpackedDimensions;
    }

    public List<Expression> getUnpackedDimensions() {
        return unpackedDimensions;
    }

    public void addPortConnection(PortConnection connection) {
        this.portConnections.add(connection);
    }

    public void setPortConnections(List<PortConnection> portConnections) {
        this.portConnections = portConnections;
    }

    public List<PortConnection> getPortConnections() {
        return portConnections;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this); // Need to add to Visitor
    }
}
