package main.ast.nodes.ComponentItem.NetDeclaration;

import main.ast.nodes.ComponentItem.Dimension.PackedDimension;
import main.ast.nodes.Node;
import main.ast.nodes.Types.Signing;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class   ImplicitDataType extends DataTypeOrImplicit {
    private Signing signing;
    private final List<PackedDimension> packedDimensions = new ArrayList<>();

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
        this.packedDimensions.add(packedDimension);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
