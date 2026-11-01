package main.ast.nodes.Expression;

import main.ast.nodes.Types.SimpleType;
import main.ast.nodes.Types.IntegerType;
import main.ast.nodes.Types.NonIntegerType;
import main.ast.nodes.Types.Signing;
import main.visitor.IVisitor;

/**
 * constant_primary :
 *   integer_type   '\'' '(' constant_expression ')'
 * | non_integer_type '\'' '(' constant_expression ')'
 * | signing        '\'' '(' constant_expression ')'
 * | 'string'       '\'' '(' constant_expression ')'
 * | 'const'        '\'' '(' constant_expression ')'
 */
public class ConstantTypeCastPrimary extends ConstantPrimary {

    private IntegerType integerType;
    private NonIntegerType nonIntegerType;
    private Signing signing;
    private boolean stringKeyword;
    private boolean constKeyword;
    private ConstantExpression expression;

    public SimpleType getSimpleType() {
        return simpleType;
    }

    public void setSimpleType(SimpleType simpleType) {
        this.simpleType = simpleType;
    }

    private SimpleType simpleType;

    public IntegerType getIntegerType() {
        return integerType;
    }

    public void setIntegerType(IntegerType integerType) {
        this.integerType = integerType;
    }

    public NonIntegerType getNonIntegerType() {
        return nonIntegerType;
    }

    public void setNonIntegerType(NonIntegerType nonIntegerType) {
        this.nonIntegerType = nonIntegerType;
    }

    public Signing getSigning() {
        return signing;
    }

    public void setSigning(Signing signing) {
        this.signing = signing;
    }

    public boolean isStringKeyword() {
        return stringKeyword;
    }

    public void setStringKeyword(boolean stringKeyword) {
        this.stringKeyword = stringKeyword;
    }

    public boolean isConstKeyword() {
        return constKeyword;
    }

    public void setConstKeyword(boolean constKeyword) {
        this.constKeyword = constKeyword;
    }

    public ConstantExpression getExpression() {
        return expression;
    }

    public void setExpression(ConstantExpression expression) {
        this.expression = expression;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
