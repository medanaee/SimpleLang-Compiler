package main.ast.nodes.Expression;

import main.ast.nodes.PartSelectRange;
import main.visitor.IVisitor;

/**
 * Represents:
 *   bit_select ('[' part_select_range ']')?
 */
public class BitSelectWithRange extends Select_ {

    private BitSelect bitSelect;
    private PartSelectRange partSelectRange; // may be null

    public BitSelect getBitSelect() {
        return bitSelect;
    }

    public void setBitSelect(BitSelect bitSelect) {
        this.bitSelect = bitSelect;
    }

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
