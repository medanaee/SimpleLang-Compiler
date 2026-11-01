package main.ast.nodes.ComponentItem;


import main.ast.nodes.ComponentItem.NetDeclaration.DataTypeOrImplicit;
import main.ast.nodes.ParameterPortDeclaration.ParameterPortDeclaration;
import main.visitor.IVisitor;

import java.util.List;

/**
 * AST node for:
 *
 *   parameter data_type_or_implicit? list_of_param_assignments
 *
 * Used both as a component item declaration and as a parameter port declaration.
 */
public class ParameterDeclaration extends ParameterPortDeclaration {

    private DataTypeOrImplicit dataTypeOrImplicit;      // may be null
    private List<ParamAssignment> listOfParamAssignments;

    public DataTypeOrImplicit getDataTypeOrImplicit() {
        return dataTypeOrImplicit;
    }

    public void setDataTypeOrImplicit(DataTypeOrImplicit dataTypeOrImplicit) {
        this.dataTypeOrImplicit = dataTypeOrImplicit;
    }

    public List<ParamAssignment> getListOfParamAssignments() {
        return listOfParamAssignments;
    }

    public void setListOfParamAssignments(List<ParamAssignment> listOfParamAssignments) {
        this.listOfParamAssignments = listOfParamAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
