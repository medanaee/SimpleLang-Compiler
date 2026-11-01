package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.ast.nodes.Types.IntegerType;
import main.ast.nodes.Types.NonIntegerType;
import main.ast.nodes.Types.Signing;
import main.visitor.IVisitor;

import java.util.List;

/**
 * Matches grammar:
 *
 * primary :
 *   integer_type '\'' '(' expression ')'
 * | non_integer_type '\'' '(' expression ')'
 * | signing       '\'' '(' expression ')'
 * | 'string'      '\'' '(' expression ')'
 * | 'const'       '\'' '(' expression ')'
 */
public class TypeCastPrimary extends Primary {

    // integer_type '\'' '(' expression ')'
    private IntegerType integerType;

    // non_integer_type '\'' '(' expression ')'
    private NonIntegerType nonIntegerType;

    // signing '\'' '(' expression ')'
    private Signing signing;

    // 'string' '\'' '(' expression ')'
    private boolean stringKeyword;

    // 'const' '\'' '(' expression ')'
    private boolean constKeyword;

    // the expression inside the parentheses in all alternatives
    private Expression expression;

    // --------- setters exactly as used in the grammar ---------

    public void setIntegerType(IntegerType integerType) {
        this.integerType = integerType;
    }

    public void setNonIntegerType(NonIntegerType nonIntegerType) {
        this.nonIntegerType = nonIntegerType;
    }

    public void setSigning(Signing signing) {
        this.signing = signing;
    }

    public void setStringKeyword(boolean stringKeyword) {
        this.stringKeyword = stringKeyword;
    }

    public void setConstKeyword(boolean constKeyword) {
        this.constKeyword = constKeyword;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    // --------- getters (optional but useful) ---------

    public IntegerType getIntegerType() {
        return integerType;
    }

    public NonIntegerType getNonIntegerType() {
        return nonIntegerType;
    }

    public Signing getSigning() {
        return signing;
    }

    public boolean isStringKeyword() {
        return stringKeyword;
    }

    public boolean isConstKeyword() {
        return constKeyword;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return List.of();
    }
// --------- visitor ---------

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
