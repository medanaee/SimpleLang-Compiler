package main.ast.nodes.ComponentItem;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.List;

/**
 * AST node for a continuous set statement:
 *     set <list_of_net_assignments> ;
 *
 * Follows the same conventions as the other AST nodes in your project.
 */
public class ComponentItemDeclarationEmpty extends ComponentItemDeclaration{

    private List<Identifier> listOfGenvarIdentifiers;
    private int line;

    public ComponentItemDeclarationEmpty() {
        // no-op; list object will be set by the parser action
    }

    /**
     * Set the parsed list-of-identifiers produced by the
     * list_of_genvar_identifiers rule.
     */
    public void setListOfGenvarIdentifiers(List<Identifier> listOfGenvarIdentifiers) {
        this.listOfGenvarIdentifiers = listOfGenvarIdentifiers;
    }

    public List<Identifier> getListOfGenvarIdentifiers() {
        return listOfGenvarIdentifiers;
    }

    /**
     * Set the source line where this declaration started (typically the 'index' token line).
     * The parser action in your grammar calls setLine($i.line).
     */
    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

//    @Override
//    public String toString() {
//        return "GenvarDeclaration{" +
//                "listOfGenvarIdentifiers=" + listOfGenvarIdentifiers +
//                ", line=" + line +
//                '}';
//    }


}
