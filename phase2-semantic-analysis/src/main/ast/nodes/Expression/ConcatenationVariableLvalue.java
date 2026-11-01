package main.ast.nodes.Expression;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

/**
 * '{' variable_lvalue ( ',' variable_lvalue)* '}'
 */
public class ConcatenationVariableLvalue extends VariableLvalue {

    private final List<VariableLvalue> variableLvalues = new ArrayList<>();

    public void addVariableLvalue(VariableLvalue variableLvalue) {
        if (variableLvalue != null) {
            this.variableLvalues.add(variableLvalue);
        }
    }

    public List<VariableLvalue> getVariableLvalues() {
        return variableLvalues;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        List<Identifier> result = new ArrayList<>();
        for(VariableLvalue variableLvalue:variableLvalues)
            result.addAll(variableLvalue.getIdentifiers());
        return result;
    }

    @Override
    public String getName() {
        return variableLvalues.get(0).getName();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
