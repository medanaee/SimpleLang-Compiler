package main.ast.nodes.Expression;

import main.ast.nodes.BlockLabel;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * Base class for all concrete statements.
 * `statement` rule should return a subclass of this.
 */
public abstract class StatementItem extends Node {
}
