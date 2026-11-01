package main.ast.nodes;



import main.ast.nodes.Node;
import main.ast.nodes.Expression.Expression;
import main.visitor.IVisitor;

// Adjust this import if you place NetLvalue in a different package


public class NetAssignment extends Node {

    private NetLvalue netLvalue;
    private Expression expression;

    public NetAssignment() {
        super();
    }

    public void setNetLvalue(NetLvalue netLvalue) {
        this.netLvalue = netLvalue;
    }

    public NetLvalue getNetLvalue() {
        return netLvalue;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
    public String getName(){
        return netLvalue.getName();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
