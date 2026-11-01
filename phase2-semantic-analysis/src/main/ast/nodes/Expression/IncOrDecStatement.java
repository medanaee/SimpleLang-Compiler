package main.ast.nodes.Expression;

import main.ast.nodes.Expression.IncOrDecExpression;
import main.visitor.IVisitor;

/**
 * statement_item:
 *   inc_or_dec_expression ';'
 */
public class IncOrDecStatement extends StatementItem {

    private IncOrDecExpression incOrDecExpression;

    public IncOrDecExpression getIncOrDecExpression() {
        return incOrDecExpression;
    }

    public void setIncOrDecExpression(IncOrDecExpression incOrDecExpression) {
        this.incOrDecExpression = incOrDecExpression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
