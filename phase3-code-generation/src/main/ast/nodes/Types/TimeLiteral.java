package main.ast.nodes.Types;

import main.ast.nodes.*;
import main.visitor.IVisitor;

import java.util.List;

public class TimeLiteral extends PrimaryLiteral {


    public TimeLiteral(String text, int line) {
        super(text,line);
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return List.of();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
