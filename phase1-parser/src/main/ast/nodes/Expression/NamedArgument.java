package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

public class NamedArgument extends Expression {
    private Identifier name;
    private Expression expression;

    public NamedArgument(Identifier name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    public Identifier getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
