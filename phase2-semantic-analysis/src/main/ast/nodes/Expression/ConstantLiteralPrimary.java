package main.ast.nodes.Expression;

import main.ast.nodes.Types.PrimaryLiteral;
import main.visitor.IVisitor;

/**
 * constant_primary : primary_literal
 */
public class ConstantLiteralPrimary extends ConstantPrimary {

    private PrimaryLiteral primaryLiteral;

    public PrimaryLiteral getPrimaryLiteral() {
        return primaryLiteral;
    }

    public void setPrimaryLiteral(PrimaryLiteral primaryLiteral) {
        this.primaryLiteral = primaryLiteral;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
