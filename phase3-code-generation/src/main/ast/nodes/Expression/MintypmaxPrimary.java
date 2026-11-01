package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * primary : '(' mintypmax_expression ')'
 */
public class MintypmaxPrimary extends Primary {

    private MintypmaxExpression mintypmaxExpression;

    public MintypmaxExpression getMintypmaxExpression() {
        return mintypmaxExpression;
    }

    public void setMintypmaxExpression(MintypmaxExpression mintypmaxExpression) {
        this.mintypmaxExpression = mintypmaxExpression;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return List.of();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
