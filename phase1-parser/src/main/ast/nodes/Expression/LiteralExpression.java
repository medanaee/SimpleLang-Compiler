package main.ast.nodes.Expression;

import main.visitor.IVisitor;

public class LiteralExpression extends Expression {
    private String value;
    private String type;  

    public LiteralExpression(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
