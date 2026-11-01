package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ComponentItem.*;

import main.ast.nodes.ComponentItem.Dimension.RangePackedDimension;
import main.ast.nodes.ComponentItem.Dimension.UnsizedDimension;
import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;

import main.ast.nodes.ComponentItem.ListOfPortConnections.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.ComponentItem.NetDeclaration.*;
import main.ast.nodes.Headers.*;

import main.ast.nodes.ParameterPortDeclaration.ParameterPortDeclaration;
import main.ast.nodes.ParameterPortList.*;
import main.ast.nodes.ParameterPortDeclaration.*;
import main.ast.nodes.PortDecl.DataTypePortDecl;
import main.ast.nodes.PortDecl.ImplicitDataPortDecl;
import main.ast.nodes.PortDecl.NetTypePortDecl;
import main.ast.nodes.PortDecl.PortDecl;
import main.ast.nodes.PortDirection.*;
import main.ast.nodes.Types.*;
import main.ast.nodes.Types.Number;
import main.ast.nodes.Expression.*;
import main.ast.nodes.ListOfParameterAssignments.*;
public interface IVisitor<T> {

    T visit(SourceText sourceText);

    T visit(ComponentHeader componentHeader);

    T visit(ConfigurationHeader configurationHeader);

    T visit(Identifier identifier);

    T visit(ItemHeaderDeclaration itemHeaderDeclaration);

    T visit(IdentifierComponent identifierComponent);

    // New ones:
    T visit(ConfigHeaderDeclaration configHeaderDeclaration);

    T visit(ConfigDeclaration configDeclaration);

    T visit(DeclarationBased declarationBased);

    T visit(EmptyBased emptyBased);

    T visit(ListBased listBased);
    T visit(ParameterPortDeclaration parameterPortDeclaration);
    T visit(ParameterDeclarationBased parameterDeclarationBased);

    T visit(ParameterAssignmentBased parameterAssignmentBased);

    T visit(ParameterDeclaration parameterDeclaration);
    T visit(InPortDirection inPortDirection);

    T visit(OutPortDirection outPortDirection);

    T visit(GenvarDeclaration genvarDeclaration);
    T visit(GenerateRegion generateRegion);
    T visit(GenerateItem generateItem);
    T visit(BlockLabel blockLabel);
    T visit(ContinuousSet continuousSet);
    T visit(WhileLoop whileLoop);

    T visit(ForeverLoop foreverLoop);

    T visit(RepeatLoop repeatLoop);
    T visit(ComponentProgramInterfaceInstantiation componentProgramInterfaceInstantiation);

    T visit(LoopGenerateConstruct loopGenerateConstruct);
    T visit(TimeLiteral timeLiteral);

   T visit(StringLiteral stringLiteral);

    T visit(Number number);
    T visit(PrimaryLiteral primaryLiteral);
    T visit(UnnamedListOfParameterAssignments unnamedListOfParameterAssignments);
    T visit(NamedListOfParameterAssignments namedListOfParameterAssignments);

    // Named parameter assignment: .NAME(expr)
    T visit(NamedParameterAssignment namedParameterAssignment);

    // ParamExpression hierarchy
    T visit(ParamExpression paramExpression);


    // mintypmax expression
    T visit(MintypmaxExpression mintypmaxExpression);
    T visit(Expression expression);
    T visit(ParameterValueAssignment parameterValueAssignment);

    T visit(RangeUnpackedDimension rangeUnpackedDimension);
    T visit(SingleUnpackedDimension singleUnpackedDimension);
    T visit(NameOfInstance nameOfInstance);
    T visit(HierarchicalInstance hierarchicalInstance);
    T visit(GenerateBlockLabel generateBlockLabel);
    T visit(GenerateBlockName generateBlockName);
    T visit(DoGenerateBlock doGenerateBlock);
    // in IVisitor<T>
    T visit(NetTypeNetDeclaration netTypeNetDeclaration);
    T visit(IdentifierNetDeclaration listOfNetDeclAssignments);
    T visit(NetDeclAssignment netDeclAssignment);
    T visit(UnsizedDimension unsizedDimension);
    T visit(RangePackedDimension rangePackedDimension);
    T visit(GenvarInitialization genvarInitialization);




    // --- Net assignments ---
    T visit(NetAssignment netAssignment);


    // concrete subclass
    T visit(ConstantBitSelect constantBitSelect);

    // --- Net lvalues ---
    // concrete subclasses
    T visit(IdentifierNetLvalue identifierNetLvalue);
    T visit(ConcatenationNetLvalue concatenationNetLvalue);

    T visit(PortAssign portAssign);

    T visit(DotNamedPortConnection dotNamedPortConnection);

    T visit(WildcardNamedPortConnection wildcardNamedPortConnection);
    T visit(ConstantRange constantRange);
    // --- Genvar initialization ---


    T visit(ImplicitDataType implicitDataType);
    T visit(EmptyGenerateItem emptyGenerateItem);
    T visit(AssignGenvarIteration assignGenvarIteration);
    T visit(PreIncDecGenvarIteration preIncDecGenvarIteration);
    T visit(PostIncDecGenvarIteration postIncDecGenvarIteration);

    T visit(ParamAssignment paramAssignment);
    T visit(BitSelect bitSelect);
    T visit(RangeOnlySelect rangeOnlySelect);
    T visit(BitSelectWithRange sel);
    // Expression / select_ related


    // variable_lvalue related
    T visit(HierarchicalVariableLvalue hierarchicalVariableLvalue);
    T visit(ConcatenationVariableLvalue concatenationVariableLvalue);
    T visit(OrderedArg orderedArg);
    T visit(NamedArg namedArg);
    T visit(MixedListOfArguments mixedListOfArguments);
    T visit(NamedOnlyListOfArguments namedOnlyListOfArguments);
    T visit(ArgList argList);

    T visit(SubroutineCall subroutineCall);

    T visit(PeriodDeclaration periodDeclaration);
    T visit(SimpleSubroutineCallStatement stmt);
    T visit(VoidSubroutineCallStatement stmt);
    T visit(NonEmptyConcatenation concat);
    T visit(EmptyConcatenation concat);
    T visit(ConstantConcatenation constantConcatenation);
    T visit(Statement statement); // if you later have concrete subclasses, you may override this
    T visit(NonEmptyStatementOrNull nonEmptyStatementOrNull);
    T visit(EmptyStatementOrNull emptyStatementOrNull);

    T visit(CondPredicate condPredicate);

    T visit(ConditionalStatement conditionalStatement);

    T visit(VariableAssignment variableAssignment);

    T visit(ProceduralContinuousSet proceduralContinuousSet);
    T visit(ConstantMintypmaxExpression constantMintypmaxExpression);
    T visit(ConstantTernaryExpression constantTernaryExpression);
    T visit(ConstantUnaryExpression constantUnaryExpression);
    T visit(NonblockingAssignment nonblockingAssignment);
    T visit(IntegerDataType integerDataType);
    T visit(NonIntegerDataType nonIntegerDataType);
    T visit(StringDataType stringDataType);
    T visit(IdentifierDataType identifierDataType);
    T visit(ConstantLiteralPrimary constantLiteralPrimary);
    T visit(ConstantIdentifierPrimary constantIdentifierPrimary);
    T visit(ConstantCallPrimary constantCallPrimary);
    T visit(ConstantMintypmaxPrimary constantMintypmaxPrimary);
    T visit(ConstantPrimaryCast constantPrimaryCast);
    T visit(ConstantTypeCastPrimary constantTypeCastPrimary);
    T visit(ConstantNullPrimary constantNullPrimary);
   //T visit(ConstantNullPrimary constantNullPrimary);
    T visit(ComponentCommonItem componentCommonItem);
    T visit(ComponentItemDeclarationEmpty componentItemDeclarationEmpty);
    T visit(AssignmentExpression assignmentExpression);
    T visit(BinaryExpression binaryExpression);
    T visit(CallPrimary callPrimary);
    T visit(ConstantBinaryExpression constantBinaryExpression);
    T visit(HierarchicalPrimary hierarchicalPrimary);
    T visit(IfExpressionExpression ifExpressionExpression);
    T visit(IncOrDecStatement incOrDecStatement);
    T visit(MintypmaxPrimary mintypmaxPrimary);
    T visit(NonblockingAssignmentStatement nonblockingAssignmentStatement);
    T visit(OperatorAssignment operatorAssignment);
    T visit(OperatorAssignmentStatement operatorAssignmentStatement);
    T visit(PostfixIncOrDecExpression postfixIncOrDecExpression);
    T visit(PrefixIncOrDecExpression prefixIncOrDecExpression);
    T visit(PrimaryCast primaryCast);
    T visit(ProceduralContinuousSetStatement proceduralContinuousSetStatement);
    T visit(UnaryExpression unaryExpression);
    T visit(SimpleType simpleType);
    T visit(TypeCastPrimary typeCastPrimary);
    T visit(ImplicitDataPortDecl implicitDataPortDecl);
    T visit(NetTypePortDecl netTypePortDecl);
    T visit(DataTypePortDecl dataTypePortDecl);
    T visit(IntegerType integerType);
}
