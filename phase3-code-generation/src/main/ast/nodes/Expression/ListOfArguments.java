package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Base class for:
 *   ordered_arg (',' ordered_arg)* (',' named_arg)*
 *   | named_arg ( ',' named_arg)*
 */
public abstract class ListOfArguments extends Node {

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
