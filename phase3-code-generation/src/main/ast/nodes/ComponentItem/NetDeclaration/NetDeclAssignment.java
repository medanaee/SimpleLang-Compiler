package main.ast.nodes.ComponentItem.NetDeclaration;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.DataDec;
import main.ast.nodes.Identifier;
import main.ast.nodes.Node;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class NetDeclAssignment extends Node {

    private Identifier identifier;
    private List<UnpackedDimension> unpackedDimensions = new ArrayList<>();
    private Expression expression; // optional: right side of '='

    public String getName()
    {
        return identifier.getName();
    }

    public NetDeclAssignment() {
    }

    // -----------------------------
    // Identifier
    // -----------------------------
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    // -----------------------------
    // Unpacked dimensions
    // -----------------------------
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

    // -----------------------------
    // Optional expression (= expr)
    // -----------------------------
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    // -----------------------------
    // Visitor
    // -----------------------------
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
