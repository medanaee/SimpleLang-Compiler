package main.ast.nodes.ComponentItem;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

public class IndexDeclaration extends ComponentItemDeclaration {
    private Identifier identifier;

    public IndexDeclaration() {
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
