package main.ast.nodes.Expression;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class CallExpression extends Expression {
    private Identifier methodName;
    private List<Expression> arguments = new ArrayList<>();

    public CallExpression(Identifier methodName, List<Expression> arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public CallExpression(Identifier methodName) {
        this.methodName = methodName;
    }

    public Identifier getMethodName() {
        return methodName;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
