// Generated from /home/medanaee/Documents/Term5/PLC/Phase2/src/main/grammar/SimpleLang.g4 by ANTLR 4.13.1

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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleLangParser}.
 */
public interface SimpleLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#source_text}.
	 * @param ctx the parse tree
	 */
	void enterSource_text(SimpleLangParser.Source_textContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#source_text}.
	 * @param ctx the parse tree
	 */
	void exitSource_text(SimpleLangParser.Source_textContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#component_declaration}.
	 * @param ctx the parse tree
	 */
	void enterComponent_declaration(SimpleLangParser.Component_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#component_declaration}.
	 * @param ctx the parse tree
	 */
	void exitComponent_declaration(SimpleLangParser.Component_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#component_header}.
	 * @param ctx the parse tree
	 */
	void enterComponent_header(SimpleLangParser.Component_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#component_header}.
	 * @param ctx the parse tree
	 */
	void exitComponent_header(SimpleLangParser.Component_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#configuration_header}.
	 * @param ctx the parse tree
	 */
	void enterConfiguration_header(SimpleLangParser.Configuration_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#configuration_header}.
	 * @param ctx the parse tree
	 */
	void exitConfiguration_header(SimpleLangParser.Configuration_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#component_name}.
	 * @param ctx the parse tree
	 */
	void enterComponent_name(SimpleLangParser.Component_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#component_name}.
	 * @param ctx the parse tree
	 */
	void exitComponent_name(SimpleLangParser.Component_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#parameter_port_list}.
	 * @param ctx the parse tree
	 */
	void enterParameter_port_list(SimpleLangParser.Parameter_port_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#parameter_port_list}.
	 * @param ctx the parse tree
	 */
	void exitParameter_port_list(SimpleLangParser.Parameter_port_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#parameter_port_declaration}.
	 * @param ctx the parse tree
	 */
	void enterParameter_port_declaration(SimpleLangParser.Parameter_port_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#parameter_port_declaration}.
	 * @param ctx the parse tree
	 */
	void exitParameter_port_declaration(SimpleLangParser.Parameter_port_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_port_declarations}.
	 * @param ctx the parse tree
	 */
	void enterList_of_port_declarations(SimpleLangParser.List_of_port_declarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_port_declarations}.
	 * @param ctx the parse tree
	 */
	void exitList_of_port_declarations(SimpleLangParser.List_of_port_declarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#port_decl}.
	 * @param ctx the parse tree
	 */
	void enterPort_decl(SimpleLangParser.Port_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#port_decl}.
	 * @param ctx the parse tree
	 */
	void exitPort_decl(SimpleLangParser.Port_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#port_direction}.
	 * @param ctx the parse tree
	 */
	void enterPort_direction(SimpleLangParser.Port_directionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#port_direction}.
	 * @param ctx the parse tree
	 */
	void exitPort_direction(SimpleLangParser.Port_directionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#port_prefix}.
	 * @param ctx the parse tree
	 */
	void enterPort_prefix(SimpleLangParser.Port_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#port_prefix}.
	 * @param ctx the parse tree
	 */
	void exitPort_prefix(SimpleLangParser.Port_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#component_item}.
	 * @param ctx the parse tree
	 */
	void enterComponent_item(SimpleLangParser.Component_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#component_item}.
	 * @param ctx the parse tree
	 */
	void exitComponent_item(SimpleLangParser.Component_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#component_common_item}.
	 * @param ctx the parse tree
	 */
	void enterComponent_common_item(SimpleLangParser.Component_common_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#component_common_item}.
	 * @param ctx the parse tree
	 */
	void exitComponent_common_item(SimpleLangParser.Component_common_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#component_item_declaration}.
	 * @param ctx the parse tree
	 */
	void enterComponent_item_declaration(SimpleLangParser.Component_item_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#component_item_declaration}.
	 * @param ctx the parse tree
	 */
	void exitComponent_item_declaration(SimpleLangParser.Component_item_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#parameter_declaration}.
	 * @param ctx the parse tree
	 */
	void enterParameter_declaration(SimpleLangParser.Parameter_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#parameter_declaration}.
	 * @param ctx the parse tree
	 */
	void exitParameter_declaration(SimpleLangParser.Parameter_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#genvar_declaration}.
	 * @param ctx the parse tree
	 */
	void enterGenvar_declaration(SimpleLangParser.Genvar_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#genvar_declaration}.
	 * @param ctx the parse tree
	 */
	void exitGenvar_declaration(SimpleLangParser.Genvar_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#net_declaration}.
	 * @param ctx the parse tree
	 */
	void enterNet_declaration(SimpleLangParser.Net_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#net_declaration}.
	 * @param ctx the parse tree
	 */
	void exitNet_declaration(SimpleLangParser.Net_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(SimpleLangParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(SimpleLangParser.Data_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#data_type_or_implicit}.
	 * @param ctx the parse tree
	 */
	void enterData_type_or_implicit(SimpleLangParser.Data_type_or_implicitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#data_type_or_implicit}.
	 * @param ctx the parse tree
	 */
	void exitData_type_or_implicit(SimpleLangParser.Data_type_or_implicitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#implicit_data_type}.
	 * @param ctx the parse tree
	 */
	void enterImplicit_data_type(SimpleLangParser.Implicit_data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#implicit_data_type}.
	 * @param ctx the parse tree
	 */
	void exitImplicit_data_type(SimpleLangParser.Implicit_data_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#integer_type}.
	 * @param ctx the parse tree
	 */
	void enterInteger_type(SimpleLangParser.Integer_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#integer_type}.
	 * @param ctx the parse tree
	 */
	void exitInteger_type(SimpleLangParser.Integer_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#integer_atom_type}.
	 * @param ctx the parse tree
	 */
	void enterInteger_atom_type(SimpleLangParser.Integer_atom_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#integer_atom_type}.
	 * @param ctx the parse tree
	 */
	void exitInteger_atom_type(SimpleLangParser.Integer_atom_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#integer_vector_type}.
	 * @param ctx the parse tree
	 */
	void enterInteger_vector_type(SimpleLangParser.Integer_vector_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#integer_vector_type}.
	 * @param ctx the parse tree
	 */
	void exitInteger_vector_type(SimpleLangParser.Integer_vector_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#non_integer_type}.
	 * @param ctx the parse tree
	 */
	void enterNon_integer_type(SimpleLangParser.Non_integer_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#non_integer_type}.
	 * @param ctx the parse tree
	 */
	void exitNon_integer_type(SimpleLangParser.Non_integer_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#net_type}.
	 * @param ctx the parse tree
	 */
	void enterNet_type(SimpleLangParser.Net_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#net_type}.
	 * @param ctx the parse tree
	 */
	void exitNet_type(SimpleLangParser.Net_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#signing}.
	 * @param ctx the parse tree
	 */
	void enterSigning(SimpleLangParser.SigningContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#signing}.
	 * @param ctx the parse tree
	 */
	void exitSigning(SimpleLangParser.SigningContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#simple_type}.
	 * @param ctx the parse tree
	 */
	void enterSimple_type(SimpleLangParser.Simple_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#simple_type}.
	 * @param ctx the parse tree
	 */
	void exitSimple_type(SimpleLangParser.Simple_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_genvar_identifiers}.
	 * @param ctx the parse tree
	 */
	void enterList_of_genvar_identifiers(SimpleLangParser.List_of_genvar_identifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_genvar_identifiers}.
	 * @param ctx the parse tree
	 */
	void exitList_of_genvar_identifiers(SimpleLangParser.List_of_genvar_identifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_net_decl_assignments}.
	 * @param ctx the parse tree
	 */
	void enterList_of_net_decl_assignments(SimpleLangParser.List_of_net_decl_assignmentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_net_decl_assignments}.
	 * @param ctx the parse tree
	 */
	void exitList_of_net_decl_assignments(SimpleLangParser.List_of_net_decl_assignmentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_param_assignments}.
	 * @param ctx the parse tree
	 */
	void enterList_of_param_assignments(SimpleLangParser.List_of_param_assignmentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_param_assignments}.
	 * @param ctx the parse tree
	 */
	void exitList_of_param_assignments(SimpleLangParser.List_of_param_assignmentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#net_decl_assignment}.
	 * @param ctx the parse tree
	 */
	void enterNet_decl_assignment(SimpleLangParser.Net_decl_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#net_decl_assignment}.
	 * @param ctx the parse tree
	 */
	void exitNet_decl_assignment(SimpleLangParser.Net_decl_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void enterParam_assignment(SimpleLangParser.Param_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void exitParam_assignment(SimpleLangParser.Param_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#unpacked_dimension}.
	 * @param ctx the parse tree
	 */
	void enterUnpacked_dimension(SimpleLangParser.Unpacked_dimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#unpacked_dimension}.
	 * @param ctx the parse tree
	 */
	void exitUnpacked_dimension(SimpleLangParser.Unpacked_dimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#packed_dimension}.
	 * @param ctx the parse tree
	 */
	void enterPacked_dimension(SimpleLangParser.Packed_dimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#packed_dimension}.
	 * @param ctx the parse tree
	 */
	void exitPacked_dimension(SimpleLangParser.Packed_dimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#unsized_dimension}.
	 * @param ctx the parse tree
	 */
	void enterUnsized_dimension(SimpleLangParser.Unsized_dimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#unsized_dimension}.
	 * @param ctx the parse tree
	 */
	void exitUnsized_dimension(SimpleLangParser.Unsized_dimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#block_label}.
	 * @param ctx the parse tree
	 */
	void enterBlock_label(SimpleLangParser.Block_labelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#block_label}.
	 * @param ctx the parse tree
	 */
	void exitBlock_label(SimpleLangParser.Block_labelContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#component_program_interface_instantiation}.
	 * @param ctx the parse tree
	 */
	void enterComponent_program_interface_instantiation(SimpleLangParser.Component_program_interface_instantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#component_program_interface_instantiation}.
	 * @param ctx the parse tree
	 */
	void exitComponent_program_interface_instantiation(SimpleLangParser.Component_program_interface_instantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#parameter_value_assignment}.
	 * @param ctx the parse tree
	 */
	void enterParameter_value_assignment(SimpleLangParser.Parameter_value_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#parameter_value_assignment}.
	 * @param ctx the parse tree
	 */
	void exitParameter_value_assignment(SimpleLangParser.Parameter_value_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_parameter_assignments}.
	 * @param ctx the parse tree
	 */
	void enterList_of_parameter_assignments(SimpleLangParser.List_of_parameter_assignmentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_parameter_assignments}.
	 * @param ctx the parse tree
	 */
	void exitList_of_parameter_assignments(SimpleLangParser.List_of_parameter_assignmentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#named_parameter_assignment}.
	 * @param ctx the parse tree
	 */
	void enterNamed_parameter_assignment(SimpleLangParser.Named_parameter_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#named_parameter_assignment}.
	 * @param ctx the parse tree
	 */
	void exitNamed_parameter_assignment(SimpleLangParser.Named_parameter_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#hierarchical_instance}.
	 * @param ctx the parse tree
	 */
	void enterHierarchical_instance(SimpleLangParser.Hierarchical_instanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#hierarchical_instance}.
	 * @param ctx the parse tree
	 */
	void exitHierarchical_instance(SimpleLangParser.Hierarchical_instanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#name_of_instance}.
	 * @param ctx the parse tree
	 */
	void enterName_of_instance(SimpleLangParser.Name_of_instanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#name_of_instance}.
	 * @param ctx the parse tree
	 */
	void exitName_of_instance(SimpleLangParser.Name_of_instanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_port_connections}.
	 * @param ctx the parse tree
	 */
	void enterList_of_port_connections(SimpleLangParser.List_of_port_connectionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_port_connections}.
	 * @param ctx the parse tree
	 */
	void exitList_of_port_connections(SimpleLangParser.List_of_port_connectionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#named_port_connection}.
	 * @param ctx the parse tree
	 */
	void enterNamed_port_connection(SimpleLangParser.Named_port_connectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#named_port_connection}.
	 * @param ctx the parse tree
	 */
	void exitNamed_port_connection(SimpleLangParser.Named_port_connectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#port_assign}.
	 * @param ctx the parse tree
	 */
	void enterPort_assign(SimpleLangParser.Port_assignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#port_assign}.
	 * @param ctx the parse tree
	 */
	void exitPort_assign(SimpleLangParser.Port_assignContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#generate_region}.
	 * @param ctx the parse tree
	 */
	void enterGenerate_region(SimpleLangParser.Generate_regionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#generate_region}.
	 * @param ctx the parse tree
	 */
	void exitGenerate_region(SimpleLangParser.Generate_regionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#loop_generate_construct}.
	 * @param ctx the parse tree
	 */
	void enterLoop_generate_construct(SimpleLangParser.Loop_generate_constructContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#loop_generate_construct}.
	 * @param ctx the parse tree
	 */
	void exitLoop_generate_construct(SimpleLangParser.Loop_generate_constructContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#genvar_initialization}.
	 * @param ctx the parse tree
	 */
	void enterGenvar_initialization(SimpleLangParser.Genvar_initializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#genvar_initialization}.
	 * @param ctx the parse tree
	 */
	void exitGenvar_initialization(SimpleLangParser.Genvar_initializationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#genvar_iteration}.
	 * @param ctx the parse tree
	 */
	void enterGenvar_iteration(SimpleLangParser.Genvar_iterationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#genvar_iteration}.
	 * @param ctx the parse tree
	 */
	void exitGenvar_iteration(SimpleLangParser.Genvar_iterationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#generate_block}.
	 * @param ctx the parse tree
	 */
	void enterGenerate_block(SimpleLangParser.Generate_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#generate_block}.
	 * @param ctx the parse tree
	 */
	void exitGenerate_block(SimpleLangParser.Generate_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#generate_block_label}.
	 * @param ctx the parse tree
	 */
	void enterGenerate_block_label(SimpleLangParser.Generate_block_labelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#generate_block_label}.
	 * @param ctx the parse tree
	 */
	void exitGenerate_block_label(SimpleLangParser.Generate_block_labelContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#generate_block_name}.
	 * @param ctx the parse tree
	 */
	void enterGenerate_block_name(SimpleLangParser.Generate_block_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#generate_block_name}.
	 * @param ctx the parse tree
	 */
	void exitGenerate_block_name(SimpleLangParser.Generate_block_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#generate_item}.
	 * @param ctx the parse tree
	 */
	void enterGenerate_item(SimpleLangParser.Generate_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#generate_item}.
	 * @param ctx the parse tree
	 */
	void exitGenerate_item(SimpleLangParser.Generate_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#continuous_set}.
	 * @param ctx the parse tree
	 */
	void enterContinuous_set(SimpleLangParser.Continuous_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#continuous_set}.
	 * @param ctx the parse tree
	 */
	void exitContinuous_set(SimpleLangParser.Continuous_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#procedural_continuous_set}.
	 * @param ctx the parse tree
	 */
	void enterProcedural_continuous_set(SimpleLangParser.Procedural_continuous_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#procedural_continuous_set}.
	 * @param ctx the parse tree
	 */
	void exitProcedural_continuous_set(SimpleLangParser.Procedural_continuous_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_net_assignments}.
	 * @param ctx the parse tree
	 */
	void enterList_of_net_assignments(SimpleLangParser.List_of_net_assignmentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_net_assignments}.
	 * @param ctx the parse tree
	 */
	void exitList_of_net_assignments(SimpleLangParser.List_of_net_assignmentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#net_assignment}.
	 * @param ctx the parse tree
	 */
	void enterNet_assignment(SimpleLangParser.Net_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#net_assignment}.
	 * @param ctx the parse tree
	 */
	void exitNet_assignment(SimpleLangParser.Net_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#operator_assignment}.
	 * @param ctx the parse tree
	 */
	void enterOperator_assignment(SimpleLangParser.Operator_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#operator_assignment}.
	 * @param ctx the parse tree
	 */
	void exitOperator_assignment(SimpleLangParser.Operator_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#assignment_operator}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_operator(SimpleLangParser.Assignment_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#assignment_operator}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_operator(SimpleLangParser.Assignment_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#nonblocking_assignment}.
	 * @param ctx the parse tree
	 */
	void enterNonblocking_assignment(SimpleLangParser.Nonblocking_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#nonblocking_assignment}.
	 * @param ctx the parse tree
	 */
	void exitNonblocking_assignment(SimpleLangParser.Nonblocking_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void enterVariable_assignment(SimpleLangParser.Variable_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void exitVariable_assignment(SimpleLangParser.Variable_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#statement_or_null}.
	 * @param ctx the parse tree
	 */
	void enterStatement_or_null(SimpleLangParser.Statement_or_nullContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#statement_or_null}.
	 * @param ctx the parse tree
	 */
	void exitStatement_or_null(SimpleLangParser.Statement_or_nullContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SimpleLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SimpleLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#statement_item}.
	 * @param ctx the parse tree
	 */
	void enterStatement_item(SimpleLangParser.Statement_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#statement_item}.
	 * @param ctx the parse tree
	 */
	void exitStatement_item(SimpleLangParser.Statement_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#conditional_statement}.
	 * @param ctx the parse tree
	 */
	void enterConditional_statement(SimpleLangParser.Conditional_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#conditional_statement}.
	 * @param ctx the parse tree
	 */
	void exitConditional_statement(SimpleLangParser.Conditional_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#cond_predicate}.
	 * @param ctx the parse tree
	 */
	void enterCond_predicate(SimpleLangParser.Cond_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#cond_predicate}.
	 * @param ctx the parse tree
	 */
	void exitCond_predicate(SimpleLangParser.Cond_predicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void enterLoop_statement(SimpleLangParser.Loop_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void exitLoop_statement(SimpleLangParser.Loop_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#period_declaration}.
	 * @param ctx the parse tree
	 */
	void enterPeriod_declaration(SimpleLangParser.Period_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#period_declaration}.
	 * @param ctx the parse tree
	 */
	void exitPeriod_declaration(SimpleLangParser.Period_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#subroutine_call_statement}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine_call_statement(SimpleLangParser.Subroutine_call_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#subroutine_call_statement}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine_call_statement(SimpleLangParser.Subroutine_call_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(SimpleLangParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(SimpleLangParser.ConcatenationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_concatenation}.
	 * @param ctx the parse tree
	 */
	void enterConstant_concatenation(SimpleLangParser.Constant_concatenationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_concatenation}.
	 * @param ctx the parse tree
	 */
	void exitConstant_concatenation(SimpleLangParser.Constant_concatenationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void enterArg_list(SimpleLangParser.Arg_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void exitArg_list(SimpleLangParser.Arg_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#subroutine_call}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine_call(SimpleLangParser.Subroutine_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#subroutine_call}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine_call(SimpleLangParser.Subroutine_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#list_of_arguments}.
	 * @param ctx the parse tree
	 */
	void enterList_of_arguments(SimpleLangParser.List_of_argumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#list_of_arguments}.
	 * @param ctx the parse tree
	 */
	void exitList_of_arguments(SimpleLangParser.List_of_argumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#ordered_arg}.
	 * @param ctx the parse tree
	 */
	void enterOrdered_arg(SimpleLangParser.Ordered_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#ordered_arg}.
	 * @param ctx the parse tree
	 */
	void exitOrdered_arg(SimpleLangParser.Ordered_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#named_arg}.
	 * @param ctx the parse tree
	 */
	void enterNamed_arg(SimpleLangParser.Named_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#named_arg}.
	 * @param ctx the parse tree
	 */
	void exitNamed_arg(SimpleLangParser.Named_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#inc_or_dec_expression}.
	 * @param ctx the parse tree
	 */
	void enterInc_or_dec_expression(SimpleLangParser.Inc_or_dec_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#inc_or_dec_expression}.
	 * @param ctx the parse tree
	 */
	void exitInc_or_dec_expression(SimpleLangParser.Inc_or_dec_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_expression}.
	 * @param ctx the parse tree
	 */
	void enterConstant_expression(SimpleLangParser.Constant_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_expression}.
	 * @param ctx the parse tree
	 */
	void exitConstant_expression(SimpleLangParser.Constant_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_param_expression}.
	 * @param ctx the parse tree
	 */
	void enterConstant_param_expression(SimpleLangParser.Constant_param_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_param_expression}.
	 * @param ctx the parse tree
	 */
	void exitConstant_param_expression(SimpleLangParser.Constant_param_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#param_expression}.
	 * @param ctx the parse tree
	 */
	void enterParam_expression(SimpleLangParser.Param_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#param_expression}.
	 * @param ctx the parse tree
	 */
	void exitParam_expression(SimpleLangParser.Param_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_range}.
	 * @param ctx the parse tree
	 */
	void enterConstant_range(SimpleLangParser.Constant_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_range}.
	 * @param ctx the parse tree
	 */
	void exitConstant_range(SimpleLangParser.Constant_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SimpleLangParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SimpleLangParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#part_select_range}.
	 * @param ctx the parse tree
	 */
	void enterPart_select_range(SimpleLangParser.Part_select_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#part_select_range}.
	 * @param ctx the parse tree
	 */
	void exitPart_select_range(SimpleLangParser.Part_select_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#genvar_expression}.
	 * @param ctx the parse tree
	 */
	void enterGenvar_expression(SimpleLangParser.Genvar_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#genvar_expression}.
	 * @param ctx the parse tree
	 */
	void exitGenvar_expression(SimpleLangParser.Genvar_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_primary}.
	 * @param ctx the parse tree
	 */
	void enterConstant_primary(SimpleLangParser.Constant_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_primary}.
	 * @param ctx the parse tree
	 */
	void exitConstant_primary(SimpleLangParser.Constant_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(SimpleLangParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(SimpleLangParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#primary_literal}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_literal(SimpleLangParser.Primary_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#primary_literal}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_literal(SimpleLangParser.Primary_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#bit_select}.
	 * @param ctx the parse tree
	 */
	void enterBit_select(SimpleLangParser.Bit_selectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#bit_select}.
	 * @param ctx the parse tree
	 */
	void exitBit_select(SimpleLangParser.Bit_selectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#select_}.
	 * @param ctx the parse tree
	 */
	void enterSelect_(SimpleLangParser.Select_Context ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#select_}.
	 * @param ctx the parse tree
	 */
	void exitSelect_(SimpleLangParser.Select_Context ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_bit_select}.
	 * @param ctx the parse tree
	 */
	void enterConstant_bit_select(SimpleLangParser.Constant_bit_selectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_bit_select}.
	 * @param ctx the parse tree
	 */
	void exitConstant_bit_select(SimpleLangParser.Constant_bit_selectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_select}.
	 * @param ctx the parse tree
	 */
	void enterConstant_select(SimpleLangParser.Constant_selectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_select}.
	 * @param ctx the parse tree
	 */
	void exitConstant_select(SimpleLangParser.Constant_selectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#net_lvalue}.
	 * @param ctx the parse tree
	 */
	void enterNet_lvalue(SimpleLangParser.Net_lvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#net_lvalue}.
	 * @param ctx the parse tree
	 */
	void exitNet_lvalue(SimpleLangParser.Net_lvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#variable_lvalue}.
	 * @param ctx the parse tree
	 */
	void enterVariable_lvalue(SimpleLangParser.Variable_lvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#variable_lvalue}.
	 * @param ctx the parse tree
	 */
	void exitVariable_lvalue(SimpleLangParser.Variable_lvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(SimpleLangParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(SimpleLangParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#inc_or_dec_operator}.
	 * @param ctx the parse tree
	 */
	void enterInc_or_dec_operator(SimpleLangParser.Inc_or_dec_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#inc_or_dec_operator}.
	 * @param ctx the parse tree
	 */
	void exitInc_or_dec_operator(SimpleLangParser.Inc_or_dec_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(SimpleLangParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(SimpleLangParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#integral_number}.
	 * @param ctx the parse tree
	 */
	void enterIntegral_number(SimpleLangParser.Integral_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#integral_number}.
	 * @param ctx the parse tree
	 */
	void exitIntegral_number(SimpleLangParser.Integral_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#decimal_number}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_number(SimpleLangParser.Decimal_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#decimal_number}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_number(SimpleLangParser.Decimal_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#constant_mintypmax_expression}.
	 * @param ctx the parse tree
	 */
	void enterConstant_mintypmax_expression(SimpleLangParser.Constant_mintypmax_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#constant_mintypmax_expression}.
	 * @param ctx the parse tree
	 */
	void exitConstant_mintypmax_expression(SimpleLangParser.Constant_mintypmax_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#mintypmax_expression}.
	 * @param ctx the parse tree
	 */
	void enterMintypmax_expression(SimpleLangParser.Mintypmax_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#mintypmax_expression}.
	 * @param ctx the parse tree
	 */
	void exitMintypmax_expression(SimpleLangParser.Mintypmax_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#binary_number}.
	 * @param ctx the parse tree
	 */
	void enterBinary_number(SimpleLangParser.Binary_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#binary_number}.
	 * @param ctx the parse tree
	 */
	void exitBinary_number(SimpleLangParser.Binary_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#time_literal}.
	 * @param ctx the parse tree
	 */
	void enterTime_literal(SimpleLangParser.Time_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#time_literal}.
	 * @param ctx the parse tree
	 */
	void exitTime_literal(SimpleLangParser.Time_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#size}.
	 * @param ctx the parse tree
	 */
	void enterSize(SimpleLangParser.SizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#size}.
	 * @param ctx the parse tree
	 */
	void exitSize(SimpleLangParser.SizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#real_number}.
	 * @param ctx the parse tree
	 */
	void enterReal_number(SimpleLangParser.Real_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#real_number}.
	 * @param ctx the parse tree
	 */
	void exitReal_number(SimpleLangParser.Real_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#unsigned_number}.
	 * @param ctx the parse tree
	 */
	void enterUnsigned_number(SimpleLangParser.Unsigned_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#unsigned_number}.
	 * @param ctx the parse tree
	 */
	void exitUnsigned_number(SimpleLangParser.Unsigned_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#decimal_base}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_base(SimpleLangParser.Decimal_baseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#decimal_base}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_base(SimpleLangParser.Decimal_baseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#binary_base}.
	 * @param ctx the parse tree
	 */
	void enterBinary_base(SimpleLangParser.Binary_baseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#binary_base}.
	 * @param ctx the parse tree
	 */
	void exitBinary_base(SimpleLangParser.Binary_baseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#unbased_unsized_literal}.
	 * @param ctx the parse tree
	 */
	void enterUnbased_unsized_literal(SimpleLangParser.Unbased_unsized_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#unbased_unsized_literal}.
	 * @param ctx the parse tree
	 */
	void exitUnbased_unsized_literal(SimpleLangParser.Unbased_unsized_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void enterString_literal(SimpleLangParser.String_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void exitString_literal(SimpleLangParser.String_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(SimpleLangParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(SimpleLangParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#hierarchical_identifier}.
	 * @param ctx the parse tree
	 */
	void enterHierarchical_identifier(SimpleLangParser.Hierarchical_identifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#hierarchical_identifier}.
	 * @param ctx the parse tree
	 */
	void exitHierarchical_identifier(SimpleLangParser.Hierarchical_identifierContext ctx);
}