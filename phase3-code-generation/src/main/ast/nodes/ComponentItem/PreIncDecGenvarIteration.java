package main.ast.nodes.ComponentItem;

import main.ast.nodes.Identifier;
import main.ast.nodes.Expression.Operators.IncOrDecOperator;
import main.visitor.IVisitor;

/**
 * inc_or_dec_operator identifier  (e.g. ++i, --i)
 */
public class PreIncDecGenvarIteration extends GenvarIteration {

    private IncOrDecOperator operator;
    private Identifier identifier;

    public IncOrDecOperator getOperator() {
        return operator;
    }

    public void setOperator(IncOrDecOperator operator) {
        this.operator = operator;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
