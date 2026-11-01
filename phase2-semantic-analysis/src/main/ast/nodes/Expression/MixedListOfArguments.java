package main.ast.nodes.Expression;

import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents:
 *   ordered_arg (',' ordered_arg)* (',' named_arg)*
 */
public class MixedListOfArguments extends ListOfArguments {

    private final List<OrderedArg> orderedArgs = new ArrayList<>();
    private final List<NamedArg> namedArgs = new ArrayList<>();

    public void addOrderedArg(OrderedArg arg) {
        if (arg != null) {
            this.orderedArgs.add(arg);
        }
    }

    public void addNamedArg(NamedArg arg) {
        if (arg != null) {
            this.namedArgs.add(arg);
        }
    }

    public List<OrderedArg> getOrderedArgs() {
        return orderedArgs;
    }

    public List<NamedArg> getNamedArgs() {
        return namedArgs;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
