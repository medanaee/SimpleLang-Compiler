package main.ast.nodes.Expression;

import main.ast.nodes.PartSelectRange;
import main.visitor.IVisitor;

/**
 * Represents:
 *   '[' part_select_range ']'
 */
public class RangeOnlySelect extends Select_ {

    private PartSelectRange partSelectRange;

    public PartSelectRange getPartSelectRange() {
        return partSelectRange;
    }

    public void setPartSelectRange(PartSelectRange partSelectRange) {
        this.partSelectRange = partSelectRange;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
