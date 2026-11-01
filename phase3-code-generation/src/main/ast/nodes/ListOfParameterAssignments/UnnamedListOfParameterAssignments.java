package main.ast.nodes.ListOfParameterAssignments;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.Expression.ParamExpression;
import main.visitor.IVisitor;

public class UnnamedListOfParameterAssignments extends ListOfParameterAssignments {

    private List<ParamExpression> paramExpressions = new ArrayList<>();

    public UnnamedListOfParameterAssignments() {
    }

    public void addParamExpression(ParamExpression expr) {
        if (expr != null) {
            this.paramExpressions.add(expr);
        }
    }

    public List<ParamExpression> getParamExpressions() {
        return paramExpressions;
    }

    public void setParamExpressions(List<ParamExpression> paramExpressions) {
        this.paramExpressions = paramExpressions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
