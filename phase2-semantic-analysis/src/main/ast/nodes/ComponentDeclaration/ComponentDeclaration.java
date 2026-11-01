package main.ast.nodes.ComponentDeclaration;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class ComponentDeclaration extends ComponentItem {

    public ComponentDeclaration() {
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    private int endLine=0;
    private int startLine=0;
}
