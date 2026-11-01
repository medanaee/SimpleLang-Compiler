package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * primary : primary '\'' '(' expression ')'
 */
public class PrimaryCast extends Primary {

    private Primary primary;
    private Expression expression;

    public Primary getPrimary() {
        return primary;
    }

    public void setPrimary(Primary primary) {
        this.primary = primary;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        List<Identifier> result = new ArrayList<>();
        result.addAll(primary.getIdentifiers());
        result.addAll(expression.getIdentifiers());
        return result;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
