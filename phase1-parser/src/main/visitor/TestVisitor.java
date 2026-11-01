package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.Expression.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.ListOfPortDeclarations.*;
import main.ast.nodes.Statement.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.PortDirection.*;

import java.util.List;

public class TestVisitor extends Visitor<Void> {

    @Override
    public Void visit(SourceText sourceText) {
        for (ComponentDeclaration componentDeclaration : sourceText.getComponentDeclarationList()) {
            componentDeclaration.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ItemHeaderDeclaration itemHeaderDeclaration) {
        itemHeaderDeclaration.getComponentHeader().accept(this);
        for (ComponentItem componentItem : itemHeaderDeclaration.getComponentItemList()) {
            if (componentItem != null) {
                componentItem.accept(this);
            }

        }
        System.out.println(" number of component Item is : " + itemHeaderDeclaration.getComponentItemList().size());
        return null;
    }

    @Override
    public Void visit(ForLoopStatement forLoopStatement) {
        System.out.println(forLoopStatement.toString());
        return null;
    }

    @Override
    public Void visit(RepeatLoop repeatLoop) {
        System.out.println(repeatLoop.toString());
        return null;
    }

    @Override
    public Void visit(ForeverLoop foreverLoop) {
        System.out.println(foreverLoop.toString());
        return null;
    }

    @Override
    public Void visit(WhileLoop whileLoop) {
        System.out.println(whileLoop.toString());
        return null;
    }

    @Override
    public Void visit(IndexAssignment indexAssignment) {
        System.out.println(indexAssignment.toString());
        return null;
    }

    @Override
    public Void visit(NetDeclaration netDeclaration) {
        System.out.println(netDeclaration.toString());
        return null;
    }

    @Override
    public Void visit(NetDeclAssignment netDeclAssignment) {
        System.out.println(netDeclAssignment.toString());
        return null;
    }

    @Override
    public Void visit(ListOfNetDeclAssignments listOfNetDeclAssignments) {
        System.out.println(listOfNetDeclAssignments.toString());
        return null;
    }

    @Override
    public Void visit(ImplicitDataType implicitDataType) {
        System.out.println(implicitDataType.toString());
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        System.out.println(identifier.toString());
        return null;
    }

    @Override
    public Void visit(IntegerType integerType) {
        System.out.println(integerType.toString());
        return null;
    }

    @Override
    public Void visit(IntegerAtomType integerAtomType) {
        System.out.println(integerAtomType.toString());
        return null;
    }

    @Override
    public Void visit(IntegerVectorType integerVectorType) {
        System.out.println(integerVectorType.toString());
        return null;
    }

    @Override
    public Void visit(NonIntegerType nonIntegerType) {
        System.out.println(nonIntegerType.toString());
        return null;
    }

    @Override
    public Void visit(ExpandLoop expandLoop) {
        System.out.println(expandLoop.toString());
        return null;
    }

    @Override
    public Void visit(IndexDeclaration indexDeclaration) {
        System.out.println(indexDeclaration.toString());
        return null;
    }

    @Override
    public Void visit(BinaryExpression binaryExpression) {
        System.out.println(binaryExpression.toString());
        return null;
    }

    @Override
    public Void visit(UnaryExpression unaryExpression) {
        System.out.println(unaryExpression.toString());
        return null;
    }

    @Override
    public Void visit(TernaryExpression ternaryExpression) {
        System.out.println(ternaryExpression.toString());
        return null;
    }

    @Override
    public Void visit(AssignmentExpression assignmentExpression) {
        System.out.println(assignmentExpression.toString());
        return null;
    }

    @Override
    public Void visit(IncDecExpression incDecExpression) {
        System.out.println(incDecExpression.toString());
        return null;
    }

    @Override
    public Void visit(DataTypeOrImplicit dataTypeOrImplicit) {
        System.out.println(dataTypeOrImplicit.toString());
        return null;
    }

    @Override
    public Void visit(IdentifierComponent identifierComponent) {
        identifierComponent.getIdentifier().accept(this);
        for (ComponentItem componentItem : identifierComponent.getComponentItemList()) {
            if (componentItem != null) {
                componentItem.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(ConfigHeaderDeclaration configHeaderDeclaration) {
        configHeaderDeclaration.getConfigurationHeader().accept(this);
        for (ComponentItem componentItem : configHeaderDeclaration.getComponentItemList()) {
            if (componentItem != null) {
                componentItem.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(ConcatenationExpression concatenationExpression) {
        System.out.println(concatenationExpression.toString());
        return null;
    }

    @Override
    public Void visit(MintypmaxExpression mintypmaxExpression) {
        System.out.println(mintypmaxExpression.toString());
        return null;
    }

    @Override
    public Void visit(CallExpression callExpression) {
        System.out.println(callExpression.toString());
        return null;
    }

    @Override
    public Void visit(ArrayAccessExpression arrayAccessExpression) {
        System.out.println(arrayAccessExpression.toString());
        return null;
    }

    @Override
    public Void visit(RangeExpression rangeExpression) {
        System.out.println(rangeExpression.toString());
        return null;
    }

    @Override
    public Void visit(LiteralExpression literalExpression) {
        System.out.println(literalExpression.toString());
        return null;
    }

    @Override
    public Void visit(CastExpression castExpression) {
        System.out.println(castExpression.toString());
        return null;
    }

    @Override
    public Void visit(UnsizedDimension unsizedDimension) {
        System.out.println(unsizedDimension.toString());
        return null;
    }

    @Override
    public Void visit(ParamAssignment paramAssignment) {
        System.out.println(paramAssignment.toString());
        return null;
    }

    @Override
    public Void visit(ListOfPortDeclarations listOfPortDeclarations) {
        for (PortDecl portDecl : listOfPortDeclarations.getPortDeclList()) {
            portDecl.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(PortDecl portDecl) {
        System.out.println(portDecl.toString());
        return null;
    }

    @Override
    public Void visit(PortConnection portConnection) {
        System.out.println(portConnection.toString());
        return null;
    }

    @Override
    public Void visit(HierarchicalInstance hierarchicalInstance) {
        System.out.println(hierarchicalInstance.toString());
        return null;
    }

    @Override
    public Void visit(ComponentProgramInterfaceInstantiation componentProgramInterfaceInstantiation) {
        System.out.println(componentProgramInterfaceInstantiation.toString());
        return null;
    }

    @Override
    public Void visit(NetAssignment netAssignment) {
        System.out.println(netAssignment.toString());
        return null;
    }

    @Override
    public Void visit(ListOfNetAssignments listOfNetAssignments) {
        System.out.println(listOfNetAssignments.toString());
        return null;
    }

    @Override
    public Void visit(ExpressionStatement expressionStatement) {
        System.out.println(expressionStatement.toString());
        return null;
    }

    @Override
    public Void visit(ConditionalStatement conditionalStatement) {
        System.out.println(conditionalStatement.toString());
        return null;
    }

    @Override
    public Void visit(PeriodDeclaration periodDeclaration) {
        System.out.println(periodDeclaration.toString());
        return null;
    }

    @Override
    public Void visit(BlockingAssignment blockingAssignment) {
        System.out.println(blockingAssignment.toString());
        return null;
    }

    @Override
    public Void visit(NonBlockingAssignment nonBlockingAssignment) {
        System.out.println(nonBlockingAssignment.toString());
        return null;
    }

    @Override
    public Void visit(EmptyStatement emptyStatement) {
        System.out.println(emptyStatement.toString());
        return null;
    }

    @Override
    public Void visit(NamedArgument namedArgument) {
        System.out.println(namedArgument.toString());
        return null;
    }
}