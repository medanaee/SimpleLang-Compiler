package main.ast.nodes.ComponentItem;

import main.ast.nodes.*;
import main.visitor.IVisitor;

public class NetDeclaration extends ComponentItemDeclaration {
    public NetDeclaration() {
    }

    private String netType;
    private DataTypeOrImplicit dataTypeOrImplicit;
    private ListOfNetDeclAssignments listOfNetDeclAssignments;

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getNetType() {
        return netType;
    }

    public void setDataTypeOrImplicit(DataTypeOrImplicit dataTypeOrImplicit) {
        this.dataTypeOrImplicit = dataTypeOrImplicit;
    }

    public DataTypeOrImplicit getDataTypeOrImplicit() {
        return dataTypeOrImplicit;
    }

    public void setListOfNetDeclAssignments(ListOfNetDeclAssignments listOfNetDeclAssignments) {
        this.listOfNetDeclAssignments = listOfNetDeclAssignments;
    }

    public ListOfNetDeclAssignments getListOfNetDeclAssignments() {
        return listOfNetDeclAssignments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
