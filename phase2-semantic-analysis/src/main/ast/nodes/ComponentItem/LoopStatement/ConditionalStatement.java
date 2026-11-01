package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Expression.Statement;
import main.ast.nodes.Expression.StatementItem;
import main.visitor.IVisitor;

/**
 * Represents:
 *   if (cond_predicate) statement_or_null
 *   [else statement_or_null]
 */
public class ConditionalStatement extends StatementItem {

    private CondPredicate condPredicate;
    private StatementOrNull thenStatement;
    private StatementOrNull elseStatement; // may be null

    public CondPredicate getCondPredicate() {
        return condPredicate;
    }

    public void setCondPredicate(CondPredicate condPredicate) {
        this.condPredicate = condPredicate;
    }

    public StatementOrNull getThenStatement() {
        return thenStatement;
    }

    public void setThenStatement(StatementOrNull thenStatement) {
        this.thenStatement = thenStatement;
    }

    public StatementOrNull getElseStatement() {
        return elseStatement;
    }

    public void setElseStatement(StatementOrNull elseStatement) {
        this.elseStatement = elseStatement;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
