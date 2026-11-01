package main.ast.nodes.Expression;

// adjust package to your actual VariableLvalue
import main.ast.nodes.Expression.Operators.AssignmentOperator;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * AST node for:
 *   variable_lvalue assignment_operator expression
 */
public class OperatorAssignment extends Expression {

    private VariableLvalue variableLvalue;
    private AssignmentOperator operator;
    private Expression rightExpression;

    public VariableLvalue getVariableLvalue() {
        return variableLvalue;
    }

    public void setVariableLvalue(VariableLvalue variableLvalue) {
        this.variableLvalue = variableLvalue;
    }

    public AssignmentOperator getOperator() {
        return operator;
    }

    public void setOperator(AssignmentOperator operator) {
        this.operator = operator;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        List<Identifier> result = new ArrayList<>();
        result.addAll(variableLvalue.getIdentifiers());
        result.addAll(rightExpression.getIdentifiers());
        return result;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
