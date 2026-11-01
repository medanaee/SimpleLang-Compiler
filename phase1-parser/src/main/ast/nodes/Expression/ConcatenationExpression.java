package main.ast.nodes.Expression;

import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ConcatenationExpression extends Expression {
    private List<Expression> expressions = new ArrayList<>();

    public ConcatenationExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
