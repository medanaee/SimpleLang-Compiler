package main.ast.nodes.PortDecl;

import main.ast.nodes.Types.DataType;
import main.ast.nodes.Identifier;
import main.ast.nodes.PortDirection.PortDirection;

import main.ast.nodes.Expression.ConstantExpression;

import main.visitor.IVisitor;

/**
 * port_prefix port_direction? data_type? identifier ('=' constant_expression)?
 */
public class DataTypePortDecl extends PortDecl {

    private PortDirection portDirection;   // may be null
    private DataType dataType;             // may be null
    private Identifier identifier;
    private ConstantExpression constantExpression; // may be null

    public PortDirection getPortDirection() {
        return portDirection;
    }

    public void setPortDirection(PortDirection portDirection) {
        this.portDirection = portDirection;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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
