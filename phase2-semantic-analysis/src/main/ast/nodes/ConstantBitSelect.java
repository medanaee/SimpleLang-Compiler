package main.ast.nodes;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.ConstantSelect;
import main.ast.nodes.Expression.ConstantExpression;
import main.visitor.IVisitor;

public class ConstantBitSelect extends ConstantSelect {

    private final List<ConstantExpression> constantExpressions = new ArrayList<>();

    public ConstantBitSelect() {
        super();
    }

    public void addConstantExpression(ConstantExpression expression) {
        if (expression != null) {
            this.constantExpressions.add(expression);
        }
    }

    public List<ConstantExpression> getConstantExpressions() {
        return constantExpressions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
