package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the ternary expression:
 *   condition ? yesExpr : noExpr
 */
public class IfExpressionExpression extends Expression {

    private Expression condition;
    private Expression thenExpression;
    private Expression elseExpression;

    public IfExpressionExpression() {
    }

    public IfExpressionExpression(Expression condition,
                                  Expression thenExpression,
                                  Expression elseExpression) {
        this.condition = condition;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Expression getThenExpression() {
        return thenExpression;
    }

    public void setThenExpression(Expression thenExpression) {
        this.thenExpression = thenExpression;
    }

    public Expression getElseExpression() {
        return elseExpression;
    }

    public void setElseExpression(Expression elseExpression) {
        this.elseExpression = elseExpression;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        List<Identifier> result = new ArrayList<>();
        result.addAll(thenExpression.getIdentifiers());
        result.addAll(elseExpression.getIdentifiers());
        return result;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
