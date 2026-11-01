package main.ast.nodes.Types;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

public class Number extends PrimaryLiteral {
    public enum NumberKind {
        DECIMAL,
        UNSIGNED_DECIMAL,
        BINARY,
        REAL,
        UNBASED_UNSIZED
    }
    private NumberKind kind;
    private String text;
    private Integer size;
    private String base;

    public NumberKind getKind() {
        return kind;
    }

    public void setKind(NumberKind kind) {
        this.kind = kind;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return List.of();
    }

    public Number(NumberKind kind, String text) {
        this.kind = kind;
        this.text = text;
    }
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
