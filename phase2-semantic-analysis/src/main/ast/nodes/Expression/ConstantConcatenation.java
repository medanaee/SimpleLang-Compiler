package main.ast.nodes.Expression;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * constant_concatenation:
 *   '{' constant_expression (',' constant_expression)* '}'
 */
public class ConstantConcatenation extends ConstantPrimary {

    private final List<ConstantExpression> constantExpressions = new ArrayList<>();

    public ConstantConcatenation() {
    }

    public void addConstantExpression(ConstantExpression expr) {
        if (expr != null) {
            this.constantExpressions.add(expr);
        }
    }

    public List<ConstantExpression> getConstantExpressions() {
        return constantExpressions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
