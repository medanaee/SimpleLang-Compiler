// Generated from G:/stuff/Ray Universe/Compiler_TA/PLC-Fall2025/src/main/grammar/SimpleLang.g4 by ANTLR 4.13.2
package main.grammar;

import main.ast.nodes.*;
import main.ast.nodes.Headers.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ParameterPortList.*;
import main.ast.nodes.ParameterPortDeclaration.*;
import main.ast.nodes.PortDirection.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.ComponentItem.Dimension.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.ComponentItem.NetDeclaration.*;
import main.ast.nodes.ComponentItem.GenerateItem.*;
import main.ast.nodes.ComponentItem.ListOfPortConnections.*;
import main.ast.nodes.Expression.Operators.*;
import main.ast.nodes.Expression.*;
import main.ast.nodes.Types.*;
import main.ast.nodes.PortDecl.*;
import main.ast.nodes.ListOfParameterAssignments.*;
import main.ast.nodes.Types.Number;
import main.ast.nodes.Types.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpleLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpleLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#source_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource_text(SimpleLangParser.Source_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#component_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent_declaration(SimpleLangParser.Component_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#component_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent_header(SimpleLangParser.Component_headerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#configuration_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfiguration_header(SimpleLangParser.Configuration_headerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#component_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent_name(SimpleLangParser.Component_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#parameter_port_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_port_list(SimpleLangParser.Parameter_port_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#parameter_port_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_port_declaration(SimpleLangParser.Parameter_port_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_port_declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_port_declarations(SimpleLangParser.List_of_port_declarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#port_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort_decl(SimpleLangParser.Port_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#port_direction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort_direction(SimpleLangParser.Port_directionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#port_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort_prefix(SimpleLangParser.Port_prefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#component_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent_item(SimpleLangParser.Component_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#component_common_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent_common_item(SimpleLangParser.Component_common_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#component_item_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent_item_declaration(SimpleLangParser.Component_item_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#parameter_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_declaration(SimpleLangParser.Parameter_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#genvar_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenvar_declaration(SimpleLangParser.Genvar_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#net_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNet_declaration(SimpleLangParser.Net_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type(SimpleLangParser.Data_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#data_type_or_implicit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type_or_implicit(SimpleLangParser.Data_type_or_implicitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#implicit_data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplicit_data_type(SimpleLangParser.Implicit_data_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#integer_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_type(SimpleLangParser.Integer_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#integer_atom_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_atom_type(SimpleLangParser.Integer_atom_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#integer_vector_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_vector_type(SimpleLangParser.Integer_vector_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#non_integer_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_integer_type(SimpleLangParser.Non_integer_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#net_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNet_type(SimpleLangParser.Net_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#signing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigning(SimpleLangParser.SigningContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#simple_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_type(SimpleLangParser.Simple_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_genvar_identifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_genvar_identifiers(SimpleLangParser.List_of_genvar_identifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_net_decl_assignments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_net_decl_assignments(SimpleLangParser.List_of_net_decl_assignmentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_param_assignments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_param_assignments(SimpleLangParser.List_of_param_assignmentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#net_decl_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNet_decl_assignment(SimpleLangParser.Net_decl_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#param_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_assignment(SimpleLangParser.Param_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#unpacked_dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnpacked_dimension(SimpleLangParser.Unpacked_dimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#packed_dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPacked_dimension(SimpleLangParser.Packed_dimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#unsized_dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsized_dimension(SimpleLangParser.Unsized_dimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#block_label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_label(SimpleLangParser.Block_labelContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#component_program_interface_instantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent_program_interface_instantiation(SimpleLangParser.Component_program_interface_instantiationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#parameter_value_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_value_assignment(SimpleLangParser.Parameter_value_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_parameter_assignments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_parameter_assignments(SimpleLangParser.List_of_parameter_assignmentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#named_parameter_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamed_parameter_assignment(SimpleLangParser.Named_parameter_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#hierarchical_instance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHierarchical_instance(SimpleLangParser.Hierarchical_instanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#name_of_instance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName_of_instance(SimpleLangParser.Name_of_instanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_port_connections}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_port_connections(SimpleLangParser.List_of_port_connectionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#named_port_connection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamed_port_connection(SimpleLangParser.Named_port_connectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#port_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort_assign(SimpleLangParser.Port_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#generate_region}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerate_region(SimpleLangParser.Generate_regionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#loop_generate_construct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_generate_construct(SimpleLangParser.Loop_generate_constructContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#genvar_initialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenvar_initialization(SimpleLangParser.Genvar_initializationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#genvar_iteration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenvar_iteration(SimpleLangParser.Genvar_iterationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#generate_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerate_block(SimpleLangParser.Generate_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#generate_block_label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerate_block_label(SimpleLangParser.Generate_block_labelContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#generate_block_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerate_block_name(SimpleLangParser.Generate_block_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#generate_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerate_item(SimpleLangParser.Generate_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#continuous_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuous_set(SimpleLangParser.Continuous_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#procedural_continuous_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedural_continuous_set(SimpleLangParser.Procedural_continuous_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_net_assignments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_net_assignments(SimpleLangParser.List_of_net_assignmentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#net_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNet_assignment(SimpleLangParser.Net_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#operator_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator_assignment(SimpleLangParser.Operator_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#assignment_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_operator(SimpleLangParser.Assignment_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#nonblocking_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonblocking_assignment(SimpleLangParser.Nonblocking_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#variable_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_assignment(SimpleLangParser.Variable_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#statement_or_null}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_or_null(SimpleLangParser.Statement_or_nullContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SimpleLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#statement_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_item(SimpleLangParser.Statement_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#conditional_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional_statement(SimpleLangParser.Conditional_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#cond_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_predicate(SimpleLangParser.Cond_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#loop_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_statement(SimpleLangParser.Loop_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#period_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPeriod_declaration(SimpleLangParser.Period_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#subroutine_call_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine_call_statement(SimpleLangParser.Subroutine_call_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#concatenation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatenation(SimpleLangParser.ConcatenationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_concatenation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_concatenation(SimpleLangParser.Constant_concatenationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#arg_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_list(SimpleLangParser.Arg_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#subroutine_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine_call(SimpleLangParser.Subroutine_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#list_of_arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_of_arguments(SimpleLangParser.List_of_argumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#ordered_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdered_arg(SimpleLangParser.Ordered_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#named_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamed_arg(SimpleLangParser.Named_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#inc_or_dec_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInc_or_dec_expression(SimpleLangParser.Inc_or_dec_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_expression(SimpleLangParser.Constant_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_mintypmax_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_mintypmax_expression(SimpleLangParser.Constant_mintypmax_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_param_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_param_expression(SimpleLangParser.Constant_param_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#param_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_expression(SimpleLangParser.Param_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_range(SimpleLangParser.Constant_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SimpleLangParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#mintypmax_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMintypmax_expression(SimpleLangParser.Mintypmax_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#part_select_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPart_select_range(SimpleLangParser.Part_select_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#genvar_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenvar_expression(SimpleLangParser.Genvar_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_primary(SimpleLangParser.Constant_primaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(SimpleLangParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#primary_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_literal(SimpleLangParser.Primary_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#bit_select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBit_select(SimpleLangParser.Bit_selectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#select_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_(SimpleLangParser.Select_Context ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_bit_select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_bit_select(SimpleLangParser.Constant_bit_selectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#constant_select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_select(SimpleLangParser.Constant_selectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#net_lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNet_lvalue(SimpleLangParser.Net_lvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#variable_lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_lvalue(SimpleLangParser.Variable_lvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#unary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator(SimpleLangParser.Unary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#inc_or_dec_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInc_or_dec_operator(SimpleLangParser.Inc_or_dec_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(SimpleLangParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#integral_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegral_number(SimpleLangParser.Integral_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#decimal_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal_number(SimpleLangParser.Decimal_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#binary_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_number(SimpleLangParser.Binary_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#time_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_literal(SimpleLangParser.Time_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSize(SimpleLangParser.SizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#real_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_number(SimpleLangParser.Real_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#unsigned_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsigned_number(SimpleLangParser.Unsigned_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#decimal_base}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal_base(SimpleLangParser.Decimal_baseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#binary_base}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_base(SimpleLangParser.Binary_baseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#unbased_unsized_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnbased_unsized_literal(SimpleLangParser.Unbased_unsized_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#string_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_literal(SimpleLangParser.String_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(SimpleLangParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#hierarchical_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHierarchical_identifier(SimpleLangParser.Hierarchical_identifierContext ctx);
}