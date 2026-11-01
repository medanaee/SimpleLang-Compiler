package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * constant_primary : constant_primary '\'' '(' constant_expression ')'
 */
public class ConstantPrimaryCast extends ConstantPrimary {

    private ConstantPrimary constantPrimary;
    private ConstantExpression expression;

    public ConstantPrimary getConstantPrimary() {
        return constantPrimary;
    }

    public void setConstantPrimary(ConstantPrimary constantPrimary) {
        this.constantPrimary = constantPrimary;
    }

    public ConstantExpression getExpression() {
        return expression;
    }

    public void setExpression(ConstantExpression expression) {
        this.expression = expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
