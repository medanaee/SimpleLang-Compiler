package main.ast.nodes.ComponentItem.LoopStatement;

import main.ast.nodes.Expression.Statement;
import main.visitor.IVisitor;

/**
 * Represents:
 *   procedural_continuous_set : 'set' variable_assignment
 */
public class ProceduralContinuousSet extends Statement {

    private VariableAssignment variableAssignment;

    public VariableAssignment getVariableAssignment() {
        return variableAssignment;
    }

    public void setVariableAssignment(VariableAssignment variableAssignment) {
        this.variableAssignment = variableAssignment;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
