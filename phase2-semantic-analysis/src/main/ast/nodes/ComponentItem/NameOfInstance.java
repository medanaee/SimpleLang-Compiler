package main.ast.nodes.ComponentItem;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.Identifier;
import main.ast.nodes.Node;
import main.ast.nodes.ComponentItem.*;
import main.visitor.IVisitor;

public class NameOfInstance extends Node {

    private Identifier identifier;
    private List<UnpackedDimension> unpackedDimensions = new ArrayList<>();

    public NameOfInstance() {
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public void addUnpackedDimension(UnpackedDimension dim) {
        if (dim != null) {
            this.unpackedDimensions.add(dim);
        }
    }

    public List<UnpackedDimension> getUnpackedDimensions() {
        return this.unpackedDimensions;
    }

    public void setUnpackedDimensions(List<UnpackedDimension> unpackedDimensions) {
        this.unpackedDimensions = unpackedDimensions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
