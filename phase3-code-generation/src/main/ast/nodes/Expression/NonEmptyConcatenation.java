package main.ast.nodes.Expression;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

/**
 * '{' expression (',' expression)* '}'
 */
public class NonEmptyConcatenation extends Concatenation {

    private final List<Expression> expressions = new ArrayList<>();

    public NonEmptyConcatenation() {
    }

    public void addExpression(Expression expression) {
        if (expression != null) {
            this.expressions.add(expression);
        }
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        List<Identifier> result = new ArrayList<>();
        for (Expression expression: expressions)
            result.addAll(expression.getIdentifiers());
        return result;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
