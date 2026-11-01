package main.ast.nodes.Expression;

import main.ast.nodes.Expression.Operators.BinaryOperator;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents all binary operations in the expression rule, e.g.:
 *   expression op expression
 * where op can be +, -, *, /, %, <<, >>, >>>, <<<, <, <=, >, >=,
 * ==, !=, ===, !==, ==?, !=?, &, ^, ^~, ~^, |, &&, ||, **, ...
 */
public class BinaryExpression extends Expression {

    private Expression left;
    private Expression right;
    private BinaryOperator operator;

    public BinaryExpression() {
    }

    public BinaryExpression(Expression left, BinaryOperator operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(BinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public List<Identifier> getIdentifiers(){
        List<Identifier> result = new ArrayList<>();
        result.addAll(left.getIdentifiers());
        result.addAll(right.getIdentifiers());
        return result;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
