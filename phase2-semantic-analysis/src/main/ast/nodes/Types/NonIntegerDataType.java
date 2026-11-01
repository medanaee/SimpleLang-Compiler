package main.ast.nodes.Types;

import main.visitor.IVisitor;

/**
 * data_type alternative:
 *   integer_type signing? packed_dimension*
 */
public class NonIntegerDataType extends DataType {

    private final NonIntegerType nonintegerType;
    public NonIntegerDataType(NonIntegerType nonIntegerType){
        this.nonintegerType = nonIntegerType;
    }
    public NonIntegerType getNonintegerType() {
        return nonintegerType;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
