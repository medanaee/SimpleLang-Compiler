package main.ast.nodes.ParameterPortDeclaration;

import main.ast.nodes.*;
import main.visitor.IVisitor;

public class ParameterAssignmentBased extends ParameterPortDeclaration {

    private DataType dataType;
    private ListOfParamAssignments listOfParamAssignments;

    public ParameterAssignmentBased() {
        super();
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setListOfParamAssignments(ListOfParamAssignments listOfParamAssignments) {
        this.listOfParamAssignments = listOfParamAssignments;
    }

    public ListOfParamAssignments getListOfParamAssignments() {
        return listOfParamAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
