package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Base class for select_:
 *   '[' part_select_range ']'
 *   | bit_select ('[' part_select_range ']')?
 */
public abstract class Select_ extends Node {

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
