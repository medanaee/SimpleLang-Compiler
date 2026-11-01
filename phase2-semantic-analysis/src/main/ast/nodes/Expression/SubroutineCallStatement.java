package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Base for:
 *   subroutine_call ';'
 *   | void '(' subroutine_call ')' ';'
 */
public abstract class SubroutineCallStatement extends StatementItem {

    public SubroutineCallStatement() {
    }

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
