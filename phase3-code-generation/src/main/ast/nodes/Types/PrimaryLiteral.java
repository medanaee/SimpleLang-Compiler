package main.ast.nodes.Types;

import main.ast.nodes.Expression.Primary;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class PrimaryLiteral extends Primary {
    private String text;

    public PrimaryLiteral(String text, int line) {
        this.text = text;
        this.setLine(line);
    }

    public PrimaryLiteral() {
    }

    public String getText() {
        return text;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
