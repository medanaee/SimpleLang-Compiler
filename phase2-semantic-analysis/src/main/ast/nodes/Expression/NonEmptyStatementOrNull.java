package main.ast.nodes.Expression;

import main.ast.nodes.ComponentItem.LoopStatement.StatementOrNull;
import main.visitor.IVisitor;

/**
 * statement_or_null: statement
 */
public class NonEmptyStatementOrNull extends StatementOrNull {

    private Statement statement;  // <--- this is what you were asking for

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
