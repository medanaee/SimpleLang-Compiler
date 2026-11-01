package main.ast.nodes;

import main.visitor.IVisitor;
import main.ast.nodes.ComponentItem.ComponentCommonItem;

public class ContinuousSet extends ComponentCommonItem {

    private ListOfNetAssignments listOfNetAssignments;

    public ContinuousSet() {
        super();
    }

    public void setListOfNetAssignments(ListOfNetAssignments listOfNetAssignments) {
        this.listOfNetAssignments = listOfNetAssignments;
    }

    public ListOfNetAssignments getListOfNetAssignments() {
        return listOfNetAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this); // Need to visit
    }
}
