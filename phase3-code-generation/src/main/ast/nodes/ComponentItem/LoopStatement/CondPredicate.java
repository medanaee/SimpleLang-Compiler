package main.ast.nodes.ComponentItem.LoopStatement;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.Expression.Expression;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Represents:
 *   cond_predicate : expression ('&&&' expression)*
 */
public class CondPredicate extends Node {

    private final List<Expression> expressions = new ArrayList<>();

    public void addExpression(Expression expression) {
        this.expressions.add(expression);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
