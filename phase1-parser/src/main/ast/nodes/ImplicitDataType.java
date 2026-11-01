package main.ast.nodes;

import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ImplicitDataType extends DataTypeOrImplicit {
    private String signing; // "signed" or "unsigned"
    private List<Expression> packedDimensions;

    public ImplicitDataType() {
        this.packedDimensions = new ArrayList<>();
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
