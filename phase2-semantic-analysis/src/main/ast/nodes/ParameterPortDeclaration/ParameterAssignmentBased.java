package main.ast.nodes.ParameterPortDeclaration;

import main.ast.nodes.ComponentItem.ParamAssignment;
import main.ast.nodes.Types.DataType;
import main.visitor.IVisitor;

import java.util.List;

public class ParameterAssignmentBased extends ParameterPortDeclaration {

    private DataType dataType;
    private List<ParamAssignment> listOfParamAssignments;

    public ParameterAssignmentBased() {
        super();
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setListOfParamAssignments(List<ParamAssignment> listOfParamAssignments) {
        this.listOfParamAssignments = listOfParamAssignments;
    }

    public List<ParamAssignment> getListOfParamAssignments() {
        return listOfParamAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
