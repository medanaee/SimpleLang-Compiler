package main.ast.nodes.Expression;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

/**
 * arg_list:
 *   '(' list_of_arguments ')'
 */
public class ArgList extends Node {

    private ListOfArguments listOfArguments;

    public ListOfArguments getListOfArguments() {
        return listOfArguments;
    }

    public void setListOfArguments(ListOfArguments listOfArguments) {
        this.listOfArguments = listOfArguments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
