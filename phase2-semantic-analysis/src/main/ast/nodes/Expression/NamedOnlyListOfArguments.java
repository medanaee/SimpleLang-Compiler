package main.ast.nodes.Expression;

import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents:
 *   named_arg ( ',' named_arg)*
 */
public class NamedOnlyListOfArguments extends ListOfArguments {

    private final List<NamedArg> namedArgs = new ArrayList<>();

    public void addNamedArg(NamedArg arg) {
        if (arg != null) {
            this.namedArgs.add(arg);
        }
    }

    public List<NamedArg> getNamedArgs() {
        return namedArgs;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
