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

import java.util.ArrayList;
import java.util.List;

public class NameAnalyzer extends Visitor<Void> {
    ArrayList<String> predefindedNames = new ArrayList<>();
    DataDecSymbolTableItem.IntPair cuttentRepeatTimes = null;
    boolean weAreInLValue = false;
    ComponentHeader currentComponentHeader = null;
    ComponentDecSymbolTableItem currentComponentDecSymbolTableItem = null;
    ConfigDecSymbolTableItem currentConfigDecSymbolTableItem = null;

    @Override
    public Void visit(ConfigHeaderDeclaration node) {

        ConfigDecSymbolTableItem ConfigDecItem = new ConfigDecSymbolTableItem(node);
        currentConfigDecSymbolTableItem = ConfigDecItem;
        try {
            SymbolTable.top.put(ConfigDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + node.getName() + "\" in line " + node.getLine());
        }
        SymbolTable configSymbolTable = new SymbolTable(SymbolTable.top);
        SymbolTable.push(configSymbolTable);
        node.setSymbolTable(configSymbolTable);

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
        currentConfigDecSymbolTableItem = null;
        return null;
    }

    @Override
    public Void visit(ItemHeaderDeclaration node) {
        ComponentDecSymbolTableItem ComponentDecItem = new ComponentDecSymbolTableItem(node);
        currentComponentDecSymbolTableItem = ComponentDecItem;
        try {
            SymbolTable.top.put(ComponentDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + node.getName() + "\" in line " + node.getLine());
        }
        SymbolTable configSymbolTable = new SymbolTable(SymbolTable.top);
        SymbolTable.push(configSymbolTable);
        node.setSymbolTable(configSymbolTable);

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
        currentComponentDecSymbolTableItem = null;
        return null;
    }

    @Override
    public Void visit(DotNamedPortConnection dotNamedPortConnection) {
        if (dotNamedPortConnection.getIdentifier() != null) {
            // dotNamedPortConnection.getIdentifier().accept(this);
        }
        if (dotNamedPortConnection.getPortAssign() != null) {
            dotNamedPortConnection.getPortAssign().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(NetDeclAssignment netDeclAssignment) {
        DataDecSymbolTableItem DataDecItem = new DataDecSymbolTableItem(netDeclAssignment);
        try {
            SymbolTable.top.put(DataDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + netDeclAssignment.getName() + "\" in line "
                    + netDeclAssignment.getLine());
        }
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
            netDeclAssignment.getExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(NetAssignment netAssignment) {
        if (netAssignment.getNetLvalue() != null) {
            String Name = netAssignment.getNetLvalue().getName();
            try {
                if (SymbolTable.top
                        .getItem(DataDecSymbolTableItem.START_KEY + Name) instanceof DataDecSymbolTableItem) {
                    DataDecSymbolTableItem DataDecItem = (DataDecSymbolTableItem) SymbolTable.top
                            .getItem(DataDecSymbolTableItem.START_KEY + Name);
                    DataDecItem.setValue(netAssignment.getExpression());
                }
            } catch (ItemNotFoundException e) {
                // System.out.println("Line " + netAssignment.getLine() + ": " + "there is no any " + Name + " data");
            }
            netAssignment.getNetLvalue().accept(this);
        }
        if (netAssignment.getExpression() != null) {
            netAssignment.getExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ComponentProgramInterfaceInstantiation componentProgramInterfaceInstantiation) {

        List<String> expectedPortNames = null;

        if (componentProgramInterfaceInstantiation.getIdentifier() != null) {
            try {

                ComponentDecSymbolTableItem componentItem = (ComponentDecSymbolTableItem) SymbolTable.top.getItem(
                        ComponentDecSymbolTableItem.START_KEY
                                + componentProgramInterfaceInstantiation.getIdentifier().getName());

                expectedPortNames = componentItem.getPortNames();

            } catch (ItemNotFoundException e) {
                System.out.println("Line " + componentProgramInterfaceInstantiation.getIdentifier().getLine() + ": "
                        + "there is no any " + componentProgramInterfaceInstantiation.getIdentifier().getName()
                        + " component");
                return null;
            }
        }

        if (componentProgramInterfaceInstantiation.getParameterValueAssignment() != null) {
            componentProgramInterfaceInstantiation.getParameterValueAssignment().accept(this);
        }

        List<HierarchicalInstance> instances = componentProgramInterfaceInstantiation.getHierarchicalInstances();
        if (instances != null) {
            for (HierarchicalInstance instance : instances) {
                if (instance != null) {
                    if (expectedPortNames != null) {
                        checkArgumentMismatch(instance, expectedPortNames);
                    }

                    instance.accept(this);
                }
            }
        }
        return null;
    }

    private void checkArgumentMismatch(HierarchicalInstance instance, List<String> expectedPortNames) {
        List<NamedPortConnection> actualConnections = instance.getListOfPortConnections();

        if (actualConnections.size() != expectedPortNames.size()) {
            printMismatchError(instance);
            return;
        }
        for (NamedPortConnection connection : actualConnections) {
            if (connection instanceof DotNamedPortConnection) {
                DotNamedPortConnection dotConnection = (DotNamedPortConnection) connection;
                String usedPortName = dotConnection.getIdentifier().getName();

                if (!expectedPortNames.contains(usedPortName)) {
                    printMismatchError(instance);
                    return;
                }
            }

        }
    }

    private void printMismatchError(HierarchicalInstance instance) {
        System.out.println("Line " + instance.getNameOfInstance().getIdentifier().getLine() + ": "
                + "argument mismatch for " + instance.getNameOfInstance().getIdentifier().getName());
    }

    @Override
    public Void visit(GenerateBlockName generateBlockName) {
        if (generateBlockName.getIdentifier() != null) {
            // generateBlockName.getIdentifier().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(GenvarDeclaration genvarDeclaration) {
        if (genvarDeclaration.getListOfGenvarIdentifiers() != null) {
            for (Identifier id : genvarDeclaration.getListOfGenvarIdentifiers()) {
                GenvarDecSymbolTable GenvarDecItem = new GenvarDecSymbolTable(id);
                try {
                    SymbolTable.top.put(GenvarDecItem);
                } catch (ItemAlreadyExistsException e) {
                    System.out.println("Redefinition of function \"" + id.getName() + "\" in line " + id.getLine());
                }
                id.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(HierarchicalInstance hierarchicalInstance) {
        InstanceDecSymbolTableItem InsDecItem = new InstanceDecSymbolTableItem(hierarchicalInstance);
        try {
            SymbolTable.top.put(InsDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + hierarchicalInstance.getName() + "\" in line "
                    + hierarchicalInstance.getLine());
        }

        if (hierarchicalInstance.getNameOfInstance() != null) {
            hierarchicalInstance.getNameOfInstance().accept(this);
        }

        if (hierarchicalInstance.getListOfPortConnections() != null) {
            for (NamedPortConnection namedPortConnection : hierarchicalInstance.getListOfPortConnections())
                namedPortConnection.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ParamAssignment paramAssignment) {
        ParameterDecSymbolTable ParamDecItem = new ParameterDecSymbolTable(paramAssignment);
        try {
            SymbolTable.top.put(ParamDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + paramAssignment.getName() + "\" in line "
                    + paramAssignment.getLine());
        }
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
        return null;
    }

    @Override
    public Void visit(NamedParameterAssignment namedParameterAssignment) {
        if (namedParameterAssignment.getIdentifier() != null) {
            // namedParameterAssignment.getIdentifier().accept(this);
        }
        if (namedParameterAssignment.getParamExpression() != null) {
            namedParameterAssignment.getParamExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ComponentHeader componentHeader) {
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
        return null;
    }

    @Override
    public Void visit(ConfigurationHeader configurationHeader) {
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
        return null;
    }

    @Override
    public Void visit(DataTypePortDecl dataTypePortDecl) {
        PortDecSymbolTableItem PortDecItem = new PortDecSymbolTableItem(dataTypePortDecl);
        if(currentComponentDecSymbolTableItem != null){
            currentComponentDecSymbolTableItem.addPortType(PortDecItem);
        }
        else if (currentConfigDecSymbolTableItem != null){
            currentConfigDecSymbolTableItem.addPortType(PortDecItem);
        }   
        try {
            SymbolTable.top.put(PortDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + dataTypePortDecl.getName() + "\" in line "
                    + dataTypePortDecl.getLine());
        }
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
            dataTypePortDecl.getConstantExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ImplicitDataPortDecl implicitDataPortDecl) {
        PortDecSymbolTableItem PortDecItem = new PortDecSymbolTableItem(implicitDataPortDecl);
        try {
            SymbolTable.top.put(PortDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + implicitDataPortDecl.getName() + "\" in line "
                    + implicitDataPortDecl.getLine());
        }
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
            implicitDataPortDecl.getConstantExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(NetTypePortDecl netTypePortDecl) {
        PortDecSymbolTableItem PortDecItem = new PortDecSymbolTableItem(netTypePortDecl);
        try {
            SymbolTable.top.put(PortDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + netTypePortDecl.getName() + "\" in line "
                    + netTypePortDecl.getLine());
        }
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
            netTypePortDecl.getConstantExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        try {
            if (!predefindedNames.contains(identifier.getName())) {
                SymbolTableItem Item = SymbolTable.top
                        .getItem(DataDecSymbolTableItem.START_KEY + identifier.getName());
                if (Item instanceof DataDecSymbolTableItem) {
                    DataDecSymbolTableItem DataDecItem = (DataDecSymbolTableItem) Item;
                    if (weAreInExpression && DataDecItem.IsValueNull()) {
                        System.out.println("Line " + identifier.getLine() + ": " + identifier.getName()
                                + " is uninitialized variable");
                    }
                    if (cuttentRepeatTimes != null && weAreInLValue) {
                        DataDecItem.addRepeatTimes(cuttentRepeatTimes);
                        int conflictTime = DataDecItem.hasConflict();
                        if (conflictTime != -1) {
                            System.out.println("Line " + identifier.getLine() + ": " + identifier.getName()
                                    + " has nondeterministic value at time " + conflictTime);
                        }
                    }
                }

            }
        } catch (ItemNotFoundException e) {
            System.out
                    .println("Line " + identifier.getLine() + ": " + identifier.getName() + " is undeclared variable");
        }
        return null;
    }

    @Override
    public Void visit(RepeatLoop repeatLoop) {
        Integer times = 0;
        Integer period = 0;
        if (repeatLoop.getExpression() != null) {
            repeatLoop.getExpression().accept(this);
            if (repeatLoop.getExpression() instanceof main.ast.nodes.Types.Number) {
                main.ast.nodes.Types.Number Number = (main.ast.nodes.Types.Number) repeatLoop.getExpression();
                times = Integer.parseInt(Number.getText());
            }
        }
        if (repeatLoop.getPeriodDeclaration() != null) {
            repeatLoop.getPeriodDeclaration().accept(this);
            Expression periodExp = repeatLoop.getPeriodDeclaration().getExpression();
            if (periodExp instanceof main.ast.nodes.Types.Number) {
                main.ast.nodes.Types.Number Number = (main.ast.nodes.Types.Number) periodExp;
                period = Integer.parseInt(Number.getText());
            }

        }

        if (repeatLoop.getStatementOrNull() != null) {
            if (times != 0 && period != 0) {
                cuttentRepeatTimes = new DataDecSymbolTableItem.IntPair(times, period);
            }
            repeatLoop.getStatementOrNull().accept(this);
            cuttentRepeatTimes = null;
        }
        return null;
    }

    @Override
    public Void visit(HierarchicalVariableLvalue hierarchicalVariableLvalue) {
        weAreInLValue = true;
        if (hierarchicalVariableLvalue.getHierarchicalIdentifier() != null) {
            hierarchicalVariableLvalue.getHierarchicalIdentifier().accept(this);
        }
        if (hierarchicalVariableLvalue.getSelect_() != null) {
            hierarchicalVariableLvalue.getSelect_().accept(this);
        }
        weAreInLValue = false;
        return null;
    }

    @Override
    public Void visit(SourceText sourceText) {
        predefindedNames.add("FOREVER");
        predefindedNames.add("display");
        predefindedNames.add("true");
        predefindedNames.add("false");
        SymbolTable.top = new SymbolTable();
        SymbolTable.root = SymbolTable.top;
        sourceText.setSymbolTable(SymbolTable.top);
        for (ComponentDeclaration cd : sourceText.getComponentDeclarationList()) {
            cd.accept(this);
        }
        return null;
    }
}
