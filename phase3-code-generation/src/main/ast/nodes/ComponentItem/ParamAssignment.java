package main.ast.nodes.ComponentItem;


import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * param_assignment:
 *   identifier unpacked_dimension* ('=' constant_param_expression)?
 */
public class ParamAssignment extends Node {

    private Identifier identifier;
    private final List<UnpackedDimension> unpackedDimensions = new ArrayList<>();
    // You can later add a field for the constant value when you model constant_param_expression

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
        if (unpackedDimension != null) {
            this.unpackedDimensions.add(unpackedDimension);
        }
    }

    public String getName()
    {
        return identifier.getName();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
