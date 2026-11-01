package main.ast.nodes.Types;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.ComponentItem.Dimension.PackedDimension;
import main.visitor.IVisitor;

/**
 * data_type alternative:
 *   integer_type signing? packed_dimension*
 */
public class IntegerDataType extends DataType {

    private IntegerType integerType;
    private Signing signing; // may be null
    private List<PackedDimension> packedDimensions = new ArrayList<>();

    public IntegerType getIntegerType() {
        return integerType;
    }

    public void setIntegerType(IntegerType integerType) {
        this.integerType = integerType;
    }

    public Signing getSigning() {
        return signing;
    }

    public void setSigning(Signing signing) {
        this.signing = signing;
    }

    public List<PackedDimension> getPackedDimensions() {
        return packedDimensions;
    }

    public void addPackedDimension(PackedDimension packedDimension) {
        if (packedDimension != null) {
            this.packedDimensions.add(packedDimension);
        }
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
