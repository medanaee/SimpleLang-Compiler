package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Base for:
 *   '{' expression (',' expression)* '}'
 *   | '{' '}'
 */
public abstract class Concatenation extends Primary {

    public Concatenation() {
    }

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
