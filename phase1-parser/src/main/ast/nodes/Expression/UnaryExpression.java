package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class UnaryExpression extends Expression {
    private Expression operand;
    private String operator;

    public UnaryExpression(Expression operand, String operator) {
        this.operand = operand;
        this.operator = operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "UnaryExpression{" +
                "operand=" + operand +
                ", operator='" + operator + '\'' +
                '}';
    }
}
