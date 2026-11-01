package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ComponentItem.ComponentItem;
import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;
import main.ast.nodes.ComponentItem.GenerateRegion;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.Headers.*;
import main.ast.nodes.Statement.*;
import main.ast.nodes.Expression.*;

import main.ast.nodes.ListOfPortDeclarations.*;
import main.ast.nodes.ParameterPortList.*;
import main.ast.nodes.ParameterPortDeclaration.*;
import main.ast.nodes.PortDirection.*;

import main.ast.nodes.ComponentItem.LoopStatement.PeriodDeclaration;

public interface IVisitor<T> {
    T visit(PeriodDeclaration periodDeclaration);

    T visit(SourceText sourceText);

    T visit(ComponentDeclaration componentDeclaration);

    T visit(ComponentHeader componentHeader);

    T visit(ConfigurationHeader configurationHeader);

    T visit(Identifier identifier);

    T visit(ComponentName componentName);

    T visit(ItemHeaderDeclaration itemHeaderDeclaration);

    T visit(ComponentItem componentItem);

    T visit(IdentifierComponent identifierComponent);

    // New ones:
    T visit(ConfigHeaderDeclaration configHeaderDeclaration);

    T visit(ConfigDeclaration configDeclaration);

    T visit(DeclarationBased declarationBased);

    T visit(EmptyBased emptyBased);

    T visit(ListBased listBased);

    T visit(ListOfParamAssignments listOfParamAssignments);

    T visit(ParameterPortDeclaration parameterPortDeclaration);

    T visit(PortDecl portDecl);

    T visit(ListOfPortDeclarations listOfPortDeclarations);

    T visit(ParameterAssignmentBased parameterAssignmentBased);

    T visit(DataType dataType);

    T visit(ParameterDeclaration parameterDeclaration);

    T visit(PortDirection portDirection);

    T visit(InPortDirection inPortDirection);

    T visit(OutPortDirection outPortDirection);

    T visit(PortPrefix portPrefix);

    T visit(GenvarDeclaration genvarDeclaration);

    T visit(GenerateRegion generateRegion);

    T visit(GenerateItem generateItem);

    T visit(BlockLabel blockLabel);

    T visit(ContinuousSet continuousSet);

    T visit(LoopStatement loopStatement);

    T visit(WhileLoop whileLoop);

    T visit(ForeverLoop foreverLoop);

    T visit(RepeatLoop repeatLoop);

    T visit(DataTypeOrImplicit dataTypeOrImplicit);

    T visit(ListOfNetDeclAssignments listOfNetDeclAssignments);

    T visit(IntegerType integerType);

    T visit(IntegerAtomType integerAtomType);

    T visit(IntegerVectorType integerVectorType);

    T visit(NonIntegerType nonIntegerType);

    T visit(ExpandLoop expandLoop);

    T visit(IndexAssignment indexAssignment);

    T visit(IndexDeclaration indexDeclaration);

    T visit(NetDeclaration netDeclaration);

    T visit(NetDeclAssignment netDeclAssignment);

    T visit(ImplicitDataType implicitDataType);

    T visit(ForLoopStatement forLoopStatement);

    T visit(BinaryExpression binaryExpression);

    T visit(UnaryExpression unaryExpression);

    T visit(TernaryExpression ternaryExpression);

    T visit(AssignmentExpression assignmentExpression);

    T visit(IncDecExpression incDecExpression);

    // New Expression Node Visits
    T visit(NamedArgument namedArgument);

    T visit(ConcatenationExpression concatenationExpression);

    T visit(MintypmaxExpression mintypmaxExpression);

    T visit(CallExpression callExpression);

    T visit(ArrayAccessExpression arrayAccessExpression);

    T visit(RangeExpression rangeExpression);

    T visit(LiteralExpression literalExpression);

    T visit(CastExpression castExpression);

    T visit(UnsizedDimension unsizedDimension);

    T visit(ParamAssignment paramAssignment);

    T visit(PortConnection portConnection);

    T visit(HierarchicalInstance hierarchicalInstance);

    T visit(ComponentProgramInterfaceInstantiation componentProgramInterfaceInstantiation);

    T visit(NetAssignment netAssignment);

    T visit(ListOfNetAssignments listOfNetAssignments);

    // Statement Nodes
    T visit(ExpressionStatement expressionStatement);

    T visit(ConditionalStatement conditionalStatement);

    T visit(BlockingAssignment blockingAssignment);

    T visit(NonBlockingAssignment nonBlockingAssignment);

    T visit(EmptyStatement emptyStatement);
}
