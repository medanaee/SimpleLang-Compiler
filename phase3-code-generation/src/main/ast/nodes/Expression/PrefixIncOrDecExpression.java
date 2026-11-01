package main.ast.nodes.Expression;

import main.ast.nodes.Expression.Operators.IncOrDecOperator;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * Represents:
 *   inc_or_dec_operator variable_lvalue
 *   e.g. ++i, --i
 */
public class PrefixIncOrDecExpression extends IncOrDecExpression {
    private IncOrDecOperator operator;
    private VariableLvalue variableLvalue;

    public IncOrDecOperator getOperator() {
        return operator;
    }

    public void setOperator(IncOrDecOperator operator) {
        this.operator = operator;
    }

    public VariableLvalue getVariableLvalue() {
        return variableLvalue;
    }

    public void setVariableLvalue(VariableLvalue variableLvalue) {
        this.variableLvalue = variableLvalue;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return variableLvalue.getIdentifiers();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
