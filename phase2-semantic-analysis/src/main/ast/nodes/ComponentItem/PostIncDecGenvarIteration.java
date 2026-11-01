package main.ast.nodes.ComponentItem;

import main.ast.nodes.Identifier;
import main.ast.nodes.Expression.Operators.IncOrDecOperator;
import main.visitor.IVisitor;

/**
 * identifier inc_or_dec_operator  (e.g. i++, i--)
 */
public class PostIncDecGenvarIteration extends GenvarIteration {

    private Identifier identifier;
    private IncOrDecOperator operator;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public IncOrDecOperator getOperator() {
        return operator;
    }

    public void setOperator(IncOrDecOperator operator) {
        this.operator = operator;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
