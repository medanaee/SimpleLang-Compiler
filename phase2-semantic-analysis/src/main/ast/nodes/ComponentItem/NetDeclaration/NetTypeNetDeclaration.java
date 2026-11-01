package main.ast.nodes.ComponentItem.NetDeclaration;

import main.ast.nodes.ComponentItem.NetDeclaration.NetDeclaration;
import main.ast.nodes.Node;


import main.visitor.IVisitor;

import java.util.List;

public class NetTypeNetDeclaration extends NetDeclaration {

    private DataTypeOrImplicit dataTypeOrImplicit;
    private List<NetDeclAssignment> listOfNetDeclAssignments;

    public NetTypeNetDeclaration() {
        super();
    }

    public void setDataTypeOrImplicit(DataTypeOrImplicit dataTypeOrImplicit) {
        this.dataTypeOrImplicit = dataTypeOrImplicit;
    }

    public DataTypeOrImplicit getDataTypeOrImplicit() {
        return dataTypeOrImplicit;
    }

    public void setListOfNetDeclAssignments(List<NetDeclAssignment> listOfNetDeclAssignments) {
        this.listOfNetDeclAssignments = listOfNetDeclAssignments;
    }

    public List<NetDeclAssignment> getListOfNetDeclAssignments() {
        return listOfNetDeclAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
