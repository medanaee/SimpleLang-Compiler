package main.ast.nodes.Expression;

import main.ast.nodes.ComponentItem.NetDeclaration.DataTypeOrImplicit;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class ParamExpression extends DataTypeOrImplicit {

    public ParamExpression() {
    }

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
