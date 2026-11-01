package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class DataType extends DataTypeOrImplicit {
    private Node subType; 

    private String stringType; // if "string"
    private String signing; // "signed" or "unsigned"
    private List<Expression> packedDimensions;

    public DataType() {
        this.packedDimensions = new ArrayList<>();
    }

    public void setSubType(Node subType) {
        this.subType = subType;
    }

    public Node getSubType() {
        return subType;
    }

    public void setStringType(String stringType) {
        this.stringType = stringType;
    }

    public String getStringType() {
        return stringType;
    }

    public void setSigning(String signing) {
        this.signing = signing;
    }

    public String getSigning() {
        return signing;
    }

    public void setPackedDimensions(List<Expression> packedDimensions) {
        this.packedDimensions = packedDimensions;
    }

    public void addPackedDimension(Expression dimension) {
        this.packedDimensions.add(dimension);
    }

    public List<Expression> getPackedDimensions() {
        return packedDimensions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
