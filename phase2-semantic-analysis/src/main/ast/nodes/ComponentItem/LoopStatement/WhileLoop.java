package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class WhileLoop extends LoopStatement {
    private Expression expression;
    private StatementOrNull statementOrNull;

    public WhileLoop() {
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public void setStatementOrNull(StatementOrNull statement) {
        this.statementOrNull = statement;
    }

    public StatementOrNull getStatement() {
        return this.statementOrNull;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
