package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.Types.*;
import main.ast.nodes.Headers.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.PortDecl.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.ComponentItem.NetDeclaration.*;
import main.ast.nodes.ComponentItem.ListOfPortConnections.*;
import main.ast.nodes.Expression.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.symbolTable.*;
import main.symbolTable.exceptions.ItemAlreadyExistsException;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.item.*;
import main.TypeCheck.*;

import java.util.ArrayList;
import java.util.List;

import main.symbolTable.exceptions.TypeException;

public class TypeAnalyzer extends Visitor<Void> {
    ArrayList<String> predefindedNames = new ArrayList<>();
    ComponentProgramInterfaceInstantiation currentInstance = null;
    ItemHeaderDeclaration currentComponent = null;

    @Override
    public Void visit(ConfigHeaderDeclaration node) {

        SymbolTable symbolTable = node.getSymbolTable();
        SymbolTable.push(symbolTable);

        if (node.getConfigurationHeader() != null) {
            node.getConfigurationHeader().accept(this);
        }
        List<ComponentItem> componentItems = node.getComponentItemList();
        if (componentItems != null) {
            for (ComponentItem item : componentItems) {
                if (item != null) {
                    item.accept(this);
                }
            }
        }
        if (node.getComponentName() != null) {
            node.getComponentName().accept(this);
        }
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(ItemHeaderDeclaration node) {
        currentComponent = node;

        SymbolTable symbolTable = node.getSymbolTable();
        SymbolTable.push(symbolTable);

        if (node.getComponentHeader() != null) {
            node.getComponentHeader().accept(this);
        }
        List<ComponentItem> componentItems = node.getComponentItemList();
        if (componentItems != null) {
            for (ComponentItem item : componentItems) {
                if (item != null) {
                    item.accept(this);
                }
            }
        }
        if (node.getComponentName() != null) {
            node.getComponentName().accept(this);
        }
        SymbolTable.pop();
        currentComponent = null;
        return null;
    }

    @Override
    public Void visit(NetAssignment node) {

        if (node.getNetLvalue() != null) {
            node.getNetLvalue().accept(this);
        }
        if (node.getExpression() != null) {
            try {
                SymbolTableItem lValue = SymbolTable.top.getItem("Dec_" + node.getNetLvalue().getName());
                Type lValueType = lValue.getType();
                setTypeAndCheck(lValueType, node.getExpression());
            } catch (ItemNotFoundException TypeException) {
                System.out.println("Line " + node.getNetLvalue().getLine() + ": type mismatch");
            }
            catch (TypeException TypeException) {
                System.out.println("Line " + node.getNetLvalue().getLine() + ": type mismatch");
            }
            node.getExpression().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(OperatorAssignment node) {
        if (node.getVariableLvalue() != null) {
            node.getVariableLvalue().accept(this);
        }
        if (node.getRightExpression() != null) {
            try {
                SymbolTableItem lValue = SymbolTable.top.getItem("Dec_" + node.getVariableLvalue().getName());
                Type lValueType = lValue.getType();
                setTypeAndCheck(lValueType, node.getRightExpression());
            } catch (ItemNotFoundException TypeException) {
                System.out.println("Line " + node.getVariableLvalue().getLine() + ": type mismatch");
            }
            catch (TypeException TypeException) {
                System.out.println("Line " + node.getVariableLvalue().getLine() + ": type mismatch");
            }
            node.getRightExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(NetDeclAssignment node) {
        if (node.getIdentifier() != null) {
            node.getIdentifier().accept(this);
        }
        List<UnpackedDimension> dims = node.getUnpackedDimensions();
        if (dims != null) {
            for (UnpackedDimension ud : dims) {
                if (ud != null) {
                    ud.accept(this);
                }
            }
        }
        if (node.getExpression() != null) {
            try {
                SymbolTableItem lValue = SymbolTable.top.getItem("Dec_" + node.getIdentifier().getName());
                Type lValueType = lValue.getType();
                setTypeAndCheck(lValueType, node.getExpression());
            } catch (ItemNotFoundException ItemNotFoundException) {
                System.out.println("Line " + node.getIdentifier().getLine() + ": type mismatch");
            }
            catch (TypeException TypeException) {
                System.out.println("Line " + node.getIdentifier().getLine() + ": type mismatch");
            }
            node.getExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(SourceText sourceText) {
        predefindedNames.add("FOREVER");
        predefindedNames.add("display");
        predefindedNames.add("true");
        predefindedNames.add("false");
        SymbolTable.top = sourceText.getSymbolTable();
        SymbolTable.root = SymbolTable.top;
        for (ComponentDeclaration cd : sourceText.getComponentDeclarationList()) {
            cd.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ComponentProgramInterfaceInstantiation node) {
        currentInstance = node;
        if (node.getIdentifier() != null) {
            node.getIdentifier().accept(this);
        }
        if (node.getParameterValueAssignment() != null) {
            node.getParameterValueAssignment().accept(this);
        }
        List<HierarchicalInstance> instances = node.getHierarchicalInstances();
        if (instances != null) {
            for (HierarchicalInstance instance : instances) {
                if (instance != null) {
                    instance.accept(this);
                }
            }
        }
        currentInstance = null;
        return null;
    }

    @Override
    public Void visit(DotNamedPortConnection dotNamedPortConnection) {
        ComponentDecSymbolTableItem instance = null;
        if (currentInstance != null) {
            try {
                instance = (ComponentDecSymbolTableItem) SymbolTable.top
                        .getItem("CompoDec_" + currentInstance.getIdentifier().getName());
            } catch (Exception e) {
            }
        }
        Type portType = null;
        if (dotNamedPortConnection.getIdentifier() != null) {
            portType = instance.getTypebyPortName(dotNamedPortConnection.getIdentifier().getName());
            dotNamedPortConnection.getIdentifier().accept(this);
        }
        if (dotNamedPortConnection.getPortAssign() != null) {
            dotNamedPortConnection.getPortAssign().accept(this);
            try {
                setTypeAndCheck(portType, dotNamedPortConnection.getPortAssign().getExpression());
            } catch (Exception TypeException) {
                System.out.println(
                        "Line " + dotNamedPortConnection.getPortAssign().getExpression().getLine() + ": type mismatch");
            }
        }
        return null;
    }

    public Type getTypeOfExpression(Expression expression) {
        Type type = new Type(Type.BaseType.NO_TYPE, 0, false);
        if (expression instanceof main.ast.nodes.Types.Number) {
            if (((main.ast.nodes.Types.Number) expression)
                    .getKind() == main.ast.nodes.Types.Number.NumberKind.UNSIGNED_DECIMAL ||
                    ((main.ast.nodes.Types.Number) expression)
                            .getKind() == main.ast.nodes.Types.Number.NumberKind.DECIMAL) {
                return new Type(Type.BaseType.INT, 0, false);
            }
            if (((main.ast.nodes.Types.Number) expression).getKind() == main.ast.nodes.Types.Number.NumberKind.REAL) {
                return new Type(Type.BaseType.FLOAT, 0, false);
            }
        } else if (expression instanceof HierarchicalPrimary) {
            String primaryName = ((HierarchicalPrimary) expression).getHierarchicalIdentifier().getName();
            if (primaryName.equals("true") || primaryName.equals("false")) {
                return new Type(Type.BaseType.BOOL, 0, false);
            }
            try {
                SymbolTableItem item = SymbolTable.top.getItem("Dec_" + primaryName);
                return item.getType();
            } catch (ItemNotFoundException e) {
                return new Type(Type.BaseType.NO_TYPE, 0, false);
            }
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            Type leftType = getTypeOfExpression(binaryExpression.getLeft());
            Type rightType = getTypeOfExpression(binaryExpression.getRight());

            if (binaryExpression.getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.MODULUS) {
                if ((leftType.getBaseType() == Type.BaseType.INT || leftType.getBaseType() == Type.BaseType.NO_TYPE) &&
                        (rightType.getBaseType() == Type.BaseType.INT
                                || rightType.getBaseType() == Type.BaseType.NO_TYPE)) {
                    if (leftType.getBaseType() == Type.BaseType.NO_TYPE) {
                        leftType.setBaseType(Type.BaseType.INT);
                    }
                    if (rightType.getBaseType() == Type.BaseType.NO_TYPE) {
                        rightType.setBaseType(Type.BaseType.INT);
                    }
                    return new Type(Type.BaseType.INT, 0, false);
                }
                // If modulus operands are not INT, it's a type error
                try {
                    throw new TypeException();
                } catch (TypeException e) {
                    System.out.println("Line " + expression.getLine() + ": type mismatch");
                    return new Type(Type.BaseType.NO_TYPE, 0, false);
                }
            } else if (binaryExpression.getOperator().isArithmeticOperator()) {
                if ((leftType.getBaseType() == Type.BaseType.INT || leftType.getBaseType() == Type.BaseType.FLOAT
                        || leftType.getBaseType() == Type.BaseType.NO_TYPE) &&
                        (rightType.getBaseType() == Type.BaseType.INT || rightType.getBaseType() == Type.BaseType.FLOAT
                                || rightType.getBaseType() == Type.BaseType.NO_TYPE)) {
                    if (leftType.getBaseType() == Type.BaseType.NO_TYPE) {
                        leftType.setBaseType(Type.BaseType.FLOAT); // Type inference
                    }
                    if (rightType.getBaseType() == Type.BaseType.NO_TYPE) {
                        rightType.setBaseType(Type.BaseType.FLOAT); // Type inference
                    }
                    if (leftType.getBaseType() == Type.BaseType.FLOAT
                            || rightType.getBaseType() == Type.BaseType.FLOAT) {
                        return new Type(Type.BaseType.FLOAT, 0, false);
                    }
                    return new Type(Type.BaseType.INT, 0, false);
                }
            } else if (binaryExpression.getOperator().isComparisonOperator()) {
                if (binaryExpression.getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.EQUALITY ||
                        binaryExpression
                                .getOperator() == main.ast.nodes.Expression.Operators.BinaryOperator.INEQUALITY) {
                    if (leftType.getBaseType() == rightType.getBaseType())
                        return new Type(Type.BaseType.BOOL, 0, false);
                } else {
                    if ((leftType.getBaseType() == Type.BaseType.INT || leftType.getBaseType() == Type.BaseType.FLOAT)
                            &&
                            (rightType.getBaseType() == Type.BaseType.INT
                                    || rightType.getBaseType() == Type.BaseType.FLOAT)) {
                        return new Type(Type.BaseType.BOOL, 0, false);
                    }
                }
            } else if (binaryExpression.getOperator().isLogicalOperator()) {
                if (leftType.getBaseType() == Type.BaseType.BOOL && rightType.getBaseType() == Type.BaseType.BOOL) {
                    return new Type(Type.BaseType.BOOL, 0, false);
                }
            }
            // If we fall through, it's a type error
            try {
                throw new TypeException();
            } catch (TypeException e) {
                System.out.println("Line " + expression.getLine() + ": type mismatch");
                // System.out.println("Left type: " + leftType.getBaseType() + ", Right type: "
                // + rightType.getBaseType());
                return new Type(Type.BaseType.NO_TYPE, 0, false);
            }
        } else if (expression instanceof UnaryExpression) {
            UnaryExpression unaryExpression = (UnaryExpression) expression;
            Type subType = getTypeOfExpression(unaryExpression.getExpression());
            if (unaryExpression.getOperator().isLogicalOperator()) { // !
                if (subType.getBaseType() == Type.BaseType.BOOL) {
                    return new Type(Type.BaseType.BOOL, 0, false);
                }
            } else if (unaryExpression.getOperator() == main.ast.nodes.Expression.Operators.UnaryOperator.MINUS_MINUS ||
                    unaryExpression.getOperator() == main.ast.nodes.Expression.Operators.UnaryOperator.PLUS_PLUS) {
                if (subType.getBaseType() == Type.BaseType.INT) {
                    return new Type(Type.BaseType.INT, 0, false);
                }
            } else if (unaryExpression.getOperator() == main.ast.nodes.Expression.Operators.UnaryOperator.MINUS) {
                if (subType.getBaseType() == Type.BaseType.INT || subType.getBaseType() == Type.BaseType.FLOAT) {
                    return subType;
                }
                // Handle NO_TYPE inference if needed, or error
                if (subType.getBaseType() == Type.BaseType.NO_TYPE) {
                    subType.setBaseType(Type.BaseType.FLOAT); // Inference
                    return subType;
                }
            }
            try {
                throw new TypeException();
            } catch (TypeException e) {
                System.out.println("Line " + expression.getLine() + ": type mismatch");
                return new Type(Type.BaseType.NO_TYPE, 0, false);
            }
        } else if (expression instanceof PrefixIncOrDecExpression) {
            PrefixIncOrDecExpression prefixExpression = (PrefixIncOrDecExpression) expression;
            try {
                SymbolTableItem item = SymbolTable.top
                        .getItem("Dec_" + prefixExpression.getVariableLvalue().getIdentifiers().get(0).getName());
                Type subType = item.getType();
                if (subType.getBaseType() == Type.BaseType.INT) {
                    return new Type(Type.BaseType.INT, 0, false);
                }
                throw new TypeException();
            } catch (Exception e) {
                System.out.println("Line " + prefixExpression.getVariableLvalue().getLine() + ": type mismatch");
                return new Type(Type.BaseType.NO_TYPE, 0, false);
            }
        } else if (expression instanceof PostfixIncOrDecExpression) {
            PostfixIncOrDecExpression postfixExpression = (PostfixIncOrDecExpression) expression;
            try {
                SymbolTableItem item = SymbolTable.top
                        .getItem("Dec_" + postfixExpression.getVariableLvalue().getIdentifiers().get(0).getName());
                Type subType = item.getType();
                if (subType.getBaseType() == Type.BaseType.INT) {
                    return new Type(Type.BaseType.INT, 0, false);
                }
                throw new TypeException();
            } catch (Exception e) {
                System.out.println("Line " + postfixExpression.getVariableLvalue().getLine() + ": type mismatch");
                return new Type(Type.BaseType.NO_TYPE, 0, false);
            }
        }

        return type;
    }

    public Void setTypeAndCheck(Type typeFix, Expression expression) throws TypeException {
        Type type = getTypeOfExpression(expression);
        if (typeFix.getBaseType() == Type.BaseType.NO_TYPE) {
            if (type.getBaseType() == Type.BaseType.NO_TYPE && expression instanceof HierarchicalPrimary) {
                try {
                    SymbolTableItem expItem = SymbolTable.top
                            .getItem("Dec_" + ((HierarchicalPrimary) expression).getHierarchicalIdentifier().getName());
                    expItem.setType(typeFix);
                    if (expItem instanceof PortDecSymbolTableItem) {
                        ComponentDecSymbolTableItem instance = (ComponentDecSymbolTableItem) SymbolTable.top
                                .getItem("CompoDec_" + currentComponent.getComponentHeader().getName());
                        instance.setTypebyPortName(
                                ((HierarchicalPrimary) expression).getHierarchicalIdentifier().getName(), typeFix);
                    }
                } catch (ItemNotFoundException e) {
                }
            } else {
                typeFix.setBaseType(type.getBaseType());
            }
        } else if (typeFix.getBaseType() == Type.BaseType.FLOAT || typeFix.getBaseType() == Type.BaseType.INT) {
            if (type.getBaseType() == Type.BaseType.INT || type.getBaseType() == Type.BaseType.FLOAT) {

            } else if (type.getBaseType() == Type.BaseType.NO_TYPE) {
                type.setBaseType(typeFix.getBaseType());
            } else {
                throw new TypeException();
            }
        } else {
            if (typeFix.getBaseType() == type.getBaseType()) {
            } else if (type.getBaseType() == Type.BaseType.NO_TYPE) {
                type.setBaseType(typeFix.getBaseType());
            } else {
                throw new TypeException();
            }
        }
        return null;
    }
}
