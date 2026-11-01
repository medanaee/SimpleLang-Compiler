package main.ast.nodes;

import main.ast.nodes.Types.SimpleType;
import main.visitor.IVisitor;

public class Identifier extends SimpleType {
    private String name;
    public Identifier(String name,int line) {
        this.name=name;
        this.setLine(line);
    }
    public String getName() {
        return name;
    }
    public void setName(String new_name) {
        this.name = new_name;
    }


    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
