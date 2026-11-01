package main.ast.nodes.Types;

import main.visitor.IVisitor;

/**
 * data_type alternative:
 *   'string'
 */
public class StringDataType extends DataType {

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
