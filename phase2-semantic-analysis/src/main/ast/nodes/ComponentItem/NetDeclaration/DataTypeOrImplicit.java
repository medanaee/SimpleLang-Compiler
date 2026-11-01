package main.ast.nodes.ComponentItem.NetDeclaration;


import main.ast.nodes.Expression.ConstantParamExpression;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class  DataTypeOrImplicit extends ConstantParamExpression {

    @Override
    public abstract <T> T accept(IVisitor<T> visitor);
}
