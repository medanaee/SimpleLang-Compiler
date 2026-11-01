package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * constant_primary : 'null'
 */
public class ConstantNullPrimary extends ConstantPrimary {

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
