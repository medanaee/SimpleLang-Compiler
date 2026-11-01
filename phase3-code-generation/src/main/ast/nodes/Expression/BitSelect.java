package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * AST node for:
 *   bit_select: ('[' expression ']')+
 */
public class BitSelect extends Node {

    private final List<Expression> expressions = new ArrayList<>();

    public void addExpression(Expression expression) {
        if (expression != null) {
            this.expressions.add(expression);
        }
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
