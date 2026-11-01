package main.ast.nodes.Expression;

import main.ast.nodes.ComponentItem.LoopStatement.StatementOrNull;
import main.visitor.IVisitor;

/**
 * statement_or_null: ';'
 */
public class EmptyStatementOrNull extends StatementOrNull {

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
