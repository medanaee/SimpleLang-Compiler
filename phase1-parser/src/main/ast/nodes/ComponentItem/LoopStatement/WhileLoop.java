package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Statement.Statement;
import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

public class WhileLoop extends LoopStatement {
    private Expression expression;
    private Statement statement;

    public WhileLoop() {
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return this.statement;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
