package main.ast.nodes.ParameterPortList;

import main.ast.nodes.ComponentItem.ParamAssignment;
import main.ast.nodes.ParameterPortDeclaration.ParameterPortDeclaration;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;



import main.ast.nodes.*;

public class ListBased extends ParameterPortList {

    private List<ParamAssignment> listOfParamAssignments;

    public List<ParameterPortDeclaration> getParameterPortDeclarations() {
        return parameterPortDeclarations;
    }

    public void setParameterPortDeclarations(List<ParameterPortDeclaration> parameterPortDeclarations) {
        this.parameterPortDeclarations = parameterPortDeclarations;
    }

    public List<ParamAssignment> getListOfParamAssignments() {
        return listOfParamAssignments;
    }

    private List<ParameterPortDeclaration> parameterPortDeclarations;

    public ListBased() {

        this.parameterPortDeclarations = new ArrayList<>();
    }

    public void setListOfParamAssignments(List<ParamAssignment> listOfParamAssignments) {
        this.listOfParamAssignments = listOfParamAssignments;
    }

    public void addParameterPortDeclaration(ParameterPortDeclaration declaration) {
        this.parameterPortDeclarations.add(declaration);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
