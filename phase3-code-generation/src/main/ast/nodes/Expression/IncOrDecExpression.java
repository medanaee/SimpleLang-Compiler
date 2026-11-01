package main.ast.nodes.Expression;

import main.visitor.IVisitor;

/**
 * Base class for increment/decrement expressions:
 *   ++i, --i, i++, i--
 */
public abstract class IncOrDecExpression extends Expression {
    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
