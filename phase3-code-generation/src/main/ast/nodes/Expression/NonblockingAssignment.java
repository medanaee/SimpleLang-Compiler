package main.ast.nodes.Expression;

import main.ast.nodes.Expression.Expression;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * AST node for:
 *   nonblocking_assignment : variable_lvalue '<=' expression
 *
 * This is NOT a statement by itself; `statement_item` wraps it into a
 * statement with a semicolon and sets that statement's line from ';'.
 */
public class NonblockingAssignment extends Node {

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
