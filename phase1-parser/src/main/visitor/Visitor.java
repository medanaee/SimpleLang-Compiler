package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.Expression.*;
import main.ast.nodes.Statement.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.Headers.*;

import main.ast.nodes.ListOfPortDeclarations.*;
import main.ast.nodes.ParameterPortDeclaration.*;
import main.ast.nodes.PortDirection.*;
import main.ast.nodes.ParameterPortList.*;
import main.ast.nodes.ComponentItem.GenerateItem.*;
import main.ast.nodes.ComponentItem.*;

public abstract class Visitor<T> implements IVisitor<T> {

    @Override
    public T visit(SourceText sourceText) {
        return null;
    }

    @Override
    public T visit(Identifier identifier) {
        return null;
    }

    @Override
    public T visit(ComponentDeclaration componentDeclaration) {
        return null;
    }

    @Override
    public T visit(ComponentHeader componentHeader) {
        return null;
    }

    @Override
    public T visit(ConfigurationHeader configurationHeader) {
        return null;
    }

    @Override
    public T visit(ComponentName componentName) {
        return null;
    }

    @Override
    public T visit(ItemHeaderDeclaration itemHeaderDeclaration) {
        return null;
    }

    @Override
    public T visit(IdentifierComponent identifierComponent) {
        return null;
    }

    @Override
    public T visit(ConfigHeaderDeclaration configHeaderDeclaration) {
        return null;
    }

    @Override
    public T visit(ConfigDeclaration configDeclaration) {
        return null;
    }

    @Override
    public T visit(DeclarationBased declarationBased) {
        return null;
    }

    @Override
    public T visit(EmptyBased emptyBased) {
        return null;
    }

    @Override
    public T visit(ListBased listBased) {
        return null;
    }

    @Override
    public T visit(ListOfParamAssignments listOfParamAssignments) {
        return null;
    }

    @Override
    public T visit(ParameterPortDeclaration parameterPortDeclaration) {
        return null;
    }

    @Override
    public T visit(PortDecl portDecl) {
        return null;
    }

    @Override
    public T visit(ListOfPortDeclarations listOfPortDeclarations) {
        return null;
    }


    @Override
    public T visit(ParameterAssignmentBased parameterAssignmentBased) {
        return null;
    }

    @Override
    public T visit(DataType dataType) {
        return null;
    }

    @Override
    public T visit(ParameterDeclaration parameterDeclaration) {
        return null;
    }

    @Override
    public T visit(PortDirection portDirection) {
        return null;
    }

    @Override
    public T visit(InPortDirection inPortDirection) {
        return null;
    }

    @Override
    public T visit(OutPortDirection outPortDirection) {
        return null;
    }

    @Override
    public T visit(PortPrefix portPrefix) {
        return null;
    }

    @Override
    public T visit(GenerateRegion generateRegion) {
        return null;
    }

    @Override
    public T visit(GenerateItem generateItem) {
        return null;
    }

    @Override
    public T visit(GenvarDeclaration genvarDeclaration) {
        return null;
    }

    @Override
    public T visit(BlockLabel blockLabel) {
        return null;
    }

    @Override
    public T visit(ContinuousSet continuousSet) {
        return null;
    }

    @Override
    public T visit(LoopStatement loopStatement) {
        return null;
    }

    @Override
    public T visit(WhileLoop whileLoop) {
        return null;
    }

    @Override
    public T visit(ForeverLoop foreverLoop) {
        return null;
    }

    @Override
    public T visit(ComponentItem componentItem) {
        return null;
    }

    @Override
    public T visit(RepeatLoop repeatLoop) {
        return null;
    }

    @Override
    public T visit(ForLoopStatement forLoopStatement) {
        return null;
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
        return null;
    }

    @Override
    public T visit(UnaryExpression unaryExpression) {
        return null;
    }

    @Override
    public T visit(TernaryExpression ternaryExpression) {
        return null;
    }

    @Override
    public T visit(AssignmentExpression assignmentExpression) {
        return null;
    }

    @Override
    public T visit(IncDecExpression incDecExpression) {
        return null;
    }

    @Override
    public T visit(NamedArgument namedArgument) {
        return null;
    }

    @Override
    public T visit(ConcatenationExpression concatenationExpression) {
        return null;
    }

    @Override
    public T visit(MintypmaxExpression mintypmaxExpression) {
        return null;
    }

    @Override
    public T visit(CallExpression callExpression) {
        return null;
    }

    @Override
    public T visit(ArrayAccessExpression arrayAccessExpression) {
        return null;
    }

    @Override
    public T visit(RangeExpression rangeExpression) {
        return null;
    }

    @Override
    public T visit(LiteralExpression literalExpression) {
        return null;
    }

    @Override
    public T visit(CastExpression castExpression) {
        return null;
    }

    @Override
    public T visit(UnsizedDimension unsizedDimension) {
        return null;
    }

    @Override
    public T visit(ParamAssignment paramAssignment) {
        return null;
    }

    @Override
    public T visit(PortConnection portConnection) {
        return null;
    }

    @Override
    public T visit(HierarchicalInstance hierarchicalInstance) {
        return null;
    }

    @Override
    public T visit(ComponentProgramInterfaceInstantiation instantiation) {
        return null;
    }

    @Override
    public T visit(NetAssignment netAssignment) {
        return null;
    }

    @Override
    public T visit(ListOfNetAssignments listOfNetAssignments) {
        return null;
    }

    @Override
    public T visit(ExpressionStatement expressionStatement) {
        return null;
    }

    @Override
    public T visit(ConditionalStatement conditionalStatement) {
        return null;
    }

    @Override
    public T visit(BlockingAssignment blockingAssignment) {
        return null;
    }

    @Override
    public T visit(NonBlockingAssignment nonBlockingAssignment) {
        return null;
    }

    @Override
    public T visit(EmptyStatement emptyStatement) {
        return null;
    }

    @Override
    public T visit(IndexAssignment indexAssignment) {
        return null;
    }

    @Override
    public T visit(IndexDeclaration indexDeclaration) {
        return null;
    }

    @Override
    public T visit(NetDeclaration netDeclaration) {
        return null;
    }

    @Override
    public T visit(NetDeclAssignment netDeclAssignment) {
        return null;
    }

    @Override
    public T visit(ListOfNetDeclAssignments listOfNetDeclAssignments) {
        return null;
    }

    @Override
    public T visit(ImplicitDataType implicitDataType) {
        return null;
    }

    @Override
    public T visit(IntegerType integerType) {
        return null;
    }

    @Override
    public T visit(IntegerAtomType integerAtomType) {
        return null;
    }

    @Override
    public T visit(IntegerVectorType integerVectorType) {
        return null;
    }

    @Override
    public T visit(NonIntegerType nonIntegerType) {
        return null;
    }

    @Override
    public T visit(DataTypeOrImplicit dataTypeOrImplicit) {
        return null;
    }

    @Override
    public T visit(PeriodDeclaration periodDeclaration) {
        return null;
    }

    @Override
    public T visit(ExpandLoop expandLoop) {
        return null;
    }
}
