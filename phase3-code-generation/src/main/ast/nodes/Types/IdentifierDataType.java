package main.ast.nodes.Types;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.ComponentItem.Dimension.PackedDimension;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

/**
 * Used for:
 *   data_type:     identifier packed_dimension+
 *   simple_type:   identifier
 */
public class IdentifierDataType extends DataType {

    private Identifier identifier;
    private List<PackedDimension> packedDimensions = new ArrayList<>();

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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
