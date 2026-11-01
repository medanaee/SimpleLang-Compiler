package main.ast.nodes;

import main.visitor.IVisitor;
import main.ast.nodes.Expression.Expression;

public class Identifier extends Expression {
    public Identifier(String name, int line) {
        this.name = name;
        this.setLine(line);
    }

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
