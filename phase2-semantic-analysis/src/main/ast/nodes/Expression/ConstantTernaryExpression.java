package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * constant_expression ? constant_expression : constant_expression
 */
public class ConstantTernaryExpression extends ConstantExpression {

    private ConstantExpression condition;
    private ConstantExpression yesExpression;
    private ConstantExpression noExpression;

    public ConstantExpression getCondition() {
        return condition;
    }

    public void setCondition(ConstantExpression condition) {
        this.condition = condition;
    }

    public ConstantExpression getYesExpression() {
        return yesExpression;
    }

    public void setYesExpression(ConstantExpression yesExpression) {
        this.yesExpression = yesExpression;
    }

    public ConstantExpression getNoExpression() {
        return noExpression;
    }

    public void setNoExpression(ConstantExpression noExpression) {
        this.noExpression = noExpression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
