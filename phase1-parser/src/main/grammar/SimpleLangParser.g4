// $antlr-format breakBeforeBraces true
// $antlr-format breakBeforeParens true
// $antlr-format allowShortBlocksOnASingleLine false, groupedAlignments true

parser grammar SimpleLangParser;

options {
	tokenVocab = SimpleLangLexer;
}

@header {
package main.grammar;
import main.ast.nodes.*;
import main.ast.nodes.Headers.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ParameterPortList.*;
import main.ast.nodes.ListOfPortDeclarations.*;
import main.ast.nodes.ParameterPortDeclaration.*;
import main.ast.nodes.PortDirection.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.Expression.*;
import main.ast.nodes.Statement.*;
}

source_text
	returns[SourceText SourceTextRet]:
	{
        $SourceTextRet= new SourceText();
    }
	(
		cd = component_declaration {
            $SourceTextRet.addComponentDeclaration($cd.ComponentDeclarationRet);
		}
	)* EOF;

component_declaration
	returns[ComponentDeclaration ComponentDeclarationRet]:
	{
        $ComponentDeclarationRet=new ItemHeaderDeclaration();
    } ch = component_header {
        $ComponentDeclarationRet.setStartLine($ch.ComponentHeaderRet.getLine());
        ((ItemHeaderDeclaration)$ComponentDeclarationRet).setComponentHeader($ch.ComponentHeaderRet);
    }
	(
		ci = component_item {
    ((ItemHeaderDeclaration)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
    }
	)*
	(
		tx = 'endcomponenttype' {

    $ComponentDeclarationRet.setEndLine($tx.line);


    }
	)
	(
		cn = component_name {
   ((ItemHeaderDeclaration)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);}
	)?
	| {$ComponentDeclarationRet=new IdentifierComponent();
    }
	(
		ct1 = 'componenttype' {

    $ComponentDeclarationRet.setStartLine($ct1.line);

    }
	)
	(
		i = identifier {
   $ComponentDeclarationRet.setStartLine($e.line);
    ((IdentifierComponent)$ComponentDeclarationRet).setIdentifier($i.IdentifierRet);
    }
	) '(' '.*' ')' ';'
	(
		ci = component_item {
    ((IdentifierComponent)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
    }
	)*
	(
		e = 'endcomponenttype' {
   $ComponentDeclarationRet.setEndLine($e.line);
   }
	)
	(
		cn = component_name {((IdentifierComponent)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);
			}
	)?
	| { $ComponentDeclarationRet = new ConfigHeaderDeclaration(); } ch2 = configuration_header {
              $ComponentDeclarationRet.setStartLine($ch2.ConfigurationHeaderRet.getLine());
              ((ConfigHeaderDeclaration)$ComponentDeclarationRet).setConfigurationHeader($ch2.ConfigurationHeaderRet);
          }
	(
		ci = component_item {
              ((ConfigHeaderDeclaration)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
          }
	)*
	(
		ec = 'endconfiguration' {
            $ComponentDeclarationRet.setEndLine($ec.line);
          }
	)
	(
		cn = component_name {
              ((ConfigHeaderDeclaration)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);
          }
	)?
	| { $ComponentDeclarationRet = new ConfigDeclaration(); } c = 'configuration' {
        $ComponentDeclarationRet.setStartLine($c.line);
       }
	(
		i = identifier {
           ((ConfigDeclaration)$ComponentDeclarationRet).setIdentifier($i.IdentifierRet);
       }
	) '(' '.*' ')' ';'
	(
		ci = component_item {
           ((ConfigDeclaration)$ComponentDeclarationRet).addComponentItem($ci.ComponentItemRet);
       }
	)*
	(
		ec = 'endconfiguration' {
           $ComponentDeclarationRet.setEndLine($ec.line);
       }
	)
	(
		cn = component_name {
           ((ConfigDeclaration)$ComponentDeclarationRet).setComponentName($cn.ComponentNameRet);
       }
	)?;

net_declaration
	returns[NetDeclaration NetDeclarationRet]:
	{
        $NetDeclarationRet = new NetDeclaration();
    } nt = net_type {
        $NetDeclarationRet.setNetType($nt.NetTypeRet);
    }
	(
		dti = data_type_or_implicit {
            $NetDeclarationRet.setDataTypeOrImplicit($dti.DataTypeOrImplicitRet);
        }
	)? lnda = list_of_net_decl_assignments {
        $NetDeclarationRet.setListOfNetDeclAssignments($lnda.ListOfNetDeclAssignmentsRet);
    } ';' {
        $NetDeclarationRet.setLine($nt.start.getLine());
    };

component_header
	returns[ComponentHeader ComponentHeaderRet]:
	{
        $ComponentHeaderRet = new ComponentHeader();
    } ct = 'componenttype' id = identifier {
          $ComponentHeaderRet.setIdentifier($id.IdentifierRet);
          $ComponentHeaderRet.setLine($ct.line);
    }
	(
		pp = parameter_port_list {
            $ComponentHeaderRet.setParameterPortList($pp.ParameterPortListRet);
        }
	)?
	(
		lp = list_of_port_declarations {
          $ComponentHeaderRet.setListOfPortDeclarations($lp.ListOfPortDeclarationsRet);
        }
	)? ';';

configuration_header
	returns[ConfigurationHeader ConfigurationHeaderRet]:
	{ $ConfigurationHeaderRet = new ConfigurationHeader(); } c = 'configuration' id = identifier {
          $ConfigurationHeaderRet.setIdentifier($id.IdentifierRet);
          $ConfigurationHeaderRet.setLine($c.line);
      }
	(
		pp = parameter_port_list {
          $ConfigurationHeaderRet.setParameterPortList($pp.ParameterPortListRet);
      }
	)?
	(
		lp = list_of_port_declarations {
          $ConfigurationHeaderRet.setListOfPortDeclarations($lp.ListOfPortDeclarationsRet);
      }
	)? ';';

component_name
	returns[ComponentName ComponentNameRet]:
	':'
	(
		i = identifier {$ComponentNameRet=new ComponentName($i.IdentifierRet);}
	);

parameter_port_list
	returns[ParameterPortList ParameterPortListRet]:
	{ $ParameterPortListRet = new ListBased(); } h = '#' '(' la = list_of_param_assignments {
              ((ListBased)$ParameterPortListRet).setListOfParamAssignments($la.ListOfParamAssignmentsRet);
          }
	(
		',' pp = parameter_port_declaration {
                ((ListBased)$ParameterPortListRet).addParameterPortDeclaration($pp.ParameterPortDeclarationRet);
            }
	)* ')' {
              $ParameterPortListRet.setLine($h.line);
          }
	| { $ParameterPortListRet = new DeclarationBased(); } '#' '(' pp1 = parameter_port_declaration {
              ((DeclarationBased)$ParameterPortListRet).addParameterPortDeclaration($pp1.ParameterPortDeclarationRet);
          }
	(
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
	pd = parameter_declaration {
          $ParameterPortDeclarationRet=$pd.ParameterDeclarationRet;

      }
	| { $ParameterPortDeclarationRet = new ParameterAssignmentBased(); } dt = data_type {
          ((ParameterAssignmentBased)$ParameterPortDeclarationRet).setDataType($dt.DataTypeRet);
      } la = list_of_param_assignments {
          ((ParameterAssignmentBased)$ParameterPortDeclarationRet).setListOfParamAssignments($la.ListOfParamAssignmentsRet);
          $ParameterPortDeclarationRet.setLine($dt.DataTypeRet.getLine());
      };

list_of_port_declarations
	returns[ListOfPortDeclarations ListOfPortDeclarationsRet]:
	{ $ListOfPortDeclarationsRet = new ListOfPortDeclarations(); } lp = '(' pd1 = port_decl {
          $ListOfPortDeclarationsRet.addPortDecl($pd1.PortDeclRet);
          $ListOfPortDeclarationsRet.setLine($lp.line);
      }
	(
		',' pd2 = port_decl {
          $ListOfPortDeclarationsRet.addPortDecl($pd2.PortDeclRet);
      }
	)* ')'
	| { $ListOfPortDeclarationsRet = new ListOfPortDeclarations(); } lp = '(' ')' {
          $ListOfPortDeclarationsRet.setLine($lp.line);
      };

port_direction
	returns[PortDirection PortDirectionRet]:
	i = 'in' {
        $PortDirectionRet= new InPortDirection($i.text,$i.line);
      }
	| o = 'out' {
          $PortDirectionRet = new OutPortDirection($o.text,$o.line);
      };

port_prefix
	returns[PortPrefix PortPrefixRet]:
	p = 'port' {
          $PortPrefixRet = new PortPrefix($p.text, $p.line);};

component_item
	returns[ComponentItem ComponentItemRet]:
	cc = component_common_item {
              $ComponentItemRet = $cc.ComponentCommonItemRet;
          }
	| cd = component_declaration {
              $ComponentItemRet = $cd.ComponentDeclarationRet;
          }
	| ls = loop_statement {
              $ComponentItemRet = $ls.LoopStatementRet;

          }
	| el = expand_loop {
              $ComponentItemRet = $el.ExpandLoopRet;
          };

expand_loop
	returns[ExpandLoop ExpandLoopRet]:
	{$ExpandLoopRet = new ExpandLoop();} EXPAND
	(
		fl = for_loop_statement {
        $ExpandLoopRet.addForLoopStatement($fl.ForLoopStatementRet);
        }
	)* ENDEXPAND;

component_item_declaration
	returns[ComponentItemDeclaration ComponentItemDeclarationRet]:
	nd = net_declaration {
        $ComponentItemDeclarationRet = $nd.NetDeclarationRet;
    }
	| pd = parameter_declaration {
        $ComponentItemDeclarationRet = $pd.ParameterDeclarationRet;
    } ';'
	| id = index_declaration {
        $ComponentItemDeclarationRet = $id.IndexDeclarationRet;
    } ';'
	| ';';

parameter_declaration
	returns[ParameterDeclaration ParameterDeclarationRet]:
	{$ParameterDeclarationRet = new ParameterDeclaration();} 'parameter'
	(
		dti = data_type_or_implicit { $ParameterDeclarationRet.setType($dti.DataTypeOrImplicitRet); 
			}
	)? la = list_of_param_assignments { $ParameterDeclarationRet.setAssignments($la.ListOfParamAssignmentsRet); 
		};

data_type_or_implicit
	returns[DataTypeOrImplicit DataTypeOrImplicitRet]:
	dt = data_type {
        $DataTypeOrImplicitRet = $dt.DataTypeRet;
    }
	| idt = implicit_data_type {
        $DataTypeOrImplicitRet = $idt.ImplicitDataTypeRet;
    };

integer_type
	returns[IntegerType IntegerTypeRet]:
	ivt = integer_vector_type {
        $IntegerTypeRet = $ivt.IntegerVectorTypeRet;
    }
	| iat = integer_atom_type {
        $IntegerTypeRet = $iat.IntegerAtomTypeRet;
    };

integer_atom_type
	returns[IntegerAtomType IntegerAtomTypeRet]:
	{ $IntegerAtomTypeRet = new IntegerAtomType(); } t =
	(
		'byte'
		| 'shortint'
		| 'int'
		| 'longint'
		| 'integer'
		| 'time'
	) {
        $IntegerAtomTypeRet.setType($t.text);
    };

integer_vector_type
	returns[IntegerVectorType IntegerVectorTypeRet]:
	{ $IntegerVectorTypeRet = new IntegerVectorType(); } t =
	(
		'bit'
		| 'data'
	) {
        $IntegerVectorTypeRet.setType($t.text);
    };

non_integer_type
	returns[NonIntegerType NonIntegerTypeRet]:
	t =
	(
		'shortreal'
		| 'real'
	) {
        $NonIntegerTypeRet.setNonIntegerType($t.text);
    };

net_type
	returns[String NetTypeRet]:
	d = 'data' {$NetTypeRet = $d.text;};

list_of_param_assignments
	returns[ListOfParamAssignments ListOfParamAssignmentsRet]:
	{$ListOfParamAssignmentsRet=new ListOfParamAssignments();} pa1 = param_assignment {
          $ListOfParamAssignmentsRet.addParamAssignment($pa1.ParamAssignmentRet);
      }
	(
		',' pa2 = param_assignment {
            $ListOfParamAssignmentsRet.addParamAssignment($pa2.ParamAssignmentRet);
        }
	)*;

block_label
	returns[BlockLabel BlockLabelRet]:
	{ $BlockLabelRet = new BlockLabel(); } id = identifier ':' {
          $BlockLabelRet.setIdentifier($id.IdentifierRet);
          $BlockLabelRet.setLine($id.IdentifierRet.getLine());
      };

continuous_set
	returns[ContinuousSet ContinuousSetRet]:
	{ $ContinuousSetRet = new ContinuousSet(); } s = 'set' la = list_of_net_assignments {
          $ContinuousSetRet.setListOfNetAssignments($la.ListOfNetAssignmentsRet);
          $ContinuousSetRet.setLine($s.line);
      } ';';

loop_statement
	returns[LoopStatement LoopStatementRet]:
	{
          $LoopStatementRet = new ForeverLoop();
      } f = 'forever' stmt = statement_or_null {
          ((ForeverLoop)$LoopStatementRet).setStatement($stmt.StatementRet);
          $LoopStatementRet.setLine($f.line);
      }
	| {
          $LoopStatementRet = new RepeatLoop();
      } r = 'repeat' '(' exp = expression ')' period = period_declaration stmt = statement_or_null {
          ((RepeatLoop)$LoopStatementRet).setExpression($exp.ExpressionRet);
          ((RepeatLoop)$LoopStatementRet).setPeriodDeclaration($period.PeriodDeclarationRet);
          ((RepeatLoop)$LoopStatementRet).setStatement($stmt.StatementRet);
          $LoopStatementRet.setLine($r.line);
      }
	| {
          $LoopStatementRet = new WhileLoop();
      } d = 'do' stmt = statement_or_null 'while' '(' exp = expression ')' ';' {
          ((WhileLoop)$LoopStatementRet).setExpression($exp.ExpressionRet);
          ((WhileLoop)$LoopStatementRet).setStatement($stmt.StatementRet);
          $LoopStatementRet.setLine($d.line);
      };
period_declaration
	returns[PeriodDeclaration PeriodDeclarationRet]:
	{$PeriodDeclarationRet=new PeriodDeclaration();} 'period' '=' e = expression {
        $PeriodDeclarationRet.setExpression($e.ExpressionRet);
        $PeriodDeclarationRet.setLine($e.ExpressionRet.getLine());
    };

identifier
	returns[Identifier IdentifierRet]:
	(
		s = SIMPLE_IDENTIFIER {$IdentifierRet=new Identifier($s.text,$s.line);}
	)
	|
	(
		e = ESCAPED_IDENTIFIER {$IdentifierRet=new Identifier($e.text,$e.line);}
	);

port_decl
	returns[PortDecl PortDeclRet]:
	{$PortDeclRet=new PortDecl();} port_prefix
	(
		pd = port_direction { $PortDeclRet.setPortDirection($pd.PortDirectionRet); }
	)?
	(
		dt = data_type { $PortDeclRet.setType($dt.DataTypeRet); }
	)? id = identifier { $PortDeclRet.setIdentifier($id.IdentifierRet); }
	(
		'=' ce = constant_expression { $PortDeclRet.setInitialValue($ce.ExpressionRet); }
	)?
	| port_prefix
	(
		pd = port_direction { $PortDeclRet.setPortDirection($pd.PortDirectionRet); }
	)? idt = implicit_data_type { $PortDeclRet.setType($idt.ImplicitDataTypeRet); } id = identifier
		{ $PortDeclRet.setIdentifier($id.IdentifierRet); }
	(
		ud = unpacked_dimension { $PortDeclRet.addUnpackedDimension($ud.ExpressionRet); }
	)*
	(
		'=' ce = constant_expression { $PortDeclRet.setInitialValue($ce.ExpressionRet); }
	)?
	| port_prefix
	(
		pd = port_direction { $PortDeclRet.setPortDirection($pd.PortDirectionRet); }
	)? nt = net_type { $PortDeclRet.setNetType($nt.NetTypeRet); }
	(
		dti = data_type_or_implicit { $PortDeclRet.setType($dti.DataTypeOrImplicitRet); }
	)? id = identifier { $PortDeclRet.setIdentifier($id.IdentifierRet); }
	(
		ud = unpacked_dimension { $PortDeclRet.addUnpackedDimension($ud.ExpressionRet); }
	)*
	(
		'=' ce = constant_expression { $PortDeclRet.setInitialValue($ce.ExpressionRet); }
	)?;

for_loop_statement
	returns[ForLoopStatement ForLoopStatementRet]:
	{$ForLoopStatementRet = new ForLoopStatement();} f = FOR LP na = index_assignment {
        $ForLoopStatementRet.setIndexAssignment($na.IndexAssignmentRet);
        $ForLoopStatementRet.setLine($f.line);
    } SC ex = expression {
        $ForLoopStatementRet.setCondition($ex.ExpressionRet);
    } SC
	(
		ide = inc_or_dec_expression { $ForLoopStatementRet.setStep($ide.ExpressionRet); }
		| ia = index_assignment { $ForLoopStatementRet.setStep($ia.IndexAssignmentRet); }
	) RP DO
	(
		CL id = identifier { $ForLoopStatementRet.setLoopIdentifier($id.IdentifierRet); }
	)?
	(
		cpii = component_program_interface_instantiation {
            $ForLoopStatementRet.addInstantiation($cpii.ComponentProgramInterfaceInstantiationRet);
        }
	)* ENDDO;

component_common_item
	returns[ComponentCommonItem ComponentCommonItemRet]:
	{ 
        $ComponentCommonItemRet = new ComponentCommonItem();
    } cid = component_item_declaration {
        $ComponentCommonItemRet = $cid.ComponentItemDeclarationRet;
    }
	| cpi = component_program_interface_instantiation {
        $ComponentCommonItemRet = $cpi.ComponentProgramInterfaceInstantiationRet;
    }
	| cs = continuous_set {
        $ComponentCommonItemRet = $cs.ContinuousSetRet;
    };

data_type
	returns[DataType DataTypeRet]:
	{$DataTypeRet = new DataType();} it = integer_type { $DataTypeRet.setSubType($it.IntegerTypeRet); 
		}
	(
		s = signing { $DataTypeRet.setSigning($s.text); }
	)?
	(
		pd = packed_dimension { $DataTypeRet.addPackedDimension($pd.ExpressionRet); }
	)*
	| nit = non_integer_type { $DataTypeRet = new DataType(); $DataTypeRet.setSubType($nit.NonIntegerTypeRet); 
		}
	| 'string' { $DataTypeRet = new DataType(); $DataTypeRet.setStringType("string"); }
	| id = identifier { $DataTypeRet = new DataType(); $DataTypeRet.setSubType($id.IdentifierRet); }
	(
		pd = packed_dimension { $DataTypeRet.addPackedDimension($pd.ExpressionRet); }
	)+;

parameter_value_assignment
	returns[List<ParamAssignment> ListRet]:
	'#' '('
	(
		l = list_of_parameter_assignments { $ListRet = $l.ListRet; }
	)? ')';

constant_bit_select
	returns[List<Expression> IndicesRet]
	locals[List<Expression> list = new ArrayList<Expression>()]:
	(
		'[' e = constant_expression ']' { $list.add($e.ExpressionRet); }
	)+ { $IndicesRet = $list; };

constant_select
	returns[List<Expression> IndicesRet]:
	cbs = constant_bit_select { $IndicesRet = $cbs.IndicesRet; };

constant_primary
	returns[Expression ExpressionRet]:
	pl = primary_literal { $ExpressionRet = $pl.ExpressionRet; }
	| id = identifier s = constant_select? {
        Expression current = $id.IdentifierRet;
        if (_localctx.s != null && $s.IndicesRet != null) {
             for (Expression index : $s.IndicesRet) {
                 current = new ArrayAccessExpression(current, index);
             }
        }
        $ExpressionRet = current;
    }
	| c = constant_concatenation { $ExpressionRet = $c.ExpressionRet; }
	| id = identifier '(' args = arg_list ')' { $ExpressionRet = new CallExpression($id.IdentifierRet, $args.ListRet); 
		}
	| '(' m = constant_mintypmax_expression ')' { $ExpressionRet = $m.ExpressionRet; }
	| p = constant_primary '\'' '(' e = constant_expression ')' { $ExpressionRet = new CastExpression($p.ExpressionRet, $e.ExpressionRet); 
		}
	| ct = cast_type '\'' '(' e = constant_expression ')' { $ExpressionRet = new CastExpression($ct.ExpressionRet, $e.ExpressionRet); 
		}
	| 'null' { $ExpressionRet = new LiteralExpression("null", "NULL"); };

primary
	returns[Expression ExpressionRet]:
	pl = primary_literal { $ExpressionRet = $pl.ExpressionRet; }
	| hi = hierarchical_identifier s = select_? {
        Expression current = $hi.IdentifierRet;
        if (_localctx.s != null && $s.SelectorsRet != null) {
            for (Expression selector : $s.SelectorsRet) {
                current = new ArrayAccessExpression(current, selector);
            }
        }
        $ExpressionRet = current;
    }
	| c = concatenation { $ExpressionRet = $c.ExpressionRet; }
	| identifier
	(
		arg_list
	) 
	| id = identifier args = arg_list { $ExpressionRet = new CallExpression($id.IdentifierRet, $args.ListRet); 
		}
	| '(' m = mintypmax_expression ')' { $ExpressionRet = $m.ExpressionRet; }
	| p = primary '\'' '(' e = expression ')' { $ExpressionRet = new CastExpression($p.ExpressionRet, $e.ExpressionRet); 
		}
	| ct = cast_type '\'' '(' e = expression ')' { $ExpressionRet = new CastExpression($ct.ExpressionRet, $e.ExpressionRet); 
		};
primary_literal
	returns[Expression ExpressionRet]:
	n = number { $ExpressionRet = new LiteralExpression($n.text, "NUMBER"); }
	| t = time_literal { $ExpressionRet = new LiteralExpression($t.text, "TIME"); }
	| u = unbased_unsized_literal { $ExpressionRet = new LiteralExpression($u.text, "UNBASED"); }
	| s = string_literal { $ExpressionRet = new LiteralExpression($s.text, "STRING"); };

bit_select
	returns[List<Expression> IndicesRet]
	locals[List<Expression> list = new ArrayList<Expression>()]:
	(
		'[' e = expression ']' { $list.add($e.ExpressionRet); }
	)+ { $IndicesRet = $list; };

variable_lvalue
	returns[Expression ExpressionRet]:
	hi = hierarchical_identifier s = select_? {
        Expression current = $hi.IdentifierRet;
        if (_localctx.s != null && $s.SelectorsRet != null) {
            for (Expression selector : $s.SelectorsRet) {
                current = new ArrayAccessExpression(current, selector);
            }
        }
        $ExpressionRet = current;
    }
	| '{' v1 = variable_lvalue { List<Expression> exprs = new ArrayList<>(); exprs.add($v1.ExpressionRet); 
		}
	(
		',' v2 = variable_lvalue { exprs.add($v2.ExpressionRet); }
	)* '}' { $ExpressionRet = new ConcatenationExpression(exprs); };

index_assignment
	returns[IndexAssignment IndexAssignmentRet]:
	{$IndexAssignmentRet = new IndexAssignment();} id = identifier {
        $IndexAssignmentRet.setIdentifier($id.IdentifierRet);
        $IndexAssignmentRet.setLine($id.IdentifierRet.getLine());
    } '=' exp = expression {
        $IndexAssignmentRet.setExpression($exp.ExpressionRet);
    };

index_declaration
	returns[IndexDeclaration IndexDeclarationRet]:
	{$IndexDeclarationRet = new IndexDeclaration();} s = 'index' id = identifier {
        $IndexDeclarationRet.setIdentifier($id.IdentifierRet);
        $IndexDeclarationRet.setLine($s.line);
    };

implicit_data_type
	returns[ImplicitDataType ImplicitDataTypeRet]:
	{$ImplicitDataTypeRet = new ImplicitDataType();}
	(
		pd = packed_dimension { $ImplicitDataTypeRet.addPackedDimension($pd.ExpressionRet); }
	)+
	| {$ImplicitDataTypeRet = new ImplicitDataType();} s = signing { $ImplicitDataTypeRet.setSigning($s.text); 
		}
	(
		pd = packed_dimension { $ImplicitDataTypeRet.addPackedDimension($pd.ExpressionRet); }
	)*;

cast_type
	returns[Expression ExpressionRet]:
	it = integer_type { $ExpressionRet = $it.IntegerTypeRet; }
	| nit = non_integer_type { $ExpressionRet = $nit.NonIntegerTypeRet; }
	| s = signing { $ExpressionRet = new LiteralExpression($s.text, "SIGNING"); }
	| 'string' { $ExpressionRet = new LiteralExpression("string", "TYPE"); }
	| 'const' { $ExpressionRet = new LiteralExpression("const", "TYPE"); };

list_of_net_decl_assignments
	returns[ListOfNetDeclAssignments ListOfNetDeclAssignmentsRet]:
	{ $ListOfNetDeclAssignmentsRet = new ListOfNetDeclAssignments(); } nd1 = net_decl_assignment {
          $ListOfNetDeclAssignmentsRet.addNetDeclAssignment($nd1.NetDeclAssignmentRet);
      }
	(
		',' nd2 = net_decl_assignment {
              $ListOfNetDeclAssignmentsRet.addNetDeclAssignment($nd2.NetDeclAssignmentRet);
          }
	)*;

unpacked_dimension
	returns[Expression ExpressionRet]:
	'[' r = constant_range ']' { $ExpressionRet = $r.RangeRet; }
	| '[' e = constant_expression ']' { $ExpressionRet = $e.ExpressionRet; };

param_assignment
	returns[ParamAssignment ParamAssignmentRet]:
	{$ParamAssignmentRet = new ParamAssignment();} id = identifier { $ParamAssignmentRet.setIdentifier($id.IdentifierRet); 
		}
	(
		ud = unpacked_dimension { $ParamAssignmentRet.addUnpackedDimension($ud.ExpressionRet); }
	)*
	(
		'=' cpe = constant_param_expression { $ParamAssignmentRet.setInitialValue($cpe.ExpressionRet); 
			}
	)?;

net_decl_assignment
	returns[NetDeclAssignment NetDeclAssignmentRet]:
	{ $NetDeclAssignmentRet = new NetDeclAssignment(); } id = identifier {
          $NetDeclAssignmentRet.setIdentifier($id.IdentifierRet);
      } unpacked_dimension*
	(
		'=' e = expression {
              $NetDeclAssignmentRet.setExpression($e.ExpressionRet);
          }
	)? {
          $NetDeclAssignmentRet.setLine($id.IdentifierRet.getLine());
      };

packed_dimension
	returns[Expression ExpressionRet]:
	'[' r = constant_range ']' { $ExpressionRet = $r.RangeRet; }
	| ud = unsized_dimension { $ExpressionRet = $ud.ExpressionRet; };

unsized_dimension
	returns[UnsizedDimension ExpressionRet]:
	'[' ']' { $ExpressionRet = new UnsizedDimension(); };

component_program_interface_instantiation
	returns[ComponentProgramInterfaceInstantiation ComponentProgramInterfaceInstantiationRet]:
	{$ComponentProgramInterfaceInstantiationRet = new ComponentProgramInterfaceInstantiation();} id
		= identifier { $ComponentProgramInterfaceInstantiationRet.setIdentifier($id.IdentifierRet); 
                       $ComponentProgramInterfaceInstantiationRet.setLine($id.IdentifierRet.getLine());
		}
	(
		pva = parameter_value_assignment { $ComponentProgramInterfaceInstantiationRet.setParameterAssignments($pva.ListRet); 
			}
	)? hi1 = hierarchical_instance { $ComponentProgramInterfaceInstantiationRet.addInstance($hi1.HierarchicalInstanceRet); 
		}
	(
		',' hi2 = hierarchical_instance { $ComponentProgramInterfaceInstantiationRet.addInstance($hi2.HierarchicalInstanceRet); 
			}
	)* ';';

list_of_parameter_assignments
	returns[List<ParamAssignment> ListRet]
	locals[List<ParamAssignment> list = new ArrayList<ParamAssignment>()]:
	pe = param_expression { 
           ParamAssignment pa1 = new ParamAssignment();
           pa1.setInitialValue($pe.ExpressionRet);
           $list.add(pa1);
        }
	(
		',' pe2 = param_expression {
           ParamAssignment pa2 = new ParamAssignment();
           pa2.setInitialValue($pe2.ExpressionRet);
           $list.add(pa2);
        }
	)* { $ListRet = $list; }
	| npa = named_parameter_assignment { $list.add($npa.ParamAssignmentRet); }
	(
		',' npa2 = named_parameter_assignment { $list.add($npa2.ParamAssignmentRet); }
	)* { $ListRet = $list; };

net_assignment
	returns[NetAssignment NetAssignmentRet]:
	{$NetAssignmentRet = new NetAssignment();} lvl = variable_lvalue { $NetAssignmentRet.setLValue($lvl.ExpressionRet); 
		} '=' e = expression { $NetAssignmentRet.setExpression($e.ExpressionRet); };

name_of_instance
	returns[HierarchicalInstance HierarchicalInstanceRet]:
	{$HierarchicalInstanceRet = new HierarchicalInstance();} id = identifier { $HierarchicalInstanceRet.setInstanceName($id.IdentifierRet); 
		}
	(
		ud = unpacked_dimension { $HierarchicalInstanceRet.addUnpackedDimension($ud.ExpressionRet); 
			}
	)*;

list_of_port_connections
	returns[List<PortConnection> ListRet]
	locals[List<PortConnection> list = new ArrayList<PortConnection>()]:
	npc = named_port_connection { $list.add($npc.PortConnectionRet); }
	(
		',' npc2 = named_port_connection { $list.add($npc2.PortConnectionRet); }
	)* { $ListRet = $list; };

hierarchical_instance
	returns[HierarchicalInstance HierarchicalInstanceRet]:
	noi = name_of_instance { $HierarchicalInstanceRet = $noi.HierarchicalInstanceRet; } '(' lp =
		list_of_port_connections { $HierarchicalInstanceRet.setPortConnections($lp.ListRet); } ')';

list_of_net_assignments
	returns[ListOfNetAssignments ListOfNetAssignmentsRet]:
	{$ListOfNetAssignmentsRet = new ListOfNetAssignments();} na1 = net_assignment { $ListOfNetAssignmentsRet.addNetAssignment($na1.NetAssignmentRet); 
		}
	(
		',' na2 = net_assignment { $ListOfNetAssignmentsRet.addNetAssignment($na2.NetAssignmentRet); 
			}
	)*;

named_port_connection
	returns[PortConnection PortConnectionRet]:
	{$PortConnectionRet = new PortConnection();} '.' id = identifier { $PortConnectionRet.setPortName($id.IdentifierRet); 
		}
	(
		pa = port_assign { $PortConnectionRet.setExpression($pa.ExpressionRet); }
	)?
	| '.*' { $PortConnectionRet = new PortConnection(); $PortConnectionRet.setWildcard(true); };

port_assign
	returns[Expression ExpressionRet]:
	'('
	(
		e = expression { $ExpressionRet = $e.ExpressionRet; }
	)? ')';

concatenation
	returns[Expression ExpressionRet]
	locals[List<Expression> exprs = new ArrayList<Expression>()]:
	'{' e1 = expression { $exprs.add($e1.ExpressionRet); }
	(
		',' e2 = expression { $exprs.add($e2.ExpressionRet); }
	)* '}' { $ExpressionRet = new ConcatenationExpression($exprs); }
	| '{' '}' { $ExpressionRet = new ConcatenationExpression(new ArrayList<Expression>()); };

operator_assignment
	returns[Expression ExpressionRet]:
	vl = variable_lvalue ao = assignment_operator e = expression {
         $ExpressionRet = new AssignmentExpression($vl.ExpressionRet, $e.ExpressionRet, $ao.text);
    };

constant_concatenation
	returns[Expression ExpressionRet]
	locals[List<Expression> exprs = new ArrayList<Expression>()]:
	'{' e1 = constant_expression { $exprs.add($e1.ExpressionRet); }
	(
		',' e2 = constant_expression { $exprs.add($e2.ExpressionRet); }
	)* '}' { $ExpressionRet = new ConcatenationExpression($exprs); };

arg_list
	returns[List<Expression> ListRet]:
	'(' l = list_of_arguments ')' { $ListRet = $l.ListRet; };

constant_expression
	returns[Expression ExpressionRet]:
	p = constant_primary { $ExpressionRet = $p.ExpressionRet; }
	| uo = unary_operator p2 = constant_primary { $ExpressionRet = new UnaryExpression($p2.ExpressionRet, $uo.text); 
		}
	| e1 = constant_expression '**' e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, "**"); 
		}
	| e1 = constant_expression op =
	(
		'*'
		| '/'
		| '%'
	) e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text); 
		}
	| e1 = constant_expression op =
	(
		'+'
		| '-'
	) e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text); 
		}
	| e1 = constant_expression op =
	(
		'>>'
		| '<<'
		| '>>>'
		| '<<<'
	) e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text); 
		}
	| e1 = constant_expression op =
	(
		'<'
		| '<='
		| '>'
		| '>='
	) e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text); 
		}
	| e1 = constant_expression op =
	(
		'=='
		| '!='
		| '==='
		| '!=='
		| '==?'
		| '!=?'
	) e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text); 
		}
	| e1 = constant_expression '&' e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, "&"); 
		}
	| e1 = constant_expression op =
	(
		'^'
		| '^~'
		| '~^'
	) e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text); 
		}
	| e1 = constant_expression '|' e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, "|"); 
		}
	| e1 = constant_expression '&&' e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, "&&"); 
		}
	| e1 = constant_expression '||' e2 = constant_expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, "||"); 
		}
	| <assoc = right> e1 = constant_expression '?' e2 = constant_expression ':' e3 =
		constant_expression { $ExpressionRet = new TernaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $e3.ExpressionRet); 
		};

constant_mintypmax_expression
	returns[Expression ExpressionRet]:
	e1 = constant_expression
	(
		':' e2 = constant_expression ':' e3 = constant_expression { $ExpressionRet = new MintypmaxExpression($e1.ExpressionRet, $e2.ExpressionRet, $e3.ExpressionRet); 
			}
	)? { if ($ExpressionRet == null) $ExpressionRet = $e1.ExpressionRet; };

constant_param_expression
	returns[Expression ExpressionRet]:
	cm = constant_mintypmax_expression { $ExpressionRet = $cm.ExpressionRet; }
	| dt = data_type { $ExpressionRet = $dt.DataTypeRet; };

operator_variable_lvalue
	returns[Expression ExpressionRet]:
	id = hierarchical_identifier { $ExpressionRet = $id.IdentifierRet; }
	(
		'[' e = expression ']' { $ExpressionRet = new ArrayAccessExpression($ExpressionRet, $e.ExpressionRet); 
			}
	)*;

inc_or_dec_expression
	returns[Expression ExpressionRet]:
	op = inc_or_dec_operator v = variable_lvalue {
        $ExpressionRet = new IncDecExpression($v.ExpressionRet, $op.text, true);
    }
	| v = variable_lvalue op = inc_or_dec_operator {
        $ExpressionRet = new IncDecExpression($v.ExpressionRet, $op.text, false);
    };

mintypmax_expression
	returns[Expression ExpressionRet]:
	e1 = expression
	(
		':' e2 = expression ':' e3 = expression { $ExpressionRet = new MintypmaxExpression($e1.ExpressionRet, $e2.ExpressionRet, $e3.ExpressionRet); 
			}
	)? { if ($ExpressionRet == null) $ExpressionRet = $e1.ExpressionRet; };

part_select_range
	returns[RangeExpression RangeRet]:
	c = constant_range { $RangeRet = $c.RangeRet; };

select_
	returns[List<Expression> SelectorsRet]
	locals[List<Expression> list = new ArrayList<Expression>()]:
	'[' r = part_select_range ']' { $list.add($r.RangeRet); $SelectorsRet = $list; }
	| bs = bit_select { $list.addAll($bs.IndicesRet); }
	(
		'[' r2 = part_select_range ']' { $list.add($r2.RangeRet); }
	)? { $SelectorsRet = $list; };

constant_range
	returns[RangeExpression RangeRet]:
	c1 = constant_expression ':' c2 = constant_expression { $RangeRet = new RangeExpression($c1.ExpressionRet, $c2.ExpressionRet); 
		};

expression
	returns[Expression ExpressionRet]:
	p = primary { $ExpressionRet = $p.ExpressionRet; }
	| '(' oa = operator_assignment ')' { $ExpressionRet = $oa.ExpressionRet; }
	| uo = unary_operator p2 = primary { $ExpressionRet = new UnaryExpression($p2.ExpressionRet, $uo.text);
		}
	| ide = inc_or_dec_expression { $ExpressionRet = $ide.ExpressionRet; }
	| e1 = expression op = '**' e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op =
	(
		'*'
		| '/'
		| '%'
	) e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op =
	(
		'+'
		| '-'
	) e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op =
	(
		'>>'
		| '<<'
		| '>>>'
		| '<<<'
	) e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op =
	(
		'<'
		| '<='
		| '>'
		| '>='
	) e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op =
	(
		'=='
		| '!='
		| '==='
		| '!=='
		| '==?'
		| '!=?'
	) e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op = '&' e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op =
	(
		'^'
		| '^~'
		| '~^'
	) e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op = '|' e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op = '&&' e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| e1 = expression op = '||' e2 = expression { $ExpressionRet = new BinaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $op.text);
		}
	| <assoc = right> e1 = expression '?' e2 = expression ':' e3 = expression { $ExpressionRet = new TernaryExpression($e1.ExpressionRet, $e2.ExpressionRet, $e3.ExpressionRet);
		};

param_expression
	returns[Expression ExpressionRet]:
	m = mintypmax_expression { $ExpressionRet = $m.ExpressionRet; }
	| dt = data_type { $ExpressionRet = $dt.DataTypeRet; };

subroutine_call
	returns[CallExpression CallExpressionRet]:
	id = identifier
	(
		args = arg_list { $CallExpressionRet = new CallExpression($id.IdentifierRet, $args.ListRet); 
			}
		| { $CallExpressionRet = new CallExpression($id.IdentifierRet); }
	);

list_of_arguments
	returns[List<Expression> ListRet]
	locals[List<Expression> exprs = new ArrayList<Expression>()]:
	o = ordered_arg { if ($o.ExpressionRet != null) $exprs.add($o.ExpressionRet); }
	(
		',' o2 = ordered_arg { if ($o2.ExpressionRet != null) $exprs.add($o2.ExpressionRet); }
	)*
	(
		',' na = named_arg { $exprs.add($na.ExpressionRet); }
	)* { $ListRet = $exprs; }
	| na1 = named_arg { $exprs.add($na1.ExpressionRet); }
	(
		',' na2 = named_arg { $exprs.add($na2.ExpressionRet); }
	)* { $ListRet = $exprs; };

ordered_arg
	returns[Expression ExpressionRet]:
	e = expression? { if ($e.ctx != null) $ExpressionRet = $e.ExpressionRet; };

named_arg
	returns[Expression ExpressionRet]:
	'.' id = identifier '(' e = expression? ')' {
        Expression argExpr = null;
        if ($e.ctx != null) argExpr = $e.ExpressionRet;
        $ExpressionRet = new NamedArgument($id.IdentifierRet, argExpr);
    };

hierarchical_identifier
	returns[Identifier IdentifierRet]:
	id = identifier { $IdentifierRet = $id.IdentifierRet; };

// ---------------------------------------------------------------------------------------------------------------

signing: 'signed' | 'unsigned';

simple_type: integer_type | non_integer_type | identifier;

named_parameter_assignment
	returns[ParamAssignment ParamAssignmentRet]:
	'.' id = identifier '('
	(
		e = param_expression
	)? ')' {
         $ParamAssignmentRet = new ParamAssignment();
         $ParamAssignmentRet.setIdentifier($id.IdentifierRet);
         if ($e.ctx != null) {
             $ParamAssignmentRet.setInitialValue($e.ExpressionRet);
         }
    };

assignment_operator:
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
	| '>>>=';

nonblocking_assignment
	returns[Statement StatementRet]:
	vl = variable_lvalue '<=' e = expression {
        $StatementRet = new NonBlockingAssignment($vl.ExpressionRet, $e.ExpressionRet);
    };

variable_assignment
	returns[Statement StatementRet]:
	vl = variable_lvalue '=' e = expression {
        $StatementRet = new BlockingAssignment($vl.ExpressionRet, $e.ExpressionRet);
    };

statement_or_null
	returns[Statement StatementRet]:
	s = statement { $StatementRet = $s.StatementRet; }
	| ';' { $StatementRet = new EmptyStatement(); };

statement
	returns[Statement StatementRet]:
	block_label? si = statement_item {
        $StatementRet = $si.StatementRet;
    };

statement_item
	returns[Statement StatementRet]:
	oa = operator_assignment ';' { $StatementRet = new ExpressionStatement($oa.ExpressionRet); }
	| na = nonblocking_assignment ';' { $StatementRet = $na.StatementRet; }
	| cs = conditional_statement { $StatementRet = $cs.StatementRet; }
	| ide = inc_or_dec_expression ';' { $StatementRet = new ExpressionStatement($ide.ExpressionRet); 
		}
	| scs = subroutine_call_statement { $StatementRet = $scs.StatementRet; }
	| ls = loop_statement { $StatementRet = $ls.LoopStatementRet; };

conditional_statement
	returns[Statement StatementRet]:
	'if' '(' cond = cond_predicate ')' thenStmt = statement_or_null
	(
		'else' elseStmt = statement_or_null
	)? {
        Statement elseBody = null;
        if ($elseStmt.ctx != null) elseBody = $elseStmt.StatementRet;
        $StatementRet = new ConditionalStatement($cond.ExpressionRet, $thenStmt.StatementRet, elseBody);
    };

cond_predicate
	returns[Expression ExpressionRet]:
	e = expression { $ExpressionRet = $e.ExpressionRet; }
	(
		'&&&' expression
	)*; // Only taking first expression for simplicity as AST expects single condition

subroutine_call_statement
	returns[Statement StatementRet]:
	sc = subroutine_call ';' { $StatementRet = new ExpressionStatement($sc.CallExpressionRet); }
	| 'void' '\'' '(' sc2 = subroutine_call ')' ';' { $StatementRet = new ExpressionStatement($sc2.CallExpressionRet); 
		};

unary_operator:
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
	| '^~';

inc_or_dec_operator: '++' | '--';

number: integral_number | real_number;

integral_number: decimal_number | binary_number;

decimal_number: size? decimal_base | unsigned_number;

binary_number: size? binary_base;

time_literal: TIME_LITERAL;

size: UNSIGNED_NUMBER;

real_number: FIXED_POINT_NUMBER | EXPONENTIAL_NUMBER;

unsigned_number: UNSIGNED_NUMBER;

decimal_base: DECIMAL_BASE;

binary_base: BINARY_BASE;

unbased_unsized_literal: UNBASED_UNSIZED_LITERAL;

string_literal: STRING_LITERAL;