package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.ast.nodes.PortDirection.PortDirection;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class PortDecl extends Node {
    private PortDirection portDirection;
    private String netType;
    private DataTypeOrImplicit type; // Covers both DataType and ImplicitDataType
    private Identifier identifier;
    private List<Expression> unpackedDimensions;
    private Expression initialValue;

    public PortDecl() {
        this.unpackedDimensions = new ArrayList<>();
    }

    public void setPortDirection(PortDirection portDirection) {
        this.portDirection = portDirection;
    }

    public PortDirection getPortDirection() {
        return portDirection;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getNetType() {
        return netType;
    }

    public void setType(DataTypeOrImplicit type) {
        this.type = type;
    }

    public DataTypeOrImplicit getType() {
        return type;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setUnpackedDimensions(List<Expression> unpackedDimensions) {
        this.unpackedDimensions = unpackedDimensions;
    }

    public void addUnpackedDimension(Expression dimension) {
        this.unpackedDimensions.add(dimension);
    }

    public List<Expression> getUnpackedDimensions() {
        return unpackedDimensions;
    }

    public void setInitialValue(Expression initialValue) {
        this.initialValue = initialValue;
    }

    public Expression getInitialValue() {
        return initialValue;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {

        return visitor.visit(this);
    }
}
