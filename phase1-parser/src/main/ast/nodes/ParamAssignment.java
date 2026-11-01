package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ParamAssignment extends Node {
    private Identifier identifier;
    private List<Expression> unpackedDimensions;
    private Expression initialValue;

    public ParamAssignment() {
        this.unpackedDimensions = new ArrayList<>();
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setUnpackedDimensions(List<Expression> unpackedDimensions) {
        this.unpackedDimensions = unpackedDimensions;
    }

    public void addUnpackedDimension(Expression dimension) {
        this.unpackedDimensions.add(dimension);
    }

    public List<Expression> getUnpackedDimensions() {
        return unpackedDimensions;
    }

    public void setInitialValue(Expression initialValue) {
        this.initialValue = initialValue;
    }

    public Expression getInitialValue() {
        return initialValue;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
