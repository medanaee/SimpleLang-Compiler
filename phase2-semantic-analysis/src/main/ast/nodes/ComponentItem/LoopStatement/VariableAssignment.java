package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Expression.Expression;
import main.ast.nodes.Expression.VariableLvalue;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Represents:
 *   variable_assignment : variable_lvalue '=' expression
 */
public class VariableAssignment extends Node {

    private VariableLvalue variableLvalue;
    private Expression expression;

    public VariableLvalue getVariableLvalue() {
        return variableLvalue;
    }

    public void setVariableLvalue(VariableLvalue variableLvalue) {
        this.variableLvalue = variableLvalue;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
