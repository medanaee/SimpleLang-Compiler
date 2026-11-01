package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * constant_primary : '(' constant_mintypmax_expression ')'
 */
public class ConstantMintypmaxPrimary extends ConstantPrimary {

    // You can use a dedicated ConstantMintypmaxExpression type
    // or reuse MintypmaxExpression if that's how you've defined it.
    private ConstantMintypmaxExpression constantMintypmaxExpression;

    public ConstantMintypmaxExpression getConstantMintypmaxExpression() {
        return constantMintypmaxExpression;
    }

    public void setConstantMintypmaxExpression(ConstantMintypmaxExpression constantMintypmaxExpression) {
        this.constantMintypmaxExpression = constantMintypmaxExpression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
