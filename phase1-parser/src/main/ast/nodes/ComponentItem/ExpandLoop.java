package main.ast.nodes.ComponentItem;

import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ExpandLoop extends ComponentItem {
    private List<ForLoopStatement> loopStatements = new ArrayList<>();

    public void addForLoopStatement(ForLoopStatement forLoopStatement) {
        this.loopStatements.add(forLoopStatement);
    }

    public List<ForLoopStatement> getLoopStatements() {
        return loopStatements;
    }

    public List<ForLoopStatement> getForLoopStatements() {
        return loopStatements;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
