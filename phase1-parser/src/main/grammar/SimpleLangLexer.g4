lexer grammar SimpleLangLexer;

@header {
package main.grammar;
}

AND: 'and';

LOGIC: 'logic';


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

PERIOD: 'period';

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