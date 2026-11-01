package main.ast.nodes.PortDirection;
import main.ast.nodes.*;
import main.visitor.IVisitor;

public class InPortDirection extends PortDirection {

    public InPortDirection() {

    }

    public InPortDirection(String text, int line) {
        super(text, line);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
