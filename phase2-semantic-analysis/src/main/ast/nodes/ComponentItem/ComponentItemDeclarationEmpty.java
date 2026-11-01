package main.ast.nodes.ComponentItem;

import main.visitor.IVisitor;

/**
 * AST node for a continuous set statement:
 * set <list_of_net_assignments> ;
 *
 * Follows the same conventions as the other AST nodes in your project.
 */
public class ComponentItemDeclarationEmpty extends ComponentItemDeclaration {

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
