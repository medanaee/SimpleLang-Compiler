package main.visitor;

import main.TypeCheck.Type;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.ComponentItem.ListOfPortConnections.*;
import main.ast.nodes.ComponentItem.NetDeclaration.*;
import main.ast.nodes.Expression.BinaryExpression;
import main.ast.nodes.Expression.ConstantExpression;
import main.ast.nodes.Expression.ConstantPrimary;
import main.ast.nodes.Expression.NamedParameterAssignment;
import main.ast.nodes.Headers.*;
import main.ast.nodes.Identifier;
import main.ast.nodes.NetAssignment;
import main.ast.nodes.PortDecl.*;
import main.ast.nodes.SourceText;
import main.ast.nodes.Types.*;
import main.ast.nodes.Types.Number;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemAlreadyExistsException;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.item.*;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckerVisitor extends Visitor<Type> {
    ArrayList<String> predefindedNames = new ArrayList<>();

    @Override
    protected void merge(Type into, Type from) {
        if (into.isNoType()) {
            into.copy(from);
        }
    }

    @Override
    protected Type defaultResult() {
        return new Type();
    }

    @Override
    public Type visit(ConfigHeaderDeclaration node) {
        SymbolTable configSymbolTable = node.getSymbolTable();
        SymbolTable.push(configSymbolTable);

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
        // SymbolTable.top.print();
        SymbolTable.pop();
        return new Type();
    }

    @Override
    public Type visit(ItemHeaderDeclaration node) {
        SymbolTable itemHeaderSymbolTable = node.getSymbolTable();
        SymbolTable.push(itemHeaderSymbolTable);

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
        // SymbolTable.top.print();
        SymbolTable.pop();
        return new Type();
    }

    @Override
    public Type visit(DotNamedPortConnection dotNamedPortConnection) {
        if (dotNamedPortConnection.getIdentifier() != null) {
            // dotNamedPortConnection.getIdentifier().accept(this);
        }
        if (dotNamedPortConnection.getPortAssign() != null) {
            dotNamedPortConnection.getPortAssign().accept(this);
        }
        return new Type();
    }

    @Override
    public Type visit(ComponentItemDeclarationEmpty componentItemDeclarationEmpty) {
        if (componentItemDeclarationEmpty.getListOfGenvarIdentifiers() != null) {
            for (Identifier id : componentItemDeclarationEmpty.getListOfGenvarIdentifiers()) {
                id.accept(this);
            }
        }
        return new Type();
    }

    @Override
    public Type visit(ComponentProgramInterfaceInstantiation componentProgramInterfaceInstantiation) {

        try {
            SymbolTableItem symbolTableItem = SymbolTable.top
                    .getItem(ConfigDecSymbolTableItem.START_KEY + componentProgramInterfaceInstantiation.getNumOfPorts()
                            + "_" + componentProgramInterfaceInstantiation.getIdentifier().getName());
            if (componentProgramInterfaceInstantiation.getIdentifier() != null) {
                // componentProgramInterfaceInstantiation.getIdentifier().accept(this);
            }

            if (componentProgramInterfaceInstantiation.getParameterValueAssignment() != null) {
                componentProgramInterfaceInstantiation.getParameterValueAssignment().accept(this);
            }
            List<String> portNames = new ArrayList<>();
            if (symbolTableItem instanceof ComponentDecSymbolTableItem componentDecSymbolTableItem) {
                portNames = componentDecSymbolTableItem.getPortNames();
            } else if (symbolTableItem instanceof ConfigDecSymbolTableItem configDecSymbolTableItem) {
                portNames = configDecSymbolTableItem.getPortNames();
            }

            List<HierarchicalInstance> instances = componentProgramInterfaceInstantiation.getHierarchicalInstances();
            if (instances != null) {
                for (HierarchicalInstance instance : instances) {
                    if (instance != null) {
                        Type type = instance.accept(this);
                        for (int i = 0; i < type.getPorts().size(); i++) {
                            if (!type.getPorts().get(i).equals(portNames.get(i))) {
                                System.out.println("TypeCheck Error(" + instance.getLine() +
                                        "): " + (i + 1) + "th port mismatch, expected: " + portNames.get(i) + " found: "
                                        + type.getPorts().get(i));
                            }
                        }
                    }
                }
            }
        } catch (ItemNotFoundException ignored) {
        }
        return new Type();
    }

    @Override
    public Type visit(GenerateBlockName generateBlockName) {
        if (generateBlockName.getIdentifier() != null) {
            // generateBlockName.getIdentifier().accept(this);
        }
        return new Type();
    }

    @Override
    public Type visit(GenvarDeclaration genvarDeclaration) {
        if (genvarDeclaration.getListOfGenvarIdentifiers() != null) {
            for (Identifier id : genvarDeclaration.getListOfGenvarIdentifiers()) {
                GenvarDecSymbolTable GenvarDecItem = new GenvarDecSymbolTable(id);
                id.accept(this);
            }
        }
        return new Type();
    }

    @Override
    public Type visit(ParamAssignment paramAssignment) {
        if (paramAssignment.getIdentifier() != null) {
            paramAssignment.getIdentifier().accept(this);
        }

        List<UnpackedDimension> dimensions = paramAssignment.getUnpackedDimensions();
        if (dimensions != null) {
            for (UnpackedDimension dim : dimensions) {
                if (dim != null) {
                    dim.accept(this);
                }
            }
        }
        return new Type();
    }

    @Override
    public Type visit(NamedParameterAssignment namedParameterAssignment) {
        if (namedParameterAssignment.getIdentifier() != null) {
            // namedParameterAssignment.getIdentifier().accept(this);
        }
        if (namedParameterAssignment.getParamExpression() != null) {
            namedParameterAssignment.getParamExpression().accept(this);
        }
        return new Type();
    }

    @Override
    public Type visit(ComponentHeader componentHeader) {
        if (componentHeader.getIdentifier() != null) {
            // componentHeader.getIdentifier().accept(this);
        }
        if (componentHeader.getParameterPortList() != null) {
            componentHeader.getParameterPortList().accept(this);
        }
        if (componentHeader.getListOfPortDeclarations() != null) {
            for (PortDecl portDecl : componentHeader.getListOfPortDeclarations())
                portDecl.accept(this);
        }
        return new Type();
    }

    @Override
    public Type visit(ConfigurationHeader configurationHeader) {
        if (configurationHeader.getIdentifier() != null) {
            // configurationHeader.getIdentifier().accept(this);
        }
        if (configurationHeader.getParameterPortList() != null) {
            configurationHeader.getParameterPortList().accept(this);
        }
        if (configurationHeader.getListOfPortDeclarations() != null) {
            for (PortDecl portDecl : configurationHeader.getListOfPortDeclarations())
                portDecl.accept(this);
        }
        return new Type();
    }

    @Override
    public Type visit(HierarchicalInstance hierarchicalInstance) {
        if (hierarchicalInstance.getNameOfInstance() != null) {
            hierarchicalInstance.getNameOfInstance().accept(this);
        }

        List<String> portNames = new ArrayList<>();
        if (hierarchicalInstance.getListOfPortConnections() != null) {
            for (NamedPortConnection namedPortConnection : hierarchicalInstance.getListOfPortConnections()) {
                namedPortConnection.accept(this);
                if (namedPortConnection instanceof DotNamedPortConnection dotNamedPortConnection) {
                    portNames.add(dotNamedPortConnection.getIdentifier().getName());
                }
            }
        }
        return new Type(portNames);
    }

    // -------------------------------------------------------------
    // Port Declaration
    // -------------------------------------------------------------
    @Override
    public Type visit(DataTypePortDecl dataTypePortDecl) {
        if (dataTypePortDecl.getPortDirection() != null) {
            dataTypePortDecl.getPortDirection().accept(this);
        }
        if (dataTypePortDecl.getDataType() != null) {
            dataTypePortDecl.getDataType().accept(this);
        }
        if (dataTypePortDecl.getIdentifier() != null) {
            dataTypePortDecl.getIdentifier().accept(this);
        }
        if (dataTypePortDecl.getConstantExpression() != null) {
            Type rType = dataTypePortDecl.getConstantExpression().accept(this);
            if (!rType.isNoType()) {
                try {
                    String key = DataDecSymbolTableItem.START_KEY + dataTypePortDecl.getIdentifier().getName();
                    SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key);
                    symbolTableItem.setType(rType);
                } catch (ItemNotFoundException ignored) {
                }
            }
        }
        return new Type();
    }

    @Override
    public Type visit(ImplicitDataPortDecl implicitDataPortDecl) {
        if (implicitDataPortDecl.getPortDirection() != null) {
            implicitDataPortDecl.getPortDirection().accept(this);
        }
        if (implicitDataPortDecl.getImplicitDataType() != null) {
            implicitDataPortDecl.getImplicitDataType().accept(this);
        }
        if (implicitDataPortDecl.getIdentifier() != null) {
            implicitDataPortDecl.getIdentifier().accept(this);
        }
        for (UnpackedDimension dim : implicitDataPortDecl.getUnpackedDimensions()) {
            if (dim != null) {
                dim.accept(this);
            }
        }
        if (implicitDataPortDecl.getConstantExpression() != null) {
            Type rType = implicitDataPortDecl.getConstantExpression().accept(this);
            if (!rType.isNoType()) {
                try {
                    String key = DataDecSymbolTableItem.START_KEY + implicitDataPortDecl.getIdentifier().getName();
                    SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key);
                    symbolTableItem.setType(rType);
                } catch (ItemNotFoundException ignored) {
                }
            }
        }
        return new Type();
    }

    @Override
    public Type visit(NetTypePortDecl netTypePortDecl) {
        if (netTypePortDecl.getPortDirection() != null) {
            netTypePortDecl.getPortDirection().accept(this);
        }
        if (netTypePortDecl.getDataTypeOrImplicit() != null) {
            netTypePortDecl.getDataTypeOrImplicit().accept(this);
        }
        if (netTypePortDecl.getIdentifier() != null) {
            netTypePortDecl.getIdentifier().accept(this);
        }
        for (UnpackedDimension dim : netTypePortDecl.getUnpackedDimensions()) {
            if (dim != null) {
                dim.accept(this);
            }
        }
        if (netTypePortDecl.getConstantExpression() != null) {
            Type rType = netTypePortDecl.getConstantExpression().accept(this);
            if (!rType.isNoType()) {
                try {
                    String key = DataDecSymbolTableItem.START_KEY + netTypePortDecl.getIdentifier().getName();
                    SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key);
                    symbolTableItem.setType(rType);
                } catch (ItemNotFoundException ignored) {
                }
            }
        }
        return new Type();
    }

    // -------------------------------------------------------------
    // Net Declaration
    // -------------------------------------------------------------
    @Override
    public Type visit(NetTypeNetDeclaration netDeclaration) {
        Type type = new Type();
        if (netDeclaration.getDataTypeOrImplicit() != null)
            type = netDeclaration.getDataTypeOrImplicit().accept(this);
        if (netDeclaration.getListOfNetDeclAssignments() != null) {
            for (NetDeclAssignment netDeclAssignment : netDeclaration.getListOfNetDeclAssignments()) {
                netDeclAssignment.accept(this);
                String name = netDeclAssignment.getIdentifier().getName();
                try {
                    SymbolTableItem symbolTableItem = SymbolTable.top.getItem(DataDecSymbolTableItem.START_KEY + name);
                    if (!type.isNoType())
                        symbolTableItem.setType(type);
                } catch (ItemNotFoundException ignored) {
                }
            }
        }
        return new Type();
    }

    // Todo: @Override
    // public Type visit(IdentifierNetDeclaration netDeclaration){
    //
    // return null;
    // }
    @Override
    public Type visit(NetDeclAssignment netDeclAssignment) {
        if (netDeclAssignment.getIdentifier() != null) {
            netDeclAssignment.getIdentifier().accept(this);
        }
        List<UnpackedDimension> dimensions = netDeclAssignment.getUnpackedDimensions();
        if (dimensions != null) {
            for (UnpackedDimension dim : dimensions) {
                if (dim != null) {
                    dim.accept(this);
                }
            }
        }
        if (netDeclAssignment.getExpression() != null) {
            Type type = netDeclAssignment.getExpression().accept(this);
            try {
                String name = netDeclAssignment.getIdentifier().getName();
                SymbolTableItem symbolTableItem = SymbolTable.top.getItem(DataDecSymbolTableItem.START_KEY + name);
                if (!type.isNoType())
                    symbolTableItem.setType(type);
            } catch (ItemNotFoundException ignored) {
            }
        }
        return new Type();
    }

    // -------------------------------------------------------------
    // set
    // -------------------------------------------------------------
    @Override
    public Type visit(NetAssignment netAssignment) {
        try {
            String key = DataDecSymbolTableItem.START_KEY + netAssignment.getName();
            SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key);
            Type ltype = symbolTableItem.getType();
            Type rtype = netAssignment.getExpression().accept(this);
            if (ltype.isNoType()) {
                symbolTableItem.setType(rtype);
            } else if (!rtype.isNoType() && !ltype.equals(rtype)) {
                System.out.println("TypeCheck Error(" + netAssignment.getLine() +
                        "): Incompatible Assignment left Type(" + ltype + ") with right Type(" + rtype + ")");
            }
        } catch (ItemNotFoundException ignored) {
        }
        return new Type();
    }

    @Override
    public Type visit(main.ast.nodes.Expression.OperatorAssignment oa) {
        Type rtype = new Type();
        if (oa.getRightExpression() != null)
            rtype = oa.getRightExpression().accept(this);
        if (oa.getVariableLvalue() instanceof main.ast.nodes.Expression.HierarchicalVariableLvalue) {
            String name = ((main.ast.nodes.Expression.HierarchicalVariableLvalue) oa.getVariableLvalue())
                    .getHierarchicalIdentifier().getName();
            try {
                String key = DataDecSymbolTableItem.START_KEY + name;
                SymbolTableItem item = SymbolTable.top.getItem(key);
                Type ltype = item.getType();
                if (ltype.isNoType() && !rtype.isNoType()) {
                    item.setType(
                            new Type(rtype.baseType, rtype.width, rtype.isSigned != null ? rtype.isSigned : false));
                }
            } catch (ItemNotFoundException ignored) {
            }
        }
        return new Type();
    }

    @Override
    public Type visit(main.ast.nodes.Expression.NonblockingAssignment na) {
        Type rtype = new Type();
        if (na.getExpression() != null)
            rtype = na.getExpression().accept(this);
        if (na.getVariableLvalue() instanceof main.ast.nodes.Expression.HierarchicalVariableLvalue) {
            String name = ((main.ast.nodes.Expression.HierarchicalVariableLvalue) na.getVariableLvalue())
                    .getHierarchicalIdentifier().getName();
            try {
                String key = DataDecSymbolTableItem.START_KEY + name;
                SymbolTableItem item = SymbolTable.top.getItem(key);
                Type ltype = item.getType();
                if (ltype.isNoType() && !rtype.isNoType()) {
                    item.setType(
                            new Type(rtype.baseType, rtype.width, rtype.isSigned != null ? rtype.isSigned : false));
                }
            } catch (ItemNotFoundException ignored) {
            }
        }
        return new Type();
    }

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        Type ltype = binaryExpression.getLeft().accept(this);
        Type rtype = binaryExpression.getRight().accept(this);
        if (!ltype.isNoType() && !rtype.isNoType() && !ltype.equals(rtype)) {
            boolean isLNumeric = ltype.baseType == Type.BaseType.INT || ltype.baseType == Type.BaseType.REAL;
            boolean isRNumeric = rtype.baseType == Type.BaseType.INT || rtype.baseType == Type.BaseType.REAL;
            if (isLNumeric && isRNumeric) {
                // allowed mixed numerics
            } else {
                System.out.println("TypeCheck Error(" + binaryExpression.getLine() +
                        "): Incompatible Binary Expression '" + binaryExpression.getOperator().getSymbol() +
                        "' left Type(" + ltype + ") with right Type(" + rtype + ")");
                return new Type();
            }
        }

        if (!rtype.isNoType() && ltype.isNoType()) {
            List<Identifier> list = binaryExpression.getLeft().getIdentifiers();
            for (Identifier identifier : list) {
                try {
                    String key = DataDecSymbolTableItem.START_KEY + identifier.getName();
                    SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key);
                    symbolTableItem.setType(
                            new Type(rtype.baseType, rtype.width, rtype.isSigned != null ? rtype.isSigned : false));
                } catch (ItemNotFoundException ignored) {
                }
            }
            ltype = rtype;
        } else if (!ltype.isNoType() && rtype.isNoType()) {
            List<Identifier> list = binaryExpression.getRight().getIdentifiers();
            for (Identifier identifier : list) {
                try {
                    String key = DataDecSymbolTableItem.START_KEY + identifier.getName();
                    SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key);
                    symbolTableItem.setType(
                            new Type(ltype.baseType, ltype.width, ltype.isSigned != null ? ltype.isSigned : false));
                } catch (ItemNotFoundException ignored) {
                }
            }
            rtype = ltype;
        }
        if (binaryExpression.getOperator().isComparisonOperator()) {
            return new Type(Type.BaseType.BOOL, 0, false);
        }
        if (!ltype.isNoType() && !rtype.isNoType()) {
            if (ltype.baseType == Type.BaseType.REAL || rtype.baseType == Type.BaseType.REAL) {
                return new Type(Type.BaseType.REAL, 0, false);
            }
        }
        return ltype;
    }

    @Override
    public Type visit(main.ast.nodes.Expression.IfExpressionExpression iee) {
        if (iee.getCondition() != null) {
            iee.getCondition().accept(this);
        }
        Type thenType = new Type();
        if (iee.getThenExpression() != null)
            thenType = iee.getThenExpression().accept(this);
        Type elseType = new Type();
        if (iee.getElseExpression() != null)
            elseType = iee.getElseExpression().accept(this);

        if (!thenType.isNoType())
            return thenType;
        if (!elseType.isNoType())
            return elseType;
        return new Type();
    }

    @Override
    public Type visit(main.ast.nodes.Expression.UnaryExpression ue) {
        if (ue.getExpression() != null) {
            return ue.getExpression().accept(this);
        }
        return new Type();
    }

    @Override
    public Type visit(Number number) {
        return switch (number.getKind()) {
            case REAL -> new Type(Type.BaseType.REAL, 0, false);
            case UNSIGNED_DECIMAL -> new Type(Type.BaseType.INT, 0, false);
            case DECIMAL -> new Type(Type.BaseType.INT, 0, true);
            case BINARY -> new Type(Type.BaseType.INT, 0, false);
            case UNBASED_UNSIZED -> new Type(Type.BaseType.INT, 0, false);
        };
    }

    @Override
    public Type visit(IntegerDataType integerDataType) {
        boolean isSigned = integerDataType.getSigning() != null && integerDataType.getSigning() == Signing.SIGNED;
        return new Type(Type.BaseType.INT, 0, isSigned);
    }

    @Override
    public Type visit(NonIntegerDataType nonIntegerDataType) {
        return new Type(Type.BaseType.REAL, 0, false);
    }

    @Override
    public Type visit(ImplicitDataType implicitDataType) {
        return new Type();
    }

    @Override
    public Type visit(Identifier identifier) {
        if (!predefindedNames.contains(identifier.getName())) {
            try {
                SymbolTableItem symbolTableItem = SymbolTable.top
                        .getItem(DataDecSymbolTableItem.START_KEY + identifier.getName());
                return symbolTableItem.getType();
            } catch (ItemNotFoundException ignored) {
            }
        }
        return new Type();
    }

    @Override
    public Type visit(SourceText sourceText) {
        predefindedNames.add("FOREVER");
        predefindedNames.add("display");
        SymbolTable.top = new SymbolTable();
        SymbolTable.root = SymbolTable.top;
        sourceText.setSymbolTable(SymbolTable.top);
        for (ComponentDeclaration cd : sourceText.getComponentDeclarationList()) {
            cd.accept(this);
        }
        return new Type();
    }
}
