package main.ast.nodes.ComponentItem;

import main.ast.nodes.ComponentItem.LoopStatement.LoopStatement;
import main.ast.nodes.Expression.Expression;
import main.ast.nodes.Node;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ForLoopStatement extends LoopStatement { 

    private IndexAssignment indexAssignment;
    private Expression condition;
    private Node step;
    private Identifier loopIdentifier;
    private List<ComponentProgramInterfaceInstantiation> instantiations;

    public ForLoopStatement() {
        this.instantiations = new ArrayList<>();
    }

    public void setIndexAssignment(IndexAssignment indexAssignment) {
        this.indexAssignment = indexAssignment;
    }

    public IndexAssignment getIndexAssignment() {
        return indexAssignment;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setStep(Node step) {
        this.step = step;
    }

    public Node getStep() {
        return step;
    }

    public void setLoopIdentifier(Identifier loopIdentifier) {
        this.loopIdentifier = loopIdentifier;
    }

    public Identifier getLoopIdentifier() {
        return loopIdentifier;
    }

    public void addInstantiation(ComponentProgramInterfaceInstantiation instantiation) {
        this.instantiations.add(instantiation);
    }

    public List<ComponentProgramInterfaceInstantiation> getInstantiations() {
        return instantiations;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
