package main.ast.nodes.PortDecl;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.ComponentItem.NetDeclaration.DataTypeOrImplicit;
import main.ast.nodes.ComponentItem.UnpackedDimension;
import main.ast.nodes.Identifier;
import main.ast.nodes.PortDirection.PortDirection;

import main.ast.nodes.Expression.ConstantExpression;

import main.visitor.IVisitor;

/**
 * port_prefix port_direction? net_type data_type_or_implicit? identifier unpacked_dimension* ('=' constant_expression)?
 */
public class NetTypePortDecl extends PortDecl {

    private PortDirection portDirection;          // may be null
    private DataTypeOrImplicit dataTypeOrImplicit; // may be null
    private Identifier identifier;
    private final List<UnpackedDimension> unpackedDimensions = new ArrayList<>();
    private ConstantExpression constantExpression; // may be null

    public PortDirection getPortDirection() {
        return portDirection;
    }

    public void setPortDirection(PortDirection portDirection) {
        this.portDirection = portDirection;
    }

    public DataTypeOrImplicit getDataTypeOrImplicit() {
        return dataTypeOrImplicit;
    }

    public void setDataTypeOrImplicit(DataTypeOrImplicit dataTypeOrImplicit) {
        this.dataTypeOrImplicit = dataTypeOrImplicit;
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
