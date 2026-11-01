package main.visitor;

import main.ast.nodes.ComponentDeclaration.ConfigHeaderDeclaration;
import main.ast.nodes.SourceText;
import main.ast.nodes.ComponentDeclaration.ComponentDeclaration;
import main.ast.nodes.ComponentItem.ComponentItem;
import main.ast.nodes.ComponentItem.NetDeclaration.*;
import main.ast.nodes.ComponentItem.LoopStatement.StatementOrNull;
import main.ast.nodes.Expression.*;
import main.scheduler.ScheduledEvent;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.item.SymbolTableItem;
import main.TypeCheck.Type;

import java.io.*;
import java.util.ArrayList;

public class CodeGenerator extends Visitor<String> {
    private final String outputPath;
    private FileWriter writer;
    private final ArrayList<ScheduledEvent> eventQueue;
    private boolean isPrint = false;

    public CodeGenerator(ArrayList<ScheduledEvent> eventQueue) {
        this.outputPath = "./codeGenOutput/";
        this.eventQueue = eventQueue;
        prepareOutputDirectory();
    }

    private void prepareOutputDirectory() {
        File dir = new File(outputPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private java.util.Map<String, Integer> slotMap = new java.util.HashMap<>();
    private int nextSlot = 1;
    private int labelCount = 0;

    private Type getExprType(Expression expr) {
        TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
        return expr.accept(typeChecker);
    }

    private boolean isFloat(String varName) {
        try {
            SymbolTableItem item = SymbolTable.top
                    .getItem(main.symbolTable.item.DataDecSymbolTableItem.START_KEY + varName);
            if (item != null && item.getType() != null) {
                return item.getType().baseType == Type.BaseType.REAL;
            }
        } catch (ItemNotFoundException e) {
            // Ignore
        }
        return false;
    }

    @Override
    public String visit(SourceText sourceText) {
        try {
            initializeWriter();
            writeClassHeader();
            writeMainMethodHeader();
            for (ComponentDeclaration cd : sourceText.getComponentDeclarationList()) {
                if (cd instanceof ConfigHeaderDeclaration) {
                    cd.accept(this);
                }
            }
            writeMainMethodFooter();
            closeWriter();
        } catch (IOException e) {
        }
        return null;
    }

    private void initializeWriter() throws IOException {
        writer = new FileWriter(outputPath + "Main.j");
    }

    private void writeClassHeader() throws IOException {
        writer.write(".class public Main\n");
        writer.write(".super java/lang/Object\n\n");
    }

    private void writeMainMethodHeader() throws IOException {
        writer.write(".method public static main([Ljava/lang/String;)V\n");
        writer.write("    .limit stack 128\n");
        writer.write("    .limit locals 128\n\n");
    }

    private void writeMainMethodFooter() throws IOException {
        writer.write("\n    return\n");
        writer.write(".end method\n");
    }

    private void closeWriter() throws IOException {
        writer.close();
    }

    @Override
    public String visit(ConfigHeaderDeclaration cd) {
        SymbolTable.push(cd.getSymbolTable());
        ArrayList<main.Code.Code> codes = new ArrayList<>();

        for (String key : cd.getSymbolTable().items.keySet()) {
            if (key.startsWith(main.symbolTable.item.DataDecSymbolTableItem.START_KEY)) {
                String varName = key.substring(main.symbolTable.item.DataDecSymbolTableItem.START_KEY.length());
                slotMap.put(varName + "_old", nextSlot++);
                slotMap.put(varName + "_new", nextSlot++);
            }
        }

        if (cd.getComponentItemList() != null) {
            for (ComponentItem item : cd.getComponentItemList()) {
                if (item instanceof NetTypeNetDeclaration) {
                    String initCode = item.accept(this);
                    try {
                        if (initCode != null)
                            writer.write(initCode);
                    } catch (IOException e) {
                    }
                }
            }
        }

        long maxTime = 0;
        for (ScheduledEvent event : eventQueue) {
            if (event.getTime() > maxTime) {
                maxTime = event.getTime();
            }
        }

        for (long globalTime = 0; globalTime <= maxTime; globalTime++) {
            codes.clear();
            if (cd.getComponentItemList() != null) {
                for (ComponentItem item : cd.getComponentItemList()) {
                    if (item instanceof main.ast.nodes.ComponentItem.LoopStatement.RepeatLoop) {
                        main.ast.nodes.ComponentItem.LoopStatement.RepeatLoop repeatLoop = (main.ast.nodes.ComponentItem.LoopStatement.RepeatLoop) item;
                        String codeStr = repeatLoop.accept(this);
                        codes.add(new main.Code.Code(repeatLoop.getStatementOrNull(), codeStr));
                    }
                }
            }

            for (ScheduledEvent event : eventQueue) {
                if (event.getTime() == globalTime && event.getStatement() != null) {
                    for (main.Code.Code code : codes) {
                        if (code.getNode() == event.getStatement()) {
                            try {
                                if (code.getCode() != null)
                                    writer.write(code.getCode());
                            } catch (IOException e) {
                            }
                            break;
                        }
                    }
                }
            }

            try {
                for (String key : cd.getSymbolTable().items.keySet()) {
                    if (key.startsWith(main.symbolTable.item.DataDecSymbolTableItem.START_KEY)) {
                        String varName = key.substring(main.symbolTable.item.DataDecSymbolTableItem.START_KEY.length());
                        Integer oldSlot = slotMap.get(varName + "_old");
                        Integer newSlot = slotMap.get(varName + "_new");
                        if (oldSlot != null && newSlot != null) {
                            if (isFloat(varName)) {
                                writer.write("    fload " + newSlot + "\n");
                                writer.write("    fstore " + oldSlot + "\n");
                            } else {
                                writer.write("    iload " + newSlot + "\n");
                                writer.write("    istore " + oldSlot + "\n");
                            }
                        }
                    }
                }
            } catch (IOException e) {
            }
        }

        SymbolTable.pop();
        return null;
    }

    @Override
    public String visit(NetTypeNetDeclaration netDecl) {
        StringBuilder initCode = new StringBuilder();
        if (netDecl.getListOfNetDeclAssignments() != null) {
            for (NetDeclAssignment assignment : netDecl.getListOfNetDeclAssignments()) {
                if (assignment.getExpression() != null) {
                    String exprCode = assignment.getExpression().accept(this);
                    if (exprCode != null) {
                        initCode.append(exprCode);
                        String name = assignment.getIdentifier().getName();

                        Type exprType = getExprType(assignment.getExpression());
                        boolean isReal = isFloat(name);
                        Integer oldSlot = slotMap.get(name + "_old");
                        Integer newSlot = slotMap.get(name + "_new");

                        if (isReal && exprType != null && exprType.baseType != Type.BaseType.REAL) {
                            initCode.append("    i2f\n");
                        } else if (!isReal && exprType != null && exprType.baseType == Type.BaseType.REAL) {
                            initCode.append("    f2i\n");
                        }

                        if (oldSlot != null && newSlot != null) {
                            String storeIns = isReal ? "fstore " : "istore ";
                            String loadIns = isReal ? "fload " : "iload ";
                            initCode.append("    ").append(storeIns).append(newSlot).append("\n");
                            initCode.append("    ").append(loadIns).append(newSlot).append("\n");
                            initCode.append("    ").append(storeIns).append(oldSlot).append("\n");
                        }
                    }
                } else {
                    String name = assignment.getIdentifier().getName();
                    boolean isReal = isFloat(name);
                    Integer oldSlot = slotMap.get(name + "_old");
                    Integer newSlot = slotMap.get(name + "_new");
                    if (oldSlot != null && newSlot != null) {
                        if (isReal) {
                            initCode.append("    ldc 0.0\n");
                            initCode.append("    fstore ").append(newSlot).append("\n");
                            initCode.append("    ldc 0.0\n");
                            initCode.append("    fstore ").append(oldSlot).append("\n");
                        } else {
                            initCode.append("    ldc 0\n");
                            initCode.append("    istore ").append(newSlot).append("\n");
                            initCode.append("    ldc 0\n");
                            initCode.append("    istore ").append(oldSlot).append("\n");
                        }
                    }
                }
            }
        }
        return initCode.toString();
    }

    @Override
    public String visit(main.ast.nodes.ComponentItem.LoopStatement.RepeatLoop repeatLoop) {
        if (repeatLoop.getStatementOrNull() != null) {
            return repeatLoop.getStatementOrNull().accept(this);
        }
        return "";
    }

    @Override
    public String visit(NonEmptyStatementOrNull stmt) {
        if (stmt.getStatement() != null) {
            return stmt.getStatement().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.Statement stmt) {
        if (stmt.geStatementItem() != null) {
            return stmt.geStatementItem().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.OperatorAssignmentStatement stmt) {
        if (stmt.getOperatorAssignment() != null) {
            return stmt.getOperatorAssignment().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.NonblockingAssignmentStatement stmt) {
        if (stmt.getNonblockingAssignment() != null) {
            return stmt.getNonblockingAssignment().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.SimpleSubroutineCallStatement stmt) {
        if (stmt.getSubroutineCall() != null) {
            return stmt.getSubroutineCall().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.VoidSubroutineCallStatement stmt) {
        if (stmt.getSubroutineCall() != null) {
            return stmt.getSubroutineCall().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.OperatorAssignment oa) {
        StringBuilder code = new StringBuilder();
        if (oa.getRightExpression() != null) {
            String r = oa.getRightExpression().accept(this);
            if (r == null)
                r = "/* null right expr: " + oa.getRightExpression().getClass().getName() + " */\n";
            code.append(r);
        }
        if (oa.getVariableLvalue() instanceof main.ast.nodes.Expression.HierarchicalVariableLvalue) {
            String name = ((main.ast.nodes.Expression.HierarchicalVariableLvalue) oa.getVariableLvalue())
                    .getHierarchicalIdentifier().getName();

            Type exprType = getExprType(oa.getRightExpression());
            boolean isReal = isFloat(name);
            if (isReal && exprType != null && exprType.baseType != Type.BaseType.REAL) {
                code.append("    i2f\n");
            } else if (!isReal && exprType != null && exprType.baseType == Type.BaseType.REAL) {
                code.append("    f2i\n");
            }

            Integer nextSlot = slotMap.get(name + "_new");
            if (nextSlot != null) {
                code.append("    ").append(isReal ? "fstore " : "istore ").append(nextSlot).append("\n");
            }
        } else if (oa.getVariableLvalue() != null) {
            code.append("    /* unhandled lvalue: ").append(oa.getVariableLvalue().getClass().getName())
                    .append(" */\n");
        }
        return code.toString();
    }

    @Override
    public String visit(main.ast.nodes.Expression.NonblockingAssignment na) {
        StringBuilder code = new StringBuilder();
        if (na.getExpression() != null) {
            String r = na.getExpression().accept(this);
            if (r == null)
                r = "/* null expr: " + na.getExpression().getClass().getName() + " */\n";
            code.append(r);
        }
        if (na.getVariableLvalue() instanceof main.ast.nodes.Expression.HierarchicalVariableLvalue) {
            String name = ((main.ast.nodes.Expression.HierarchicalVariableLvalue) na.getVariableLvalue())
                    .getHierarchicalIdentifier().getName();

            Type exprType = getExprType(na.getExpression());
            boolean isReal = isFloat(name);
            if (isReal && exprType != null && exprType.baseType != Type.BaseType.REAL) {
                code.append("    i2f\n");
            } else if (!isReal && exprType != null && exprType.baseType == Type.BaseType.REAL) {
                code.append("    f2i\n");
            }

            Integer nextSlot = slotMap.get(name + "_new");
            if (nextSlot != null) {
                code.append("    ").append(isReal ? "fstore " : "istore ").append(nextSlot).append("\n");
            }
        } else if (na.getVariableLvalue() != null) {
            code.append("    /* unhandled lvalue: ").append(na.getVariableLvalue().getClass().getName())
                    .append(" */\n");
        }
        return code.toString();
    }

    @Override
    public String visit(main.ast.nodes.Expression.SubroutineCall sc) {
        if (sc.getIdentifier() != null && sc.getIdentifier().getName().equals("display")) {
            if (sc.getArgList() != null) {
                return sc.getArgList().accept(this);
            }
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.ArgList argList) {
        if (argList.getListOfArguments() != null) {
            return argList.getListOfArguments().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.MixedListOfArguments mloa) {
        if (mloa.getOrderedArgs() != null && !mloa.getOrderedArgs().isEmpty()) {
            return mloa.getOrderedArgs().get(0).accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.OrderedArg oa) {
        if (oa.getExpression() != null) {
            return oa.getExpression().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.NonEmptyConcatenation nec) {
        StringBuilder code = new StringBuilder();
        int size = nec.getExpressions().size();
        for (int i = 0; i < size; i++) {
            code.append("    getstatic java/lang/System/out Ljava/io/PrintStream;\n");
            Type exprType = getExprType(nec.getExpressions().get(i));
            isPrint = true;
            String eStr = nec.getExpressions().get(i).accept(this);
            isPrint = false;
            if (eStr == null)
                eStr = "/* null concat expr: " + nec.getExpressions().get(i).getClass().getName() + " */\n";
            code.append(eStr);

            if (exprType != null && exprType.baseType == Type.BaseType.REAL) {
                String lNotZero = "Lnot_zero_" + (++labelCount);
                String lEnd = "Lend_print_" + (++labelCount);
                code.append("    dup\n");
                code.append("    fconst_0\n");
                code.append("    fcmpg\n");
                code.append("    ifne ").append(lNotZero).append("\n");
                code.append("    pop\n");
                code.append("    iconst_0\n");
                code.append("    invokevirtual java/io/PrintStream/print(I)V\n");
                code.append("    goto ").append(lEnd).append("\n");
                code.append(lNotZero).append(":\n");
                code.append("    invokevirtual java/io/PrintStream/print(F)V\n");
                code.append(lEnd).append(":\n");
            } else {
                code.append("    invokevirtual java/io/PrintStream/print(I)V\n");
            }

            if (i < size - 1) {
                code.append("    getstatic java/lang/System/out Ljava/io/PrintStream;\n");
                code.append("    ldc \" \"\n");
                code.append("    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V\n");
            }
        }
        code.append("    getstatic java/lang/System/out Ljava/io/PrintStream;\n");
        code.append("    invokevirtual java/io/PrintStream/println()V\n");
        return code.toString();
    }

    @Override
    public String visit(main.ast.nodes.Expression.BinaryExpression be) {
        StringBuilder code = new StringBuilder();
        Type leftType = getExprType(be.getLeft());
        Type rightType = getExprType(be.getRight());
        boolean isFloatOp = (leftType != null && leftType.baseType == Type.BaseType.REAL) ||
                (rightType != null && rightType.baseType == Type.BaseType.REAL);

        if (be.getLeft() != null) {
            String l = be.getLeft().accept(this);
            if (l == null)
                l = "/* null left expr: " + be.getLeft().getClass().getName() + " */\n";
            code.append(l);
            if (isFloatOp && leftType != null && leftType.baseType != Type.BaseType.REAL) {
                code.append("    i2f\n");
            }
        }
        if (be.getRight() != null) {
            String r = be.getRight().accept(this);
            if (r == null)
                r = "/* null right expr: " + be.getRight().getClass().getName() + " */\n";
            code.append(r);
            if (isFloatOp && rightType != null && rightType.baseType != Type.BaseType.REAL) {
                code.append("    i2f\n");
            }
        }
        if (be.getOperator() != null) {
            switch (be.getOperator()) {
                case ADDITION:
                    code.append(isFloatOp ? "    fadd\n" : "    iadd\n");
                    break;
                case SUBTRACTION:
                    code.append(isFloatOp ? "    fsub\n" : "    isub\n");
                    break;
                case MULTIPLICATION:
                    code.append(isFloatOp ? "    fmul\n" : "    imul\n");
                    break;
                case DIVISION:
                    code.append(isFloatOp ? "    fdiv\n" : "    idiv\n");
                    break;
                case BITWISE_AND:
                    code.append(isFloatOp ? "    /* bitwise on float not standard JVM */\n    iand\n" : "    iand\n");
                    break;
                case BITWISE_OR:
                    code.append(isFloatOp ? "    ior\n" : "    ior\n");
                    break;
                case BITWISE_XOR:
                    code.append(isFloatOp ? "    ixor\n" : "    ixor\n");
                    break;
                case LEFT_SHIFT:
                    code.append(isFloatOp ? "    ishl\n" : "    ishl\n");
                    break;
                case RIGHT_SHIFT:
                    code.append(isFloatOp ? "    ishr\n" : "    ishr\n");
                    break;
                case LESS_THAN:
                case GREATER_THAN:
                case EQUALITY:
                    String ltrue = "Ltrue_" + (++labelCount);
                    String lend = "Lend_" + (++labelCount);
                    if (isFloatOp) {
                        code.append("    fcmpg\n");
                        if (be.getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.LESS_THAN) {
                            code.append("    iflt ").append(ltrue).append("\n");
                        } else if (be
                                .getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.GREATER_THAN) {
                            code.append("    ifgt ").append(ltrue).append("\n");
                        } else if (be.getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.EQUALITY) {
                            code.append("    ifeq ").append(ltrue).append("\n");
                        }
                    } else {
                        if (be.getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.LESS_THAN) {
                            code.append("    if_icmplt ").append(ltrue).append("\n");
                        } else if (be
                                .getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.GREATER_THAN) {
                            code.append("    if_icmpgt ").append(ltrue).append("\n");
                        } else if (be.getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.EQUALITY) {
                            code.append("    if_icmpeq ").append(ltrue).append("\n");
                        }
                    }
                    code.append("    iconst_0\n");
                    code.append("    goto ").append(lend).append("\n");
                    code.append(ltrue).append(":\n");
                    code.append("    iconst_1\n");
                    code.append(lend).append(":\n");
                    break;
                default:
                    break;
            }
        }
        return code.toString();
    }

    @Override
    public String visit(main.ast.nodes.Expression.UnaryExpression ue) {
        StringBuilder code = new StringBuilder();
        if (ue.getExpression() != null) {
            String eStr = ue.getExpression().accept(this);
            if (eStr != null)
                code.append(eStr);
        }
        if (ue.getOperator() != null) {
            switch (ue.getOperator()) {
                case MINUS:
                    Type exprType = getExprType(ue.getExpression());
                    if (exprType != null && exprType.baseType == Type.BaseType.REAL) {
                        code.append("    fneg\n");
                    } else {
                        code.append("    ineg\n");
                    }
                    break;
                case BITWISE_NOT:
                    code.append("    iconst_m1\n    ixor\n");
                    break;
                case LOGICAL_NOT:
                    String lfalse = "Lfalse_" + (++labelCount);
                    String lend = "Lend_" + (++labelCount);
                    code.append("    ifeq ").append(lfalse).append("\n");
                    code.append("    iconst_0\n");
                    code.append("    goto ").append(lend).append("\n");
                    code.append(lfalse).append(":\n");
                    code.append("    iconst_1\n");
                    code.append(lend).append(":\n");
                    break;
                default:
                    code.append("    /* unhandled unary op: ").append(ue.getOperator().getSymbol()).append(" */\n");
                    break;
            }
        }
        return code.toString();
    }

    @Override
    public String visit(main.ast.nodes.Types.Number num) {
        if (num.getKind() == main.ast.nodes.Types.Number.NumberKind.REAL) {
            return "    ldc " + num.getText() + "\n";
        } else {
            return "    ldc " + num.getText() + "\n";
        }
    }

    @Override
    public String visit(main.ast.nodes.Identifier id) {
        Integer slot = slotMap.get(id.getName() + "_old");
        if (slot != null) {
            if (isFloat(id.getName())) {
                return "    fload " + slot + "\n";
            } else {
                return "    iload " + slot + "\n";
            }
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.IfExpressionExpression iee) {
        StringBuilder code = new StringBuilder();
        String lfalse = "Lfalse_" + (++labelCount);
        String lend = "Lend_" + (++labelCount);
        if (iee.getCondition() != null) {
            code.append(iee.getCondition().accept(this));
        }
        code.append("    ifeq ").append(lfalse).append("\n");
        if (iee.getThenExpression() != null) {
            code.append(iee.getThenExpression().accept(this));
        }
        code.append("    goto ").append(lend).append("\n");
        code.append(lfalse).append(":\n");
        if (iee.getElseExpression() != null) {
            code.append(iee.getElseExpression().accept(this));
        }
        code.append(lend).append(":\n");
        return code.toString();
    }

    @Override
    public String visit(main.ast.nodes.Expression.HierarchicalPrimary hp) {
        if (hp.getHierarchicalIdentifier() != null) {
            return hp.getHierarchicalIdentifier().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.ConstantLiteralPrimary clp) {
        if (clp.getPrimaryLiteral() != null) {
            return clp.getPrimaryLiteral().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.MintypmaxPrimary mp) {
        if (mp.getMintypmaxExpression() != null) {
            return mp.getMintypmaxExpression().accept(this);
        }
        return "";
    }

    @Override
    public String visit(main.ast.nodes.Expression.MintypmaxExpression me) {
        if (me.getMinExpression() != null) {
            return me.getMinExpression().accept(this);
        }
        return "";
    }
}