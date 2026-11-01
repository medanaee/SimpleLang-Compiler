package main.ast.nodes.PortDirection;
import main.visitor.IVisitor;

public class OutPortDirection extends PortDirection {

    public OutPortDirection() {
        super();
    }

    public OutPortDirection(String text, int line) {
   super(text,line);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}