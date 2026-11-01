package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.ComponentItem.LoopStatement.LoopStatement;
import main.ast.nodes.ComponentItem.LoopStatement.PeriodDeclaration;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class RepeatLoop extends LoopStatement {
    private Expression expression;
    private PeriodDeclaration periodDeclaration;
    private StatementOrNull statementOrNull;

    public RepeatLoop() {
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public void setPeriodDeclaration(PeriodDeclaration periodDeclaration) {
        this.periodDeclaration = periodDeclaration;
    }

    public PeriodDeclaration getPeriodDeclaration() {
        return this.periodDeclaration;
    }

    public void setStatementOrNull(StatementOrNull statementOrNull) {
        this.statementOrNull = statementOrNull;
    }

    public StatementOrNull getStatementOrNull() {
        return this.statementOrNull;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
