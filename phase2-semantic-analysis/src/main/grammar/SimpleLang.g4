grammar SimpleLang;
@header {
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
}

source_text
	returns[SourceText SourceTextRet]:
	{$SourceTextRet= new SourceText();} (
		cd = component_declaration {$SourceTextRet.addComponentDeclaration($cd.ComponentDeclarationRet);
			}
	)* EOF;

component_declaration
	returns[ComponentDeclaration ComponentDeclarationRet]:
	{$ComponentDeclarationRet = new ItemHeaderDeclaration();} ch = component_header {
          $ComponentDeclarationRet.setStartLine($ch.ComponentHeaderRet.getLine());
          ((ItemHeaderDeclaration)$ComponentDeclarationRet).setComponentHeader($ch.ComponentHeaderRet);
      } (
		ci = component_item {
          ((ItemHeaderDeclaration)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
      }
	)* (
		tx = 'endcomponenttype' {
          $ComponentDeclarationRet.setEndLine($tx.line);
      }
	) (
		cn = component_name {
          ((ItemHeaderDeclaration)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);
      }
	)?
	| {$ComponentDeclarationRet = new IdentifierComponent();} (
		ct1 = 'componenttype' {
          $ComponentDeclarationRet.setStartLine($ct1.line);
      }
	) (
		i = identifier {
          $ComponentDeclarationRet.setStartLine($e.line);
          ((IdentifierComponent)$ComponentDeclarationRet).setIdentifier($i.IdentifierRet);
      }
	) '(' '.*' ')' ';' (
		ci = component_item {
          ((IdentifierComponent)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
      }
	)* (
		e = 'endcomponenttype' {
          $ComponentDeclarationRet.setEndLine($e.line);
      }
	) (
		cn = component_name {
          ((IdentifierComponent)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);
      }
	)?
	| {$ComponentDeclarationRet = new ConfigHeaderDeclaration();} ch2 = configuration_header {
          $ComponentDeclarationRet.setStartLine($ch2.ConfigurationHeaderRet.getLine());
          ((ConfigHeaderDeclaration)$ComponentDeclarationRet).setConfigurationHeader($ch2.ConfigurationHeaderRet);
      } (
		ci = component_item {
          ((ConfigHeaderDeclaration)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
      }
	)* (
		ec = 'endconfiguration' {
          $ComponentDeclarationRet.setEndLine($ec.line);
      }
	) (
		cn = component_name {
          ((ConfigHeaderDeclaration)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);
      }
	)?
	| {$ComponentDeclarationRet = new ConfigDeclaration();} c = 'configuration' {
          $ComponentDeclarationRet.setStartLine($c.line);
      } (
		i = identifier {
          ((ConfigDeclaration)$ComponentDeclarationRet).setIdentifier($i.IdentifierRet);
      }
	) '(' '.*' ')' ';' (
		ci = component_item {
          ((ConfigDeclaration)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
      }
	)* (
		ec = 'endconfiguration' {
          $ComponentDeclarationRet.setEndLine($ec.line);
      }
	) (
		cn = component_name {
          ((ConfigDeclaration)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);
      }
	)?;

component_header
	returns[ComponentHeader ComponentHeaderRet]:
	{ $ComponentHeaderRet = new ComponentHeader(); } ct = 'componenttype' id = identifier {
          $ComponentHeaderRet.setIdentifier($id.IdentifierRet);
          $ComponentHeaderRet.setLine($ct.line);
      } (
		pp = parameter_port_list {
          $ComponentHeaderRet.setParameterPortList($pp.ParameterPortListRet);
      }
	)? (
		lp = list_of_port_declarations {
          $ComponentHeaderRet.setListOfPortDeclarations($lp.ListOfPortDeclarationsRet);
      }
	)? ';';

configuration_header
	returns[ConfigurationHeader ConfigurationHeaderRet]:
	{ $ConfigurationHeaderRet = new ConfigurationHeader(); } c = 'configuration' id = identifier {
          $ConfigurationHeaderRet.setIdentifier($id.IdentifierRet);
          $ConfigurationHeaderRet.setLine($c.line);
      } (
		pp = parameter_port_list {
          $ConfigurationHeaderRet.setParameterPortList($pp.ParameterPortListRet);
      }
	)? (
		lp = list_of_port_declarations {
          $ConfigurationHeaderRet.setListOfPortDeclarations($lp.ListOfPortDeclarationsRet);
      }
	)? ';';

component_name
	returns[Identifier ComponentNameRet]:
	':' (i = identifier {$ComponentNameRet=$i.IdentifierRet;});

parameter_port_list
	returns[ParameterPortList ParameterPortListRet]:
	{ $ParameterPortListRet = new ListBased(); } h = '#' '(' la = list_of_param_assignments {
              ((ListBased)$ParameterPortListRet).setListOfParamAssignments($la.ListOfParamAssignmentsRet);
          } (
		',' pp = parameter_port_declaration {
                ((ListBased)$ParameterPortListRet).addParameterPortDeclaration($pp.ParameterPortDeclarationRet);
            }
	)* ')' {
              $ParameterPortListRet.setLine($h.line);
          }
	| { $ParameterPortListRet = new DeclarationBased(); } '#' '(' pp1 = parameter_port_declaration {
              ((DeclarationBased)$ParameterPortListRet).addParameterPortDeclaration($pp1.ParameterPortDeclarationRet);
          } (
		',' pp2 = parameter_port_declaration {
                ((DeclarationBased)$ParameterPortListRet).addParameterPortDeclaration($pp2.ParameterPortDeclarationRet);
            }
	)* h = ')' {
              $ParameterPortListRet.setLine($h.line);
          }
	| { $ParameterPortListRet = new EmptyBased(); } h = '#' '(' ')' {
                $ParameterPortListRet.setLine($h.line);
            };

parameter_port_declaration
	returns[ParameterPortDeclaration ParameterPortDeclarationRet]:
	pd = parameter_declaration {$ParameterPortDeclarationRet=$pd.ParameterDeclarationRet;}
	| { $ParameterPortDeclarationRet = new ParameterAssignmentBased(); } dt = data_type {((ParameterAssignmentBased)$ParameterPortDeclarationRet).setDataType($dt.DataTypeRet);
		} la = list_of_param_assignments {((ParameterAssignmentBased)$ParameterPortDeclarationRet).setListOfParamAssignments($la.ListOfParamAssignmentsRet);
       $ParameterPortDeclarationRet.setLine($dt.DataTypeRet.getLine());};

list_of_port_declarations
	returns[List<PortDecl> ListOfPortDeclarationsRet]
	@init { $ListOfPortDeclarationsRet = new ArrayList();}:
	lp = '(' pd1 = port_decl {$ListOfPortDeclarationsRet.add($pd1.PortDeclRet);} (
		',' pd2 = port_decl {$ListOfPortDeclarationsRet.add($pd2.PortDeclRet);}
	)* ')'
	| lp = '(' ')';

port_decl
	returns[PortDecl PortDeclRet]:
	{$PortDeclRet = new DataTypePortDecl();} pp = port_prefix (
		pd = port_direction {((DataTypePortDecl)$PortDeclRet).setPortDirection($pd.PortDirectionRet);
			}
	)? (
		dt = data_type {((DataTypePortDecl)$PortDeclRet).setDataType($dt.DataTypeRet);}
	)? id = identifier {((DataTypePortDecl)$PortDeclRet).setIdentifier($id.IdentifierRet);} (
		eq = '=' ce = constant_expression {((DataTypePortDecl)$PortDeclRet).setConstantExpression($ce.ConstantExpressionRet);
			}
	)?
	| {$PortDeclRet = new ImplicitDataPortDecl();} pp = port_prefix (
		pd = port_direction {((ImplicitDataPortDecl)$PortDeclRet).setPortDirection($pd.PortDirectionRet);
			}
	)? idt = implicit_data_type {((ImplicitDataPortDecl)$PortDeclRet).setImplicitDataType($idt.ImplicitDataTypeRet);
		} id = identifier {((ImplicitDataPortDecl)$PortDeclRet).setIdentifier($id.IdentifierRet);} (
		ud = unpacked_dimension {((ImplicitDataPortDecl)$PortDeclRet).addUnpackedDimension($ud.UnpackedDimensionRet);
			}
	)* (
		eq = '=' ce = constant_expression {((ImplicitDataPortDecl)$PortDeclRet).setConstantExpression($ce.ConstantExpressionRet);
			}
	)?
	| {$PortDeclRet = new NetTypePortDecl();} pp = port_prefix (
		pd = port_direction {((NetTypePortDecl)$PortDeclRet).setPortDirection($pd.PortDirectionRet);
			}
	)? nt = net_type (
		dti = data_type_or_implicit {((NetTypePortDecl)$PortDeclRet).setDataTypeOrImplicit($dti.DataTypeOrImplicitRet);
			}
	)? id = identifier {((NetTypePortDecl)$PortDeclRet).setIdentifier($id.IdentifierRet);} (
		ud = unpacked_dimension {((NetTypePortDecl)$PortDeclRet).addUnpackedDimension($ud.UnpackedDimensionRet);
			}
	)* (
		eq = '=' ce = constant_expression {((NetTypePortDecl)$PortDeclRet).setConstantExpression($ce.ConstantExpressionRet);
			}
	)?;

port_direction
	returns[PortDirection PortDirectionRet]:
	i = 'in' {$PortDirectionRet= new InPortDirection($i.text,$i.line);}
	| o = 'out' {$PortDirectionRet = new OutPortDirection($o.text,$o.line);};
port_prefix: p = 'port';

component_item
	returns[ComponentItem ComponentItemRet]:
	gr = generate_region {$ComponentItemRet = $gr.GenerateRegionRet;}
	| cc = component_common_item {$ComponentItemRet = $cc.ComponentCommonItemRet;}
	| cd = component_declaration {$ComponentItemRet = $cd.ComponentDeclarationRet;}
	| ls = loop_statement {$ComponentItemRet = $ls.LoopStatementRet;};
component_common_item
	returns[ComponentCommonItem ComponentCommonItemRet]:
	gr = component_item_declaration { $ComponentCommonItemRet = $gr.ComponentItemDeclarationRet; }
	| cc = component_program_interface_instantiation { $ComponentCommonItemRet = $cc.ComponentProgramInterfaceInstantiationRet; 
		}
	| cd = continuous_set { $ComponentCommonItemRet = $cd.ContinuousSetRet; }
	| ls = loop_generate_construct { $ComponentCommonItemRet = $ls.LoopGenerateConstructRet; };
component_item_declaration
	returns[ComponentItemDeclaration ComponentItemDeclarationRet]:
	nd = net_declaration { $ComponentItemDeclarationRet = $nd.NetDeclarationRet; }
	| pd = parameter_declaration ';' { $ComponentItemDeclarationRet = $pd.ParameterDeclarationRet; }
	| gd = genvar_declaration { $ComponentItemDeclarationRet = $gd.GenvarDeclarationRet; }
	| ';' { $ComponentItemDeclarationRet = new ComponentItemDeclarationEmpty(); };
parameter_declaration
	returns[ParameterDeclaration ParameterDeclarationRet]:
	p = 'parameter' {$ParameterDeclarationRet = new ParameterDeclaration();
       $ParameterDeclarationRet.setLine($p.line);} (
		dti = data_type_or_implicit {$ParameterDeclarationRet.setDataTypeOrImplicit($dti.DataTypeOrImplicitRet);
			}
	)? la = list_of_param_assignments {$ParameterDeclarationRet.setListOfParamAssignments($la.ListOfParamAssignmentsRet);
		};
genvar_declaration
	returns[GenvarDeclaration GenvarDeclarationRet]:
	{ $GenvarDeclarationRet = new GenvarDeclaration(); } i = 'index' {$GenvarDeclarationRet.setLine($i.line);
		} l = list_of_genvar_identifiers {$GenvarDeclarationRet.setListOfGenvarIdentifiers($l.ListOfGenvarIdentifiersRet);
		} s = ';';
net_declaration
	returns[NetDeclaration NetDeclarationRet]:
	{$NetDeclarationRet = new NetTypeNetDeclaration();} net_type (
		dti = data_type_or_implicit {((NetTypeNetDeclaration)$NetDeclarationRet).setDataTypeOrImplicit($dti.DataTypeOrImplicitRet);
			}
	)? lna = list_of_net_decl_assignments {((NetTypeNetDeclaration)$NetDeclarationRet)
        .setListOfNetDeclAssignments($lna.ListOfNetDeclAssignmentsRet);} ';'
	| {$NetDeclarationRet = new IdentifierNetDeclaration();} id = identifier {((IdentifierNetDeclaration)$NetDeclarationRet)
              .setIdentifier($id.IdentifierRet);
        $NetDeclarationRet.setLine($id.IdentifierRet.getLine());} lna = list_of_net_decl_assignments
		{((IdentifierNetDeclaration)$NetDeclarationRet)
          .setListOfNetDeclAssignments($lna.ListOfNetDeclAssignmentsRet);} ';';

data_type
	returns[DataType DataTypeRet]:
	it = integer_type {$DataTypeRet = new IntegerDataType();
       ((IntegerDataType)$DataTypeRet).setIntegerType($it.IntegerTypeRet);} (
		sg = signing {((IntegerDataType)$DataTypeRet).setSigning($sg.SigningRet);}
	)? (
		pd = packed_dimension {((IntegerDataType)$DataTypeRet).addPackedDimension($pd.PackedDimensionRet);
			}
	)*
	| nt = non_integer_type {$DataTypeRet = new NonIntegerDataType($nt.NonIntegerTypeRet);}
	| s = 'string' {   $DataTypeRet = new StringDataType();
          $DataTypeRet.setLine($s.line);}
	| id = identifier {   $DataTypeRet = new IdentifierDataType();
          ((IdentifierDataType)$DataTypeRet).setIdentifier($id.IdentifierRet);
          $DataTypeRet.setLine($id.IdentifierRet.getLine());} (
		pd2 = packed_dimension { ((IdentifierDataType)$DataTypeRet).addPackedDimension($pd2.PackedDimensionRet);
			}
	)+;

data_type_or_implicit
	returns[DataTypeOrImplicit DataTypeOrImplicitRet]:
	dt = data_type {$DataTypeOrImplicitRet = $dt.DataTypeRet;}
	| idt = implicit_data_type {$DataTypeOrImplicitRet = $idt.ImplicitDataTypeRet;};
implicit_data_type
	returns[ImplicitDataType ImplicitDataTypeRet]
	@init {$ImplicitDataTypeRet = new ImplicitDataType();}: (
		pd2 = packed_dimension {$ImplicitDataTypeRet.addPackedDimension($pd2.PackedDimensionRet);}
	)+
	| s = signing {$ImplicitDataTypeRet.setSigning($s.SigningRet);} (
		pd3 = packed_dimension {$ImplicitDataTypeRet.addPackedDimension($pd3.PackedDimensionRet);}
	)*;
integer_type
	returns[IntegerType IntegerTypeRet]:
	iv = integer_vector_type {$IntegerTypeRet= $iv.IntegerTypeRet;}
	| ia = integer_atom_type {$IntegerTypeRet = $ia.IntegerTypeRet;};
integer_atom_type
	returns[IntegerType IntegerTypeRet]:
	b = 'byte' {$IntegerTypeRet = IntegerType.BYTE;}
	| s = 'shortint' {$IntegerTypeRet = IntegerType.SHORT_INT;}
	| i = 'int' {$IntegerTypeRet = IntegerType.INT;}
	| l = 'longint' {$IntegerTypeRet = IntegerType.LONG_INT;}
	| in = 'integer' {$IntegerTypeRet = IntegerType.INTEGER;}
	| t = 'time' {$IntegerTypeRet = IntegerType.TIME;};
integer_vector_type
	returns[IntegerType IntegerTypeRet]:
	b = 'bit' {$IntegerTypeRet = IntegerType.BIT;}
	| d = 'data' {$IntegerTypeRet = IntegerType.DATA;};
non_integer_type
	returns[NonIntegerType NonIntegerTypeRet]:
	s = 'shortreal' {$NonIntegerTypeRet = NonIntegerType.SHORT_REAL;}
	| r = 'real' {$NonIntegerTypeRet = NonIntegerType.REAL;};
net_type: d = 'data';
signing
	returns[Signing SigningRet]:
	s = 'signed' {$SigningRet = Signing.SIGNED;}
	| u = 'unsigned' {$SigningRet = Signing.UNSIGNED;};
simple_type
	returns[SimpleType SimpleTypeRet]
	@init {$SimpleTypeRet = new SimpleType();}:
	it = integer_type {$SimpleTypeRet.setIntegerType($it.IntegerTypeRet);}
	| nt = non_integer_type {$SimpleTypeRet.setNonintegerType($nt.NonIntegerTypeRet);}
	| id = identifier {$SimpleTypeRet.setIdentifier($id.IdentifierRet);};

list_of_genvar_identifiers
	returns[List<Identifier>  ListOfGenvarIdentifiersRet]:
	{$ListOfGenvarIdentifiersRet= new ArrayList();} (
		i1 = identifier {$ListOfGenvarIdentifiersRet.add($i1.IdentifierRet);}
	) (
		',' (
			i2 = identifier {$ListOfGenvarIdentifiersRet.add($i2.IdentifierRet);}
		)
	)*;
list_of_net_decl_assignments
	returns[List<NetDeclAssignment> ListOfNetDeclAssignmentsRet]:
	{ $ListOfNetDeclAssignmentsRet = new ArrayList(); } nda1 = net_decl_assignment {$ListOfNetDeclAssignmentsRet.add($nda1.NetDeclAssignmentRet);
		} (
		',' nda2 = net_decl_assignment {$ListOfNetDeclAssignmentsRet.add($nda2.NetDeclAssignmentRet);
			}
	)*;

list_of_param_assignments
	returns[List<ParamAssignment> ListOfParamAssignmentsRet]:
	{ $ListOfParamAssignmentsRet = new ArrayList<>(); } pa1 = param_assignment {$ListOfParamAssignmentsRet.add($pa1.ParamAssignmentRet);
		} (
		',' pa2 = param_assignment {$ListOfParamAssignmentsRet.add($pa2.ParamAssignmentRet);}
	)*;

net_decl_assignment
	returns[NetDeclAssignment NetDeclAssignmentRet]:
	{ $NetDeclAssignmentRet = new NetDeclAssignment(); } id = identifier {   $NetDeclAssignmentRet.setIdentifier($id.IdentifierRet);
          $NetDeclAssignmentRet.setLine($id.IdentifierRet.getLine());  } (
		ud = unpacked_dimension { $NetDeclAssignmentRet.addUnpackedDimension($ud.UnpackedDimensionRet);
			}
	)* (
		eq = '=' exp = expression {$NetDeclAssignmentRet.setExpression($exp.ExpressionRet);}
	)?;

param_assignment
	returns[ParamAssignment ParamAssignmentRet]:
	{ $ParamAssignmentRet = new ParamAssignment(); } id = identifier {   $ParamAssignmentRet.setIdentifier($id.IdentifierRet);
          $ParamAssignmentRet.setLine($id.IdentifierRet.getLine()); } (
		ud = unpacked_dimension {$ParamAssignmentRet.addUnpackedDimension($ud.UnpackedDimensionRet);
			}
	)* ('=' constant_param_expression {})?;

unpacked_dimension
	returns[UnpackedDimension UnpackedDimensionRet]:
	lb = '[' cr = constant_range rb = ']' {
          $UnpackedDimensionRet = new RangeUnpackedDimension();
          ((RangeUnpackedDimension)$UnpackedDimensionRet).setConstantRange($cr.ConstantRangeRet);
           $UnpackedDimensionRet.setStartLine($lb.line);
            $UnpackedDimensionRet.setEndLine($rb.line);
      }
	| lb = '[' ce = constant_expression rb = ']' {
          $UnpackedDimensionRet = new SingleUnpackedDimension();
          ((SingleUnpackedDimension)$UnpackedDimensionRet).setConstantExpression($ce.ConstantExpressionRet);
          $UnpackedDimensionRet.setStartLine($lb.line);
          $UnpackedDimensionRet.setEndLine($rb.line);
      };
packed_dimension
	returns[PackedDimension PackedDimensionRet]:
	lb = '[' cr = constant_range rb = ']' {
          $PackedDimensionRet = new RangePackedDimension();
          ((RangePackedDimension)$PackedDimensionRet).setConstantRange($cr.ConstantRangeRet);
          $PackedDimensionRet.setStartLine($lb.line);
          $PackedDimensionRet.setEndLine($rb.line);
      }
	| ud = unsized_dimension {
          $PackedDimensionRet = $ud.UnsizedDimensionRet;
      };

unsized_dimension
	returns[UnsizedDimension UnsizedDimensionRet]:
	lb = '[' rb = ']' {
          $UnsizedDimensionRet = new UnsizedDimension();
          $UnsizedDimensionRet.setStartLine($lb.line);
          $UnsizedDimensionRet.setEndLine($rb.line);
      };

block_label
	returns[BlockLabel BlockLabelRet]:
	{ $BlockLabelRet = new BlockLabel(); } id = identifier ':' {
          $BlockLabelRet.setIdentifier($id.IdentifierRet);
          $BlockLabelRet.setLine($id.IdentifierRet.getLine());
      };
component_program_interface_instantiation
	returns[ComponentProgramInterfaceInstantiation ComponentProgramInterfaceInstantiationRet]:
	{ $ComponentProgramInterfaceInstantiationRet = new ComponentProgramInterfaceInstantiation(); }
		id = identifier {
          $ComponentProgramInterfaceInstantiationRet.setIdentifier($id.IdentifierRet);
          $ComponentProgramInterfaceInstantiationRet.setLine($id.IdentifierRet.getLine());
      } (
		pva = parameter_value_assignment {
          $ComponentProgramInterfaceInstantiationRet.setParameterValueAssignment($pva.ParameterValueAssignmentRet);
      }
	)? hi1 = hierarchical_instance {
          $ComponentProgramInterfaceInstantiationRet.addHierarchicalInstance($hi1.HierarchicalInstanceRet);
      } (
		',' hi2 = hierarchical_instance {
          $ComponentProgramInterfaceInstantiationRet.addHierarchicalInstance($hi2.HierarchicalInstanceRet);
      }
	)* s = ';' {$ComponentProgramInterfaceInstantiationRet.setLine($s.line);};
parameter_value_assignment
	returns[ParameterValueAssignment ParameterValueAssignmentRet]:
	{ $ParameterValueAssignmentRet = new ParameterValueAssignment(); } h = '#' '(' lpa =
		list_of_parameter_assignments? ')' {
          $ParameterValueAssignmentRet.setLine($h.line);

              $ParameterValueAssignmentRet.setListOfParameterAssignments($lpa.ListOfParameterAssignmentsRet);

      };
list_of_parameter_assignments
	returns[ListOfParameterAssignments ListOfParameterAssignmentsRet]:
	{ $ListOfParameterAssignmentsRet = new UnnamedListOfParameterAssignments(); } pe1 =
		param_expression {
          ((UnnamedListOfParameterAssignments)$ListOfParameterAssignmentsRet)
              .addParamExpression($pe1.ParamExpressionRet);
          $ListOfParameterAssignmentsRet.setLine($pe1.ParamExpressionRet.getLine());
      } (
		',' pe2 = param_expression {
          ((UnnamedListOfParameterAssignments)$ListOfParameterAssignmentsRet)
              .addParamExpression($pe2.ParamExpressionRet);
      }
	)*
	| { $ListOfParameterAssignmentsRet = new NamedListOfParameterAssignments(); } n1 =
		named_parameter_assignment {
          ((NamedListOfParameterAssignments)$ListOfParameterAssignmentsRet)
              .addNamedParameterAssignment($n1.NamedParameterAssignmentRet);
          $ListOfParameterAssignmentsRet.setLine($n1.NamedParameterAssignmentRet.getLine());
      } (
		',' n2 = named_parameter_assignment {
          ((NamedListOfParameterAssignments)$ListOfParameterAssignmentsRet)
              .addNamedParameterAssignment($n2.NamedParameterAssignmentRet);
      }
	)*;
named_parameter_assignment
	returns[NamedParameterAssignment NamedParameterAssignmentRet]:
	{ $NamedParameterAssignmentRet = new NamedParameterAssignment();} d = '.' id = identifier {   $NamedParameterAssignmentRet.setIdentifier($id.IdentifierRet);
		} '(' (
		pe = param_expression {

              $NamedParameterAssignmentRet.setParamExpression($pe.ParamExpressionRet);
    }
	)? ')' {
          $NamedParameterAssignmentRet.setLine($d.line);
      };
hierarchical_instance
	returns[HierarchicalInstance HierarchicalInstanceRet]:
	{ $HierarchicalInstanceRet = new HierarchicalInstance(); } noi = name_of_instance {
          $HierarchicalInstanceRet.setNameOfInstance($noi.NameOfInstanceRet);
          $HierarchicalInstanceRet.setLine($noi.NameOfInstanceRet.getLine());
      } lp = '(' lpc = list_of_port_connections rp = ')' {
          $HierarchicalInstanceRet.setListOfPortConnections($lpc.ListOfPortConnectionsRet);
          $HierarchicalInstanceRet.setStartLine($lp.line);
          $HierarchicalInstanceRet.setEndLine($rp.line);
      };
name_of_instance
	returns[NameOfInstance NameOfInstanceRet]:
	id = identifier {
          $NameOfInstanceRet = new NameOfInstance();
          $NameOfInstanceRet.setIdentifier($id.IdentifierRet);
          $NameOfInstanceRet.setLine($id.IdentifierRet.getLine());
      } (
		ud = unpacked_dimension {
          $NameOfInstanceRet.addUnpackedDimension($ud.UnpackedDimensionRet);
      }
	)*;
list_of_port_connections
	returns[List<NamedPortConnection> ListOfPortConnectionsRet]:
	{ $ListOfPortConnectionsRet = new ArrayList(); } npc1 = named_port_connection {$ListOfPortConnectionsRet.add($npc1.NamedPortConnectionRet);
		} (
		',' npc2 = named_port_connection {$ListOfPortConnectionsRet.add($npc2.NamedPortConnectionRet);
			}
	)*;
named_port_connection
	returns[NamedPortConnection NamedPortConnectionRet]:
	{$NamedPortConnectionRet = new DotNamedPortConnection();} d = '.' id = identifier {  ((DotNamedPortConnection)$NamedPortConnectionRet).setIdentifier($id.IdentifierRet);
         $NamedPortConnectionRet.setLine($d.line);} (
		pa = port_assign {((DotNamedPortConnection)$NamedPortConnectionRet).setPortAssign($pa.PortAssignRet);
			}
	)?
	| {$NamedPortConnectionRet = new WildcardNamedPortConnection();} dtas = '.*' {$NamedPortConnectionRet.setLine($dtas.line);
		};
port_assign
	returns[PortAssign PortAssignRet]:
	{
          $PortAssignRet = new PortAssign();
      } lp = '(' {
          $PortAssignRet.setStartLine($lp.line);
      } (
		e = expression {
          $PortAssignRet.setExpression($e.ExpressionRet);
      }
	)? rp = ')' {
          $PortAssignRet.setEndLine($rp.line);
      };

generate_region
	returns[GenerateRegion GenerateRegionRet]:
	{$GenerateRegionRet=new GenerateRegion();} e1 = 'expand' (
		gi = generate_item {$GenerateRegionRet.addGenerateItem($gi.GenerateItemRet);}
	)* e2 = 'endexpand' {
      $GenerateRegionRet.setStartLine($e1.line);
       $GenerateRegionRet.setEndLine($e2.line);
    };

loop_generate_construct
	returns[LoopGenerateConstruct LoopGenerateConstructRet]:
	f = 'for' '(' gi1 = genvar_initialization ';' ge = genvar_expression ';' gi2 = genvar_iteration
		p = ')' gb = generate_block {$LoopGenerateConstructRet=new  LoopGenerateConstruct($gi1.GenvarInitializationRet,
                                        $ge.GenvarExpressionRet,
                                        $gi2.GenvarIterationRet,
                                         $gb.GenerateBlockRet,$f.line);};
genvar_initialization
	returns[GenvarInitialization GenvarInitializationRet]:
	{ $GenvarInitializationRet = new GenvarInitialization(); } (
		'index' {
           $GenvarInitializationRet.setHasIndexKeyword(true);
       }
	)? id = identifier {
          $GenvarInitializationRet.setIdentifier($id.IdentifierRet);
          $GenvarInitializationRet.setLine($id.IdentifierRet.getLine());
      } '=' ce = constant_expression {
          $GenvarInitializationRet.setConstantExpression($ce.ConstantExpressionRet);
      };
genvar_iteration
	returns[GenvarIteration GenvarIterationRet]:
	{
          $GenvarIterationRet = new AssignGenvarIteration();
      } id = identifier {
          ((AssignGenvarIteration)$GenvarIterationRet).setIdentifier($id.IdentifierRet);
          $GenvarIterationRet.setLine($id.IdentifierRet.getLine());
      } ao = assignment_operator {
          ((AssignGenvarIteration)$GenvarIterationRet).setAssignmentOperator($ao.AssignmentOperatorRet);
      } ge = genvar_expression {
          ((AssignGenvarIteration)$GenvarIterationRet).setGenvarExpression($ge.GenvarExpressionRet);
      }
	| {
          $GenvarIterationRet = new PreIncDecGenvarIteration();
      } io = inc_or_dec_operator id = identifier {
          ((PreIncDecGenvarIteration)$GenvarIterationRet).setOperator($io.IncOrDecOperatorRet);
          ((PreIncDecGenvarIteration)$GenvarIterationRet).setIdentifier($id.IdentifierRet);
      }
	| {
          $GenvarIterationRet = new PostIncDecGenvarIteration();
      } id = identifier {
          ((PostIncDecGenvarIteration)$GenvarIterationRet).setIdentifier($id.IdentifierRet);
          $GenvarIterationRet.setLine($id.IdentifierRet.getLine());
      } io = inc_or_dec_operator {
          ((PostIncDecGenvarIteration)$GenvarIterationRet).setOperator($io.IncOrDecOperatorRet);
      };
generate_block
	returns[GenerateBlock GenerateBlockRet]:
	gi = generate_item {
          $GenerateBlockRet = $gi.GenerateItemRet;
      }
	| {
          $GenerateBlockRet = new DoGenerateBlock();
      } (
		gbl = generate_block_label {
            ((DoGenerateBlock)$GenerateBlockRet).setGenerateBlockLabel($gbl.GenerateBlockLabelRet);
        }
	)? d = 'do' {
          ((DoGenerateBlock)$GenerateBlockRet).setStartLine($d.line);
      } (
		gbn1 = generate_block_name {
            ((DoGenerateBlock)$GenerateBlockRet).setBeginGenerateBlockName($gbn1.GenerateBlockNameRet);
        }
	)? (
		gi2 = generate_item {
            ((DoGenerateBlock)$GenerateBlockRet).addGenerateItem($gi2.GenerateItemRet);
        }
	)* e = 'enddo' {
          ((DoGenerateBlock)$GenerateBlockRet).setEndLine($e.line);
      } (
		gbn2 = generate_block_name {
            ((DoGenerateBlock)$GenerateBlockRet).setEndGenerateBlockName($gbn2.GenerateBlockNameRet);
        }
	)?;
generate_block_label
	returns[GenerateBlockLabel GenerateBlockLabelRet]:
	id = identifier c = ':' {
          $GenerateBlockLabelRet = new GenerateBlockLabel();
          $GenerateBlockLabelRet.setIdentifier($id.IdentifierRet);
          $GenerateBlockLabelRet.setLine($c.line);
      };

generate_block_name
	returns[GenerateBlockName GenerateBlockNameRet]:
	c = ':' id = identifier {
          $GenerateBlockNameRet = new GenerateBlockName();
          $GenerateBlockNameRet.setIdentifier($id.IdentifierRet);
          $GenerateBlockNameRet.setLine($c.line);
      };
generate_item
	returns[GenerateItem GenerateItemRet]:
	nd = net_declaration { $GenerateItemRet = $nd.NetDeclarationRet; }
	| pd = parameter_declaration ';'
	| gd = genvar_declaration { $GenerateItemRet = $gd.GenvarDeclarationRet; }
	| cpii = component_program_interface_instantiation { $GenerateItemRet = $cpii.ComponentProgramInterfaceInstantiationRet; 
		}
	| cs = continuous_set { $GenerateItemRet = $cs.ContinuousSetRet; }
	| lpg = loop_generate_construct { $GenerateItemRet = $lpg.LoopGenerateConstructRet; }
	| gr = generate_region { $GenerateItemRet = $gr.GenerateRegionRet; }
	| c = ';' { $GenerateItemRet = new EmptyGenerateItem(); };

continuous_set
	returns[ContinuousSet ContinuousSetRet]:
	{ $ContinuousSetRet = new ContinuousSet(); } s = 'set' la = list_of_net_assignments {
          $ContinuousSetRet.setListOfNetAssignments($la.ListOfNetAssignmentsRet);
          $ContinuousSetRet.setLine($s.line);
      } ';';
procedural_continuous_set
	returns[ProceduralContinuousSet ProceduralContinuousSetRet]:
	s = 'set' va = variable_assignment {
          $ProceduralContinuousSetRet = new ProceduralContinuousSet();
          $ProceduralContinuousSetRet.setVariableAssignment($va.VariableAssignmentRet);
          $ProceduralContinuousSetRet.setLine($s.line);
      };
list_of_net_assignments
	returns[List<NetAssignment> ListOfNetAssignmentsRet]:
	{ $ListOfNetAssignmentsRet = new ArrayList(); } na1 = net_assignment {$ListOfNetAssignmentsRet.add($na1.NetAssignmentRet);
		} (
		',' na2 = net_assignment {$ListOfNetAssignmentsRet.add($na2.NetAssignmentRet);}
	)*;
net_assignment
	returns[NetAssignment NetAssignmentRet]:
	{ $NetAssignmentRet = new NetAssignment();
        $NetAssignmentRet.setLine($ctx.start.getLine());} nl = net_lvalue {$NetAssignmentRet.setNetLvalue($nl.NetLvalueRet);
		} '=' exp = expression {$NetAssignmentRet.setExpression($exp.ExpressionRet);};
operator_assignment
	returns[OperatorAssignment OperatorAssignmentRet]:
	vl = variable_lvalue ao = assignment_operator rhs = expression {
          $OperatorAssignmentRet = new OperatorAssignment();
          $OperatorAssignmentRet.setVariableLvalue($vl.VariableLvalueRet);
          $OperatorAssignmentRet.setOperator($ao.AssignmentOperatorRet);
          $OperatorAssignmentRet.setRightExpression($rhs.ExpressionRet);
          $OperatorAssignmentRet.setLine($ao.AssignmentOperatorRet.getLine());
      };

assignment_operator
	returns[AssignmentOperator AssignmentOperatorRet]:
	ao = (
		'='
		| '+='
		| '-='
		| '*='
		| '/='
		| '%='
		| '&='
		| '|='
		| '^='
		| '<<='
		| '>>='
		| '<<<='
		| '>>>='
	) {$AssignmentOperatorRet= AssignmentOperator.fromString($ao.text);};
nonblocking_assignment
	returns[NonblockingAssignment NonblockingAssignmentRet]:
	vl = variable_lvalue nb = '<=' exp = expression {
          $NonblockingAssignmentRet = new NonblockingAssignment();
          $NonblockingAssignmentRet.setVariableLvalue($vl.VariableLvalueRet);
          $NonblockingAssignmentRet.setExpression($exp.ExpressionRet);

          $NonblockingAssignmentRet.setLine($nb.line);
      };
variable_assignment
	returns[VariableAssignment VariableAssignmentRet]:
	vl = variable_lvalue eq = '=' exp = expression {
          $VariableAssignmentRet = new VariableAssignment();
          $VariableAssignmentRet.setVariableLvalue($vl.VariableLvalueRet);
          $VariableAssignmentRet.setExpression($exp.ExpressionRet);
          $VariableAssignmentRet.setLine($eq.line);
      };
statement_or_null
	returns[StatementOrNull StatementOrNullRet]:
	s = statement {
          $StatementOrNullRet = new NonEmptyStatementOrNull();
          ((NonEmptyStatementOrNull)$StatementOrNullRet).setStatement($s.StatementRet);
      }
	| sc = ';' {
          $StatementOrNullRet = new EmptyStatementOrNull();
          $StatementOrNullRet.setLine($sc.line);
      };
statement
	returns[Statement StatementRet]:
	{$StatementRet= new Statement();} (
		bl = block_label {
           $StatementRet.setBlockLabel($bl.BlockLabelRet);
       }
	)? si = statement_item {
          $StatementRet.setStatementItem($si.StatementItemRet);


      };
statement_item
	returns[StatementItem StatementItemRet]:
	oa = operator_assignment sc = ';' {
          $StatementItemRet = new OperatorAssignmentStatement();
          ((OperatorAssignmentStatement)$StatementItemRet).setOperatorAssignment($oa.OperatorAssignmentRet);
          $StatementItemRet.setLine($sc.line);   // line from semicolon
      }
	| nba = nonblocking_assignment sc = ';' {
         $StatementItemRet= new NonblockingAssignmentStatement();
          ((NonblockingAssignmentStatement)$StatementItemRet).setNonblockingAssignment($nba.NonblockingAssignmentRet);
         $StatementItemRet.setLine($sc.line);   // line from semicolon
      }
	| pcs = procedural_continuous_set sc = ';' {
          $StatementItemRet= new ProceduralContinuousSetStatement();
          ((ProceduralContinuousSetStatement)$StatementItemRet).setProceduralContinuousSet($pcs.ProceduralContinuousSetRet);
       $StatementItemRet.setLine($sc.line);   // line from semicolon
      }
	| cs = conditional_statement {
         $StatementItemRet = $cs.ConditionalStatementRet;
      }
	| ide = inc_or_dec_expression sc = ';' {
          $StatementItemRet= new IncOrDecStatement();
          ((IncOrDecStatement)$StatementItemRet).setIncOrDecExpression($ide.IncOrDecExpressionRet);
          $StatementItemRet.setLine($sc.line);   // line from semicolon
      }
	| scs = subroutine_call_statement {
         $StatementItemRet = $scs.SubroutineCallStatementRet;
      }
	| ls = loop_statement {
         $StatementItemRet= $ls.LoopStatementRet;
      };
conditional_statement
	returns[ConditionalStatement ConditionalStatementRet]:
	i = 'if' '(' cp = cond_predicate ')' stThen = statement_or_null {
          $ConditionalStatementRet = new ConditionalStatement();
          $ConditionalStatementRet.setCondPredicate($cp.CondPredicateRet);
          $ConditionalStatementRet.setThenStatement($stThen.StatementOrNullRet);
          $ConditionalStatementRet.setLine($i.line);
      } (
		'else' stElse = statement_or_null {
            $ConditionalStatementRet.setElseStatement($stElse.StatementOrNullRet);
        }
	)?;

cond_predicate
	returns[CondPredicate CondPredicateRet]:
	e1 = expression {
          $CondPredicateRet = new CondPredicate();
          $CondPredicateRet.addExpression($e1.ExpressionRet);
      } (
		'&&&' e2 = expression {
            $CondPredicateRet.addExpression($e2.ExpressionRet);
        }
	)*;

loop_statement
	returns[LoopStatement LoopStatementRet]:
	{
          $LoopStatementRet = new ForeverLoop();
      } f = 'forever' stmt = statement_or_null {
          ((ForeverLoop)$LoopStatementRet).setStatementOrNull($stmt.StatementOrNullRet);
          $LoopStatementRet.setLine($f.line);
      }
	| {
          $LoopStatementRet = new RepeatLoop();
      } r = 'repeat' '(' exp = expression ')' period = period_declaration stmt = statement_or_null {
          ((RepeatLoop)$LoopStatementRet).setExpression($exp.ExpressionRet);
          ((RepeatLoop)$LoopStatementRet).setPeriodDeclaration($period.PeriodDeclarationRet);
          ((RepeatLoop)$LoopStatementRet).setStatementOrNull($stmt.StatementOrNullRet);
          $LoopStatementRet.setLine($r.getLine());
      }
	| {
          $LoopStatementRet = new WhileLoop();
      } d = 'do' stmt = statement_or_null 'while' '(' exp = expression ')' ';' {
          ((WhileLoop)$LoopStatementRet).setExpression($exp.ExpressionRet);
          ((WhileLoop)$LoopStatementRet).setStatementOrNull($stmt.StatementOrNullRet);
          $LoopStatementRet.setLine($d.line);
      };
period_declaration
	returns[PeriodDeclaration PeriodDeclarationRet]:
	p = 'period' '=' e = expression {
          $PeriodDeclarationRet = new PeriodDeclaration();
          $PeriodDeclarationRet.setExpression($e.ExpressionRet);
          $PeriodDeclarationRet.setLine($p.line);
      };
subroutine_call_statement
	returns[SubroutineCallStatement SubroutineCallStatementRet]:
	sc = subroutine_call s = ';' {
          $SubroutineCallStatementRet = new SimpleSubroutineCallStatement();
          ((SimpleSubroutineCallStatement)$SubroutineCallStatementRet)
                  .setSubroutineCall($sc.SubroutineCallRet);
          $SubroutineCallStatementRet.setLine($s.line);
      }
	| v = 'void' ap = '\'' lp = '(' sc = subroutine_call rp = ')' s = ';' {
          $SubroutineCallStatementRet = new VoidSubroutineCallStatement();
          ((VoidSubroutineCallStatement)$SubroutineCallStatementRet)
                  .setSubroutineCall($sc.SubroutineCallRet);
          $SubroutineCallStatementRet.setLine($v.line);
      };
concatenation
	returns[Concatenation ConcatenationRet]:
	lc = '{' e1 = expression {
          $ConcatenationRet = new NonEmptyConcatenation();
          ((NonEmptyConcatenation)$ConcatenationRet).addExpression($e1.ExpressionRet);
          $ConcatenationRet.setLine($lc.line);
      } (
		',' e2 = expression {
           ((NonEmptyConcatenation)$ConcatenationRet).addExpression($e2.ExpressionRet);
       }
	)* rc = '}'
	| lc = '{' rc = '}' {
          $ConcatenationRet = new EmptyConcatenation();
          $ConcatenationRet.setLine($lc.line);
      };
constant_concatenation
	returns[ConstantConcatenation ConstantConcatenationRet]:
	lc = '{' ce1 = constant_expression {
          $ConstantConcatenationRet = new ConstantConcatenation();
          $ConstantConcatenationRet.addConstantExpression($ce1.ConstantExpressionRet);
          $ConstantConcatenationRet.setLine($lc.line);
      } (
		',' ce2 = constant_expression {
           $ConstantConcatenationRet.addConstantExpression($ce2.ConstantExpressionRet);
       }
	)* rc = '}';
arg_list
	returns[ArgList ArgListRet]:
	lp = '(' loa = list_of_arguments rp = ')' {
          $ArgListRet = new ArgList();
          $ArgListRet.setListOfArguments($loa.ListOfArgumentsRet);
          $ArgListRet.setLine($lp.line);
      };
subroutine_call
	returns[SubroutineCall SubroutineCallRet]:
	id = identifier {
          $SubroutineCallRet = new SubroutineCall();
          $SubroutineCallRet.setIdentifier($id.IdentifierRet);

      } (
		al = arg_list {
           $SubroutineCallRet.setArgList($al.ArgListRet);
       }
	)?;
list_of_arguments
	returns[ListOfArguments ListOfArgumentsRet]:
	{
          $ListOfArgumentsRet = new MixedListOfArguments();
      } oa1 = ordered_arg {
          ((MixedListOfArguments)$ListOfArgumentsRet).addOrderedArg($oa1.OrderedArgRet);
      } (
		',' oa2 = ordered_arg {
          ((MixedListOfArguments)$ListOfArgumentsRet).addOrderedArg($oa2.OrderedArgRet);
      }
	)* (
		',' na1 = named_arg {
          ((MixedListOfArguments)$ListOfArgumentsRet).addNamedArg($na1.NamedArgRet);
      }
	)*
	| {
          $ListOfArgumentsRet = new NamedOnlyListOfArguments();
      } na1 = named_arg {
          ((NamedOnlyListOfArguments)$ListOfArgumentsRet).addNamedArg($na1.NamedArgRet);
      } (
		',' na2 = named_arg {
          ((NamedOnlyListOfArguments)$ListOfArgumentsRet).addNamedArg($na2.NamedArgRet);
      }
	)*;
ordered_arg
	returns[OrderedArg OrderedArgRet]:
	{ $OrderedArgRet = new OrderedArg(); } (
		e = expression {
           $OrderedArgRet.setExpression($e.ExpressionRet);

       }
	)?;
named_arg
	returns[NamedArg NamedArgRet]:
	d = '.' id = identifier lp = '(' {
          $NamedArgRet = new NamedArg();
          $NamedArgRet.setIdentifier($id.IdentifierRet);
          $NamedArgRet.setLine($d.line);
      } (
		e = expression {
           $NamedArgRet.setExpression($e.ExpressionRet);
       }
	)? rp = ')';
inc_or_dec_expression
	returns[IncOrDecExpression IncOrDecExpressionRet]:
	op = inc_or_dec_operator vl = variable_lvalue {
          $IncOrDecExpressionRet = new PrefixIncOrDecExpression();
          ((PrefixIncOrDecExpression)$IncOrDecExpressionRet)
              .setOperator($op.IncOrDecOperatorRet);
          ((PrefixIncOrDecExpression)$IncOrDecExpressionRet)
              .setVariableLvalue($vl.VariableLvalueRet);
      }
	| vl = variable_lvalue op = inc_or_dec_operator {
          $IncOrDecExpressionRet = new PostfixIncOrDecExpression();
          ((PostfixIncOrDecExpression)$IncOrDecExpressionRet)
              .setOperator($op.IncOrDecOperatorRet);
          ((PostfixIncOrDecExpression)$IncOrDecExpressionRet)
              .setVariableLvalue($vl.VariableLvalueRet);
      };
constant_expression
	returns[ConstantExpression ConstantExpressionRet]:
	cp = constant_primary {
          $ConstantExpressionRet = $cp.ConstantPrimaryRet;
      }
	| uo = unary_operator cp = constant_primary {
          $ConstantExpressionRet = new ConstantUnaryExpression();
          ((ConstantUnaryExpression)$ConstantExpressionRet).setOperator($uo.UnaryOperatorRet);
          ((ConstantUnaryExpression)$ConstantExpressionRet).setOperand($cp.ConstantPrimaryRet);
      }
	| left = constant_expression op = '**' right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = ('*' | '/' | '%') right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = ('+' | '-') right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = (
		'>>'
		| '<<'
		| '>>>'
		| '<<<'
	) right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = ('<' | '<=' | '>' | '>=') right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = (
		'=='
		| '!='
		| '==='
		| '!=='
		| '==?'
		| '!=?'
	) right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = '&' right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = ('^' | '^~' | '~^') right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = '|' right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = '&&' right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| left = constant_expression op = '||' right = constant_expression {
          $ConstantExpressionRet = new ConstantBinaryExpression();
          ((ConstantBinaryExpression)$ConstantExpressionRet).setLeft($left.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setRight($right.ConstantExpressionRet);
          ((ConstantBinaryExpression)$ConstantExpressionRet).setOperator(BinaryOperator.fromString($op.text));
          $ConstantExpressionRet.setLine($op.line);
      }
	| <assoc = right> cond = constant_expression q = '?' yes = constant_expression ':' no =
		constant_expression {
          $ConstantExpressionRet = new ConstantTernaryExpression();
          ((ConstantTernaryExpression)$ConstantExpressionRet).setCondition($cond.ConstantExpressionRet);
          ((ConstantTernaryExpression)$ConstantExpressionRet).setYesExpression($yes.ConstantExpressionRet);
          ((ConstantTernaryExpression)$ConstantExpressionRet).setNoExpression($no.ConstantExpressionRet);
          $ConstantExpressionRet.setLine($q.line);
      };

constant_param_expression
	returns[ConstantParamExpression ConstantParamExpressionRet]:
	cme = constant_mintypmax_expression {
          $ConstantParamExpressionRet = $cme.ConstantMintypmaxExpressionRet;
      }
	| dt = data_type {

           $ConstantParamExpressionRet=$dt.DataTypeRet;
      };

param_expression
	returns[ParamExpression ParamExpressionRet]:
	me = mintypmax_expression {
          $ParamExpressionRet = $me.MintypmaxExpressionRet;
      }
	| dt = data_type {
          $ParamExpressionRet = $dt.DataTypeRet;
          $ParamExpressionRet.setLine($dt.DataTypeRet.getLine());
      };
constant_range
	returns[ConstantRange ConstantRangeRet]:
	e1 = constant_expression s = ':' e2 = constant_expression {
          $ConstantRangeRet = new ConstantRange();
          $ConstantRangeRet.setLeft($e1.ConstantExpressionRet);
          $ConstantRangeRet.setRight($e2.ConstantExpressionRet);
          $ConstantRangeRet.setLine($s.line);

      };

expression
	returns[Expression ExpressionRet]:
	lp = '(' e = expression rp = ')' {
          $ExpressionRet = $e.ExpressionRet;
      } |
    p = primary {
          $ExpressionRet = $p.PrimaryRet;
      }
	| lp = '(' oa = operator_assignment rp = ')' {
       $ExpressionRet = new AssignmentExpression();
      ( ( AssignmentExpression)$ExpressionRet).setOperatorAssignment($oa.OperatorAssignmentRet);
           ( ( AssignmentExpression)$ExpressionRet).setLine($lp.line);
      }
	| uo = unary_operator p = primary {
          $ExpressionRet = new UnaryExpression();
          ((UnaryExpression)$ExpressionRet).setOperator($uo.UnaryOperatorRet);
          ((UnaryExpression)$ExpressionRet).setPrimary($p.PrimaryRet);
          $ExpressionRet.setLine($p.PrimaryRet.getLine());

      }
	| ide = inc_or_dec_expression {
          $ExpressionRet = $ide.IncOrDecExpressionRet;
      }
	| left = expression op = '**' right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = ('*' | '/' | '%') right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = ('+' | '-') right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = ('>>' | '<<' | '>>>' | '<<<') right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = ('<' | '<=' | '>' | '>=') right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = (
		'=='
		| '!='
		| '==='
		| '!=='
		| '==?'
		| '!=?'
	) right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = '&' right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = ('^' | '^~' | '~^') right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = '|' right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = '&&' right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| left = expression op = '||' right = expression {
          $ExpressionRet =
              new BinaryExpression($left.ExpressionRet,
                                   BinaryOperator.fromString($op.text),
                                   $right.ExpressionRet);
          $ExpressionRet.setLine($op.line);
      }
	| <assoc = right> cond = expression q = '?' yes = expression ':' no = expression {
          $ExpressionRet =
              new IfExpressionExpression($cond.ExpressionRet,
                                         $yes.ExpressionRet,
                                         $no.ExpressionRet);
          $ExpressionRet.setLine($q.line);
      };

part_select_range
	returns[PartSelectRange PartSelectRangeRet]:
	cr = constant_range {
          $PartSelectRangeRet = $cr.ConstantRangeRet;
      };
genvar_expression
	returns[GenvarExpression GenvarExpressionRet]:
	ce = constant_expression {
          $GenvarExpressionRet = $ce.ConstantExpressionRet;
      };
constant_primary
	returns[ConstantPrimary ConstantPrimaryRet]:
	pl = primary_literal {
          $ConstantPrimaryRet = new ConstantLiteralPrimary();
          ((ConstantLiteralPrimary)$ConstantPrimaryRet).setPrimaryLiteral($pl.PrimaryLiteralRet);
      }
	| id1 = identifier {
          $ConstantPrimaryRet = new ConstantIdentifierPrimary();
          ((ConstantIdentifierPrimary)$ConstantPrimaryRet).setIdentifier($id1.IdentifierRet);
      } (
		cs = constant_select {
           ((ConstantIdentifierPrimary)$ConstantPrimaryRet).setConstantSelect($cs.ConstantSelectRet);
       }
	)?
	| cc = constant_concatenation {
          $ConstantPrimaryRet = $cc.ConstantConcatenationRet;
      }
	| id2 = identifier al = arg_list {
          $ConstantPrimaryRet = new ConstantCallPrimary();
          ((ConstantCallPrimary)$ConstantPrimaryRet).setIdentifier($id2.IdentifierRet);
          ((ConstantCallPrimary)$ConstantPrimaryRet).setArgList($al.ArgListRet);
          $ConstantPrimaryRet.setLine($id2.IdentifierRet.getLine());
      }
	| lp = '(' cme = constant_mintypmax_expression rp = ')' {
          $ConstantPrimaryRet = new ConstantMintypmaxPrimary();
          ((ConstantMintypmaxPrimary)$ConstantPrimaryRet)
              .setConstantMintypmaxExpression($cme.ConstantMintypmaxExpressionRet);
          $ConstantPrimaryRet.setLine($lp.line);
      }
	| cp = constant_primary ap = '\'' lp = '(' ce = constant_expression rp = ')' {
          $ConstantPrimaryRet = new ConstantPrimaryCast();
          ((ConstantPrimaryCast)$ConstantPrimaryRet).setConstantPrimary($cp.ConstantPrimaryRet);
          ((ConstantPrimaryCast)$ConstantPrimaryRet).setExpression($ce.ConstantExpressionRet);
          $ConstantPrimaryRet.setLine($ap.line);
      }
	| st = simple_type ap = '\'' lp = '(' ce = constant_expression rp = ')' {
      $ConstantPrimaryRet = new ConstantTypeCastPrimary();
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet)
          .setSimpleType($st.SimpleTypeRet);
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet)
          .setExpression($ce.ConstantExpressionRet);
      $ConstantPrimaryRet.setLine($lp.line);
  }
	| sg = signing ap = '\'' lp = '(' ce = constant_expression rp = ')' {
      $ConstantPrimaryRet = new ConstantTypeCastPrimary();
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet)
          .setSigning($sg.SigningRet);
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet)
          .setExpression($ce.ConstantExpressionRet);
      $ConstantPrimaryRet.setLine($ctx.start.getLine());
  }
	| s = 'string' ap = '\'' lp = '(' ce = constant_expression rp = ')' {
      $ConstantPrimaryRet = new ConstantTypeCastPrimary();
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet).setStringKeyword(true);
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet)
          .setExpression($ce.ConstantExpressionRet);
      $ConstantPrimaryRet.setLine($s.line);
  }
	| c = 'const' ap = '\'' lp = '(' ce = constant_expression rp = ')' {
      $ConstantPrimaryRet = new ConstantTypeCastPrimary();
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet).setConstKeyword(true);
      ((ConstantTypeCastPrimary)$ConstantPrimaryRet)
          .setExpression($ce.ConstantExpressionRet);
      $ConstantPrimaryRet.setLine($c.line);
  } // 'null'
	| n = 'null' {
          $ConstantPrimaryRet = new ConstantNullPrimary();
          $ConstantPrimaryRet.setLine($n.line);
      };

primary
	returns[Primary PrimaryRet]:
	pl = primary_literal {
          $PrimaryRet = $pl.PrimaryLiteralRet;
      }
	| hi = hierarchical_identifier {
          $PrimaryRet = new HierarchicalPrimary();
          ((HierarchicalPrimary)$PrimaryRet).setHierarchicalIdentifier($hi.HierarchicalIdentifierRet);
          ((HierarchicalPrimary)$PrimaryRet).setLine($hi.HierarchicalIdentifierRet.getLine());
      } (
		sel = select_ {
           ((HierarchicalPrimary)$PrimaryRet).setSelect($sel.Select_Ret);
       }
	)?
	| cn = concatenation {
          $PrimaryRet = $cn.ConcatenationRet;
      }
	| id = identifier al = arg_list {
          $PrimaryRet = new CallPrimary();
          ((CallPrimary)$PrimaryRet).setIdentifier($id.IdentifierRet);
          ((CallPrimary)$PrimaryRet).setArgList($al.ArgListRet);
          $PrimaryRet.setLine($id.IdentifierRet.getLine());
      }
	| lp = '(' me = mintypmax_expression rp = ')' {
          $PrimaryRet = new MintypmaxPrimary();
          ((MintypmaxPrimary)$PrimaryRet).setMintypmaxExpression($me.MintypmaxExpressionRet);
          $PrimaryRet.setLine($lp.line);  // we captured '('
      }
	| p = primary ap = '\'' lp = '(' e = expression rp = ')' {
          $PrimaryRet = new PrimaryCast();
          ((PrimaryCast)$PrimaryRet).setPrimary($p.PrimaryRet);
          ((PrimaryCast)$PrimaryRet).setExpression($e.ExpressionRet);
          $PrimaryRet.setLine($ap.line);  // use the '\'' token line
      }
	| it = integer_type ap = '\'' lp = '(' e = expression rp = ')' {
          $PrimaryRet = new TypeCastPrimary();
          ((TypeCastPrimary)$PrimaryRet).setIntegerType($it.IntegerTypeRet);
          ((TypeCastPrimary)$PrimaryRet).setExpression($e.ExpressionRet);
          $PrimaryRet.setLine($lp.line);
      }
	| nt = non_integer_type ap = '\'' lp = '(' e = expression rp = ')' {
          $PrimaryRet = new TypeCastPrimary();
          ((TypeCastPrimary)$PrimaryRet).setNonIntegerType($nt.NonIntegerTypeRet);
          ((TypeCastPrimary)$PrimaryRet).setExpression($e.ExpressionRet);
          $PrimaryRet.setLine($lp.line);
      }
	| sg = signing ap = '\'' lp = '(' e = expression rp = ')' {
          $PrimaryRet = new TypeCastPrimary();
          ((TypeCastPrimary)$PrimaryRet).setSigning($sg.SigningRet);
          ((TypeCastPrimary)$PrimaryRet).setExpression($e.ExpressionRet);
          $PrimaryRet.setLine($ctx.start.getLine());
      }
	| s = 'string' ap = '\'' lp = '(' e = expression rp = ')' {
          $PrimaryRet = new TypeCastPrimary();
          ((TypeCastPrimary)$PrimaryRet).setStringKeyword(true);
          ((TypeCastPrimary)$PrimaryRet).setExpression($e.ExpressionRet);
          $PrimaryRet.setLine($s.line);
      }
	| c = 'const' ap = '\'' lp = '(' e = expression rp = ')' {
          $PrimaryRet = new TypeCastPrimary();
          ((TypeCastPrimary)$PrimaryRet).setConstKeyword(true);
          ((TypeCastPrimary)$PrimaryRet).setExpression($e.ExpressionRet);
          $PrimaryRet.setLine($c.line);
      };

primary_literal
	returns[PrimaryLiteral PrimaryLiteralRet]:
	n = number { $PrimaryLiteralRet = $n.NumberRet; }
	| tl = time_literal { $PrimaryLiteralRet = $tl.TimeLiteralRet; }
	| uul = unbased_unsized_literal { $PrimaryLiteralRet = $uul.UnbasedUnsizedLiteralRet; }
	| sl = string_literal { $PrimaryLiteralRet = $sl.StringLiteralRet; };
bit_select
	returns[BitSelect BitSelectRet]:
	{ $BitSelectRet = new BitSelect(); } (
		'[' e = expression ']' {
           $BitSelectRet.addExpression($e.ExpressionRet);
       }
	)+;
select_
	returns[Select_ Select_Ret]:
	{$Select_Ret=new RangeOnlySelect(); } '[' psr = part_select_range ']' {

           ((RangeOnlySelect)$Select_Ret).setPartSelectRange($psr.PartSelectRangeRet);

      }
	| {$Select_Ret=new BitSelectWithRange(); } bs = bit_select {

          ((BitSelectWithRange)$Select_Ret).setBitSelect($bs.BitSelectRet);

      } (
		'[' psr2 = part_select_range ']' {
            ((BitSelectWithRange)$Select_Ret).setPartSelectRange($psr2.PartSelectRangeRet);
        }
	)?;

constant_bit_select
	returns[ConstantBitSelect ConstantBitSelectRet]:
	{ $ConstantBitSelectRet = new ConstantBitSelect(); } (
		'[' ce = constant_expression ']' {
           $ConstantBitSelectRet.addConstantExpression($ce.ConstantExpressionRet);
       }
	)+;
constant_select
	returns[ConstantSelect ConstantSelectRet]:
	cbs = constant_bit_select {
          $ConstantSelectRet = $cbs.ConstantBitSelectRet;
      };
net_lvalue
	returns[NetLvalue NetLvalueRet]:
	{
          $NetLvalueRet = new IdentifierNetLvalue();
      } id = identifier {
          ((IdentifierNetLvalue)$NetLvalueRet).setIdentifier($id.IdentifierRet);
      } (
		cs = constant_select {
           ((IdentifierNetLvalue)$NetLvalueRet).setConstantSelect($cs.ConstantSelectRet);
       }
	)?
	| {
          $NetLvalueRet = new ConcatenationNetLvalue();
      } '{' nl1 = net_lvalue {
          ((ConcatenationNetLvalue)$NetLvalueRet).addNetLvalue($nl1.NetLvalueRet);
      } (
		',' nl2 = net_lvalue {
          ((ConcatenationNetLvalue)$NetLvalueRet).addNetLvalue($nl2.NetLvalueRet);
      }
	)* '}';
variable_lvalue
	returns[VariableLvalue VariableLvalueRet]:
	{
          $VariableLvalueRet = new HierarchicalVariableLvalue();
      } hi = hierarchical_identifier {
          ((HierarchicalVariableLvalue)$VariableLvalueRet)
              .setHierarchicalIdentifier($hi.HierarchicalIdentifierRet);
          $VariableLvalueRet.setLine($hi.HierarchicalIdentifierRet.getLine());
      } (
		sel = select_ {
            ((HierarchicalVariableLvalue)$VariableLvalueRet)
                .setSelect_($sel.Select_Ret);
        }
	)?
	| {
          $VariableLvalueRet = new ConcatenationVariableLvalue();
      } lb = '{' vl1 = variable_lvalue {
          ((ConcatenationVariableLvalue)$VariableLvalueRet)
              .addVariableLvalue($vl1.VariableLvalueRet);
          $VariableLvalueRet.setLine($lb.line);
      } (
		',' vl2 = variable_lvalue {
            ((ConcatenationVariableLvalue)$VariableLvalueRet)
                .addVariableLvalue($vl2.VariableLvalueRet);
        }
	)* rb = '}' {
      };

unary_operator
	returns[UnaryOperator UnaryOperatorRet]:
	uo = (
		'+'
		| '-'
		| '!'
		| '~'
		| '&'
		| '~&'
		| '|'
		| '~|'
		| '^'
		| '~^'
		| '^~'
	) {$UnaryOperatorRet= UnaryOperator.fromString($uo.text);};

inc_or_dec_operator
	returns[IncOrDecOperator IncOrDecOperatorRet]:
	uo = ('++' | '--') {$IncOrDecOperatorRet=IncOrDecOperator.fromString($uo.text);};
number
	returns[Number NumberRet]:
	in = integral_number { $NumberRet = $in.IntegralNumberRet; }
	| rn = real_number { $NumberRet = $rn.RealNumberRet; };

integral_number
	returns[Number IntegralNumberRet]:
	dn = decimal_number { $IntegralNumberRet = $dn.DecimalNumberRet; }
	| bn = binary_number { $IntegralNumberRet = $bn.BinaryNumberRet; };

decimal_number
	returns[Number DecimalNumberRet]:
	{
          $DecimalNumberRet = new Number(Number.NumberKind.DECIMAL, null);
      } (s = size { $DecimalNumberRet.setSize($s.SizeRet); })? db = decimal_base {
          $DecimalNumberRet.setText($db.text);
          $DecimalNumberRet.setBase($db.text);
      }
	| un = unsigned_number {
          $DecimalNumberRet = new Number(
              Number.NumberKind.UNSIGNED_DECIMAL,
              $un.text
          );
      };

constant_mintypmax_expression
	returns[ConstantMintypmaxExpression ConstantMintypmaxExpressionRet]:
	ce1 = constant_expression {
          $ConstantMintypmaxExpressionRet = new ConstantMintypmaxExpression();
          $ConstantMintypmaxExpressionRet.setMinExpression($ce1.ConstantExpressionRet);
      } (
		cl = ':' ce2 = constant_expression ':' ce3 = constant_expression {
            $ConstantMintypmaxExpressionRet.setTypExpression($ce2.ConstantExpressionRet);
            $ConstantMintypmaxExpressionRet.setMaxExpression($ce3.ConstantExpressionRet);
            $ConstantMintypmaxExpressionRet.setLine($cl.line);
        }
	)?;

mintypmax_expression
	returns[MintypmaxExpression MintypmaxExpressionRet]:
	e1 = expression {
          $MintypmaxExpressionRet= new MintypmaxExpression();
          $MintypmaxExpressionRet.setMinExpression($e1.ExpressionRet);

      } (
		cl = ':' e2 = expression ':' e3 = expression {
             $MintypmaxExpressionRet= new MintypmaxExpression();
          $MintypmaxExpressionRet.setTypExpression($e2.ExpressionRet);
            $MintypmaxExpressionRet.setMaxExpression($e3.ExpressionRet);
      }
	)?;

binary_number
	returns[Number BinaryNumberRet]:
	{
          $BinaryNumberRet = new Number(Number.NumberKind.BINARY, null);
      } (s = size { $BinaryNumberRet.setSize($s.SizeRet); })? bb = binary_base {
          $BinaryNumberRet.setText($bb.text);
          $BinaryNumberRet.setBase($bb.text);
      };

time_literal
	returns[TimeLiteral TimeLiteralRet]:
	t = TIME_LITERAL { $TimeLiteralRet = new TimeLiteral($t.text, $t.line); };
size
	returns[Integer SizeRet]:
	sn = UNSIGNED_NUMBER { $SizeRet = Integer.parseInt($sn.text); };

real_number
	returns[Number RealNumberRet]:
	fp = FIXED_POINT_NUMBER {
          $RealNumberRet = new Number(
              Number.NumberKind.REAL,
              $fp.text
          );
      }
	| en = EXPONENTIAL_NUMBER {
          $RealNumberRet = new Number(
              Number.NumberKind.REAL,
              $en.text
          );
      };

unsigned_number
	returns[String UnsignedNumberRet]:
	un = UNSIGNED_NUMBER { $UnsignedNumberRet = $un.text; };

decimal_base
	returns[String DecimalBaseRet]:
	db = DECIMAL_BASE { $DecimalBaseRet = $db.text; };

binary_base
	returns[String BinaryBaseRet]:
	bb = BINARY_BASE { $BinaryBaseRet = $bb.text; };

unbased_unsized_literal
	returns[Number UnbasedUnsizedLiteralRet]:
	uul = UNBASED_UNSIZED_LITERAL {
          $UnbasedUnsizedLiteralRet = new Number(
              Number.NumberKind.UNBASED_UNSIZED,
              $uul.text
          );
      };
string_literal
	returns[StringLiteral StringLiteralRet]:
	sl = STRING_LITERAL { $StringLiteralRet = new StringLiteral($sl.text, $sl.line); };
identifier
	returns[Identifier IdentifierRet]: (
		s = SIMPLE_IDENTIFIER {$IdentifierRet=new Identifier($s.text,$s.line);}
	)
	| (
		e = ESCAPED_IDENTIFIER {$IdentifierRet=new Identifier($e.text,$e.line);}
	);

hierarchical_identifier
	returns[Identifier HierarchicalIdentifierRet]:
	i = identifier {$HierarchicalIdentifierRet=$i.IdentifierRet;};
AND: 'and';
BIT: 'bit';
BREAK: 'break';
BUF: 'buf';
BUFIFONE: 'bufif1';
BUFIFZERO: 'bufif0';
BYTE: 'byte';
CASE: 'case';
CASEX: 'casex';
CASEZ: 'casez';
COMPONENTTYPE: 'componenttype';
CHANDLE: 'chandle';
CLOCKING: 'clocking';
CONFIGURATION: 'configuration';
CONST: 'const';
CONTEXT: 'context';
CONTINUE: 'continue';
CROSS: 'cross';
DATA: 'data';
DEFAULT: 'default';
DESIGN: 'design';
DO: 'do';
EDGE: 'edge';
ELSE: 'else';
ENDCASE: 'endcase';
ENDCOMPONENTTYPE: 'endcomponenttype';
ENDCONFIGURATION: 'endconfiguration';
ENDDO: 'enddo';
ENDEXPAND: 'endexpand';
ENDPRIMITIVE: 'endprimitive';
ENDPROGRAM: 'endprogram';
ENUM: 'enum';
EVENT: 'event';
EXPAND: 'expand';
FOR: 'for';
FOREACH: 'foreach';
FOREVER: 'forever';
GLOBAL: 'global';
HIGHZONE: 'highz1';
HIGHZZERO: 'highz0';
IF: 'if';
IFF: 'iff';
IFNONE: 'ifnone';
IN: 'in';
INCLUDE: 'include';
INDEX: 'index';
INOUT: 'inout';
INSTANCE: 'instance';
INT: 'int';
INTEGER: 'integer';
INTERCONNECT: 'interconnect';
LARGE: 'large';
LOCAL: 'local';
LOCALPARAM: 'localparam';
LONGINT: 'longint';
MEDIUM: 'medium';
MODPORT: 'modport';
NAND: 'nand';
NEGEDGE: 'negedge';
NETTYPE: 'nettype';
NEW: 'new';
NOR: 'nor';
NOT: 'not';
NOTIFONE: 'notif1';
NOTIFZERO: 'notif0';
NULL: 'null';
OR: 'or';
OUT: 'out';
PACKED: 'packed';
PARAMETER: 'parameter';
POSEDGE: 'posedge';
PORT: 'port';
PRIMITIVE: 'primitive';
PROTECTED: 'protected';
PULLDOWN: 'pulldown';
PULLONE: 'pull1';
PULLUP: 'pullup';
PULLZERO: 'pull0';
PURE: 'pure';
REAL: 'real';
REF: 'ref';
RELEASE: 'release';
REPEAT: 'repeat';
RESTRICT: 'restrict';
RETURN: 'return';
SAMPLE: 'sample';
SET: 'set';
SHORTINT: 'shortint';
SHORTREAL: 'shortreal';
SIGNED: 'signed';
SMALL: 'small';
SOFT: 'soft';
SOLVE: 'solve';
STATIC: 'static';
STD: 'std';
STRING: 'string';
STRONG: 'strong';
STRONGONE: 'strong1';
STRONGZERO: 'strong0';
STRUCT: 'struct';
SUPER: 'super';
TAGGED: 'tagged';
THIS: 'this';
TIME: 'time';
TIMEPRECISION: 'timeprecision';
TIMESCALE: 'timescale';
TIMEUNIT: 'timeunit';
TYPE: 'type';
TYPEDEF: 'typedef';
UNION: 'union';
UNIQUE: 'unique';
UNIQUEZERO: 'unique0';
UNSIGNED: 'unsigned';
UNTYPED: 'untyped';
VAR: 'var';
VECTORED: 'vectored';
VIRTUAL: 'virtual';
VOID: 'void';
WHILE: 'while';
WILDCARD: 'wildcard';
WITH: 'with';
XNOR: 'xnor';
XOR: 'xor';

AM: '&';
AMAM: '&&';
AMAMAM: '&&&';
AMEQ: '&=';
AP: '\'';
AS: '*';
ASAS: '**';
ASEQ: '*=';
ASGT: '*>';
AT: '@';
ATAT: '@@';
CA: '^';
CAEQ: '^=';
CATI: '^~';
CL: ':';
CLCL: '::';
CLEQ: ':=';
CLSL: ':/';
CO: ',';
DL: '$';
DQ: '"';
DT: '.';
DTAS: '.*';
EM: '!';
EMEQ: '!=';
EMEQEQ: '!==';
EMEQQM: '!=?';
EQ: '=';
EQEQ: '==';
EQEQEQ: '===';
EQEQQM: '==?';
EQGT: '=>';
GA: '`';
GT: '>';
GTEQ: '>=';
GTGT: '>>';
GTGTEQ: '>>=';
GTGTGT: '>>>';
GTGTGTEQ: '>>>=';
HA: '#';
HAEQHA: '#=#';
HAHA: '##';
HAMIHA: '#-#';
LB: '[';
LC: '{';
LP: '(';
LT: '<';
LTEQ: '<=';
LTLT: '<<';
LTLTEQ: '<<=';
LTLTLT: '<<<';
LTLTLTEQ: '<<<=';
LTMIGT: '<->';
MI: '-';
MICL: '-:';
MIEQ: '-=';
MIGT: '->';
MIGTGT: '->>';
MIMI: '--';
MO: '%';
MOEQ: '%=';
PL: '+';
PLCL: '+:';
PLEQ: '+=';
PLPL: '++';
QM: '?';
RB: ']';
RC: '}';
RP: ')';
SC: ';';
SL: '/';
SLEQ: '/=';
TI: '~';
TIAM: '~&';
TICA: '~^';
TIVL: '~|';
VL: '|';
VLEQ: '|=';
VLEQGT: '|=>';
VLMIGT: '|->';
VLVL: '||';

fragment ASCII_ANY: [\u0000-\u007f];
fragment ASCII_NO_NEWLINE:
	[\u0000-\u0009\u000b-\u000c\u000e-\u007f];
fragment ASCII_NO_NEWLINE_QUOTE_BACKSLASH:
	[\u0000-\u0009\u000b-\u000c\u000e-\u0021\u0023-\u005b\u005d-\u007f];
fragment ASCII_NO_NEWLINE_QUOTE_SLASH_BACKSLASH_GRAVE_ACCENT:
	[\u0000-\u0009\u000b-\u000c\u000e-\u0021\u0023-\u002e\u0030-\u005b\u005d-\u005f\u0061-\u007f];
fragment ASCII_NO_PARENTHESES: [\u0000-\u0027\u002a-\u007f];
fragment ASCII_NO_SLASH_GRAVE_ACCENT:
	[\u0000-\u002e\u0030-\u005f\u0061-\u007f];
fragment ASCII_PRINTABLE: [\u0020-\u007e];
fragment ASCII_PRINTABLE_NO_QUOTE_ANGLE_BRACKETS_BACKSLASH:
	[\u0020-\u0021\u0023-\u003b\u003d\u003f-\u005b\u005d-\u007e];
fragment ASCII_PRINTABLE_NO_SPACE: [\u0021-\u007e];
fragment CHAR_HEX: [0-9a-fA-F] [0-9a-fA-F]?;
fragment CHAR_OCTAL: [0-7] [0-7]? [0-7]?;
fragment ESC_ASCII_NO_NEWLINE: '\\' ASCII_NO_NEWLINE;
fragment ESC_ASCII_PRINTABLE: '\\' ASCII_PRINTABLE;
fragment ESC_NEWLINE: '\\' '\r'? '\n';
fragment ESC_SPECIAL_CHAR:
	'\\' ([nt\\"vfa] | CHAR_HEX | CHAR_OCTAL);
fragment IDENTIFIER: ESCAPED_IDENTIFIER | SIMPLE_IDENTIFIER;
fragment MACRO_ARGS:
	'(' (MACRO_ARGS | ASCII_NO_PARENTHESES)* ')';
fragment SPACE_TAB: [ \t]+;

BLOCK_COMMENT: '/*' ASCII_ANY*? '*/' -> channel(HIDDEN);
LINE_COMMENT: ('//' | '--') ASCII_NO_NEWLINE* -> channel(HIDDEN);
ESCAPED_IDENTIFIER: '\\' ASCII_PRINTABLE_NO_SPACE* [ \t\r\n];
EXPONENTIAL_NUMBER:
	UNSIGNED_NUMBER ('.' UNSIGNED_NUMBER)? [eE] [+\-]? UNSIGNED_NUMBER;
FIXED_POINT_NUMBER: UNSIGNED_NUMBER '.' UNSIGNED_NUMBER;
SIMPLE_IDENTIFIER: [a-zA-Z_] [a-zA-Z0-9_$]*;
STRING_LITERAL:
	'"' (
		ASCII_NO_NEWLINE_QUOTE_BACKSLASH
		| ESC_NEWLINE
		| ESC_SPECIAL_CHAR
	)* '"';
SYSTEM_TF_IDENTIFIER: '$' [a-zA-Z0-9_$] [a-zA-Z0-9_$]*;
TIME_LITERAL:
	UNSIGNED_NUMBER ('.' UNSIGNED_NUMBER)? [munpf]? 's';
UNBASED_UNSIZED_LITERAL: '\'0' | '\'1' | '\'' [xXzZ];
UNSIGNED_NUMBER: [0-9] [0-9_]*;
WHITE_SPACE: [ \t\r\n]+ -> channel(HIDDEN);
NEWLINE: '\r'? '\n' -> channel(HIDDEN);
ZERO_OR_ONE_X_OR_Z: [01] [xXzZ];

BINARY_BASE: '\'' [sS]? [bB] [01xXzZ?] [01xXzZ?_]*;
DECIMAL_BASE: '\'' [sS]? [dD] ([0-9] [0-9_]* | [xXzZ?] '_'*);
OCTAL_BASE: '\'' [sS]? [oO] [0-7xXzZ?] [0-7xXzZ?_]*;
HEX_BASE: '\'' [sS]? [hH] [0-9a-fA-FxXzZ?] [0-9a-fA-FxXzZ?_]*;

TIMESCALE_DIRECTIVE:
	GA TIMESCALE [ \t]* BLOCK_COMMENT* [ \t]* UNSIGNED_NUMBER [ \t]* BLOCK_COMMENT* [ \t]* [munpf]?
		's' [ \t]* BLOCK_COMMENT* [ \t]* SL [ \t]* BLOCK_COMMENT* [ \t]* UNSIGNED_NUMBER [ \t]*
		BLOCK_COMMENT* [ \t]* [munpf]? 's' [ \t]* BLOCK_COMMENT* [ \t]* -> channel(HIDDEN);