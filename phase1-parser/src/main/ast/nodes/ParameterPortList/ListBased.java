package main.ast.nodes.ParameterPortList;

import main.ast.nodes.ParameterPortDeclaration.ParameterPortDeclaration;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;



import main.ast.nodes.*;

public class ListBased extends ParameterPortList {

    private ListOfParamAssignments listOfParamAssignments;
    private List<ParameterPortDeclaration> parameterPortDeclarations;

    public ListBased() {

        this.parameterPortDeclarations = new ArrayList<>();
    }

    public void setListOfParamAssignments(ListOfParamAssignments listOfParamAssignments) {
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
