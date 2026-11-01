package main.ast.nodes.Types;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

public class SimpleType extends DataType {
    private IntegerType integerType;
    private NonIntegerType nonintegerType;
    private Identifier identifier;

    public void setIntegerType(IntegerType integerType) { this.integerType = integerType;}
    public IntegerType getIntegerType() {return integerType;}
    public void setNonintegerType(NonIntegerType nonintegerType) {this.nonintegerType = nonintegerType;}
    public NonIntegerType getNonintegerType() {return nonintegerType;}
    public Identifier getIdentifier() {return identifier;}
    public void setIdentifier(Identifier identifier) {this.identifier = identifier;}

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
