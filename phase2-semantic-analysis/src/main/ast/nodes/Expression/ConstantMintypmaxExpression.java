package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * constant_mintypmax_expression :
 *   constant_expression
 *   ( ':' constant_expression ':' constant_expression )?
 */
public class ConstantMintypmaxExpression extends ConstantParamExpression {

    private ConstantExpression minExpression;
    private ConstantExpression typExpression; // may be null
    private ConstantExpression maxExpression; // may be null

    public ConstantExpression getMinExpression() {
        return minExpression;
    }

    public void setMinExpression(ConstantExpression minExpression) {
        this.minExpression = minExpression;
    }

    public ConstantExpression getTypExpression() {
        return typExpression;
    }

    public void setTypExpression(ConstantExpression typExpression) {
        this.typExpression = typExpression;
    }

    public ConstantExpression getMaxExpression() {
        return maxExpression;
    }

    public void setMaxExpression(ConstantExpression maxExpression) {
        this.maxExpression = maxExpression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
