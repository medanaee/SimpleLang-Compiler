package main.ast.nodes.Expression;

import main.ast.nodes.ComponentItem.LoopStatement.ProceduralContinuousSet;
import main.visitor.IVisitor;

/**
 * statement_item:
 *   procedural_continuous_set ';'
 */
public class ProceduralContinuousSetStatement extends StatementItem {

    private ProceduralContinuousSet proceduralContinuousSet;

    public ProceduralContinuousSet getProceduralContinuousSet() {
        return proceduralContinuousSet;
    }

    public void setProceduralContinuousSet(ProceduralContinuousSet proceduralContinuousSet) {
        this.proceduralContinuousSet = proceduralContinuousSet;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
