package main.ast.nodes.PortDecl;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.ComponentItem.NetDeclaration.ImplicitDataType;
import main.ast.nodes.ComponentItem.UnpackedDimension;
import main.ast.nodes.Identifier;
import main.ast.nodes.PortDirection.PortDirection;

import main.ast.nodes.Expression.ConstantExpression;

import main.visitor.IVisitor;

/**
 * port_prefix port_direction? implicit_data_type identifier unpacked_dimension* ('=' constant_expression)?
 */
public class ImplicitDataPortDecl extends PortDecl {

    private PortDirection portDirection;         // may be null
    private ImplicitDataType implicitDataType;
    private Identifier identifier;
    private final List<UnpackedDimension> unpackedDimensions = new ArrayList<>();
    private ConstantExpression constantExpression; // may be null

    public PortDirection getPortDirection() {
        return portDirection;
    }

    public void setPortDirection(PortDirection portDirection) {
        this.portDirection = portDirection;
    }

    public ImplicitDataType getImplicitDataType() {
        return implicitDataType;
    }

    public void setImplicitDataType(ImplicitDataType implicitDataType) {
        this.implicitDataType = implicitDataType;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public List<UnpackedDimension> getUnpackedDimensions() {
        return unpackedDimensions;
    }

    public void addUnpackedDimension(UnpackedDimension unpackedDimension) {
        this.unpackedDimensions.add(unpackedDimension);
    }

    public ConstantExpression getConstantExpression() {
        return constantExpression;
    }

    public void setConstantExpression(ConstantExpression constantExpression) {
        this.constantExpression = constantExpression;
    }

    @Override
    public String getName(){
        return identifier.getName();
    }
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
