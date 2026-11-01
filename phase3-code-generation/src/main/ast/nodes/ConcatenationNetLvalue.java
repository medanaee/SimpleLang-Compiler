package main.ast.nodes;

import java.util.ArrayList;
import java.util.List;

import main.visitor.IVisitor;

public class ConcatenationNetLvalue extends NetLvalue {

    private final List<NetLvalue> netLvalues = new ArrayList<>();

    public ConcatenationNetLvalue() {
        super();
    }

    public void addNetLvalue(NetLvalue netLvalue) {
        if (netLvalue != null) {
            this.netLvalues.add(netLvalue);
        }
    }

    @Override
    public String getName() {
        return netLvalues.get(0).getName();
    }

    public List<NetLvalue> getNetLvalues() {
        return netLvalues;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
