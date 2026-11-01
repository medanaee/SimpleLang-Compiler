package main.ast.nodes.PortDirection;
import main.ast.nodes.*;
import main.visitor.IVisitor;

public abstract class PortDirection extends Node {

    protected String text;

    public PortDirection() {

    }

   public PortDirection(String text, int line) {

      this.text = text;
        this.setLine(line);
    }

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
