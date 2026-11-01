package main.ast.nodes;

import main.visitor.IVisitor;

/**
 * AST node for a genvar declaration, e.g.
 *   index i, j, k ;
 *
 * This class is written following the style of your existing AST nodes
 * (see main.ast.nodes.ParameterPortList.ListBased for reference).
 *
 * Note: This class assumes there is a ListOfGenvarIdentifiers type in
 * main.ast.nodes that represents the parsed list produced by the
 * list_of_genvar_identifiers rule. If your grammar returns a different
 * object for that rule, change the field/type and setter accordingly.
 */
public class GenvarDeclaration extends Node {
    private ListOfGenvarIdentifiers listOfGenvarIdentifiers;
    private int line;

    public GenvarDeclaration() {
        // no-op; list object will be set by the parser action
    }


    public void setListOfGenvarIdentifiers(ListOfGenvarIdentifiers listOfGenvarIdentifiers) {
        this.listOfGenvarIdentifiers = listOfGenvarIdentifiers;
    }

    public ListOfGenvarIdentifiers getListOfGenvarIdentifiers() {
        return listOfGenvarIdentifiers;
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
