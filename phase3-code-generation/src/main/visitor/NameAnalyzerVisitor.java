

package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.Headers.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ParameterPortList.*;
import main.ast.nodes.ParameterPortDeclaration.*;
import main.ast.nodes.PortDecl.*;
import main.ast.nodes.PortDirection.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.ComponentItem.Dimension.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.ComponentItem.NetDeclaration.*;
import main.ast.nodes.ComponentItem.GenerateItem.*;
import main.ast.nodes.ComponentItem.ListOfPortConnections.*;
import main.ast.nodes.Expression.*;
import main.ast.nodes.Types.*;
import main.ast.nodes.ListOfParameterAssignments.*;
import main.symbolTable.*;
import main.symbolTable.exceptions.ItemAlreadyExistsException;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.item.*;

import java.util.ArrayList;
import java.util.List;

public class NameAnalyzerVisitor extends Visitor<Void> {

    ArrayList<String> predefindedNames = new ArrayList<>();


    @Override
    public Void visit(ConfigHeaderDeclaration node) {

        ConfigDecSymbolTableItem ConfigDecItem = new ConfigDecSymbolTableItem(node);
        try {
            SymbolTable.top.put(ConfigDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + node.getName() +"\" in line " + node.getLine());
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
        return null;
    }


    @Override
    public Void visit(ItemHeaderDeclaration node) {
        ComponentDecSymbolTableItem ComponentDecItem = new ComponentDecSymbolTableItem(node);
        try {
            SymbolTable.top.put(ComponentDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + node.getName() +"\" in line " + node.getLine());
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
        return null;
    }

    @Override
    public Void visit(DotNamedPortConnection dotNamedPortConnection) {
        if (dotNamedPortConnection.getIdentifier() != null) {
//            dotNamedPortConnection.getIdentifier().accept(this);
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
            System.out.println("Redefinition of function \"" + netDeclAssignment.getName() +"\" in line " + netDeclAssignment.getLine());
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
    public Void visit(ComponentItemDeclarationEmpty componentItemDeclarationEmpty) {
        if (componentItemDeclarationEmpty.getListOfGenvarIdentifiers() != null) {
            for (Identifier id : componentItemDeclarationEmpty.getListOfGenvarIdentifiers()) {
                GenvarDecSymbolTable GenvarDecItem = new GenvarDecSymbolTable(id);
                try {
                    SymbolTable.top.put(GenvarDecItem);
                } catch (ItemAlreadyExistsException e) {
                    System.out.println("Redefinition of function \"" + id.getName() +"\" in line " + id.getLine());
                }
                id.accept(this);
            }
        }
        return null;
    }


    @Override
    public Void visit(ComponentProgramInterfaceInstantiation componentProgramInterfaceInstantiation) {

        try {
            SymbolTable.top.getItem(ConfigDecSymbolTableItem.START_KEY + componentProgramInterfaceInstantiation.getNumOfPorts() + "_" + componentProgramInterfaceInstantiation.getIdentifier().getName());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Line:" + componentProgramInterfaceInstantiation.getLine() + "-> " + componentProgramInterfaceInstantiation.getName() + " not declared");
        }

        if (componentProgramInterfaceInstantiation.getIdentifier() != null) {
//            componentProgramInterfaceInstantiation.getIdentifier().accept(this);
        }

        if (componentProgramInterfaceInstantiation.getParameterValueAssignment() != null) {
            componentProgramInterfaceInstantiation.getParameterValueAssignment().accept(this);
        }

    List<HierarchicalInstance> instances = componentProgramInterfaceInstantiation.getHierarchicalInstances();
        if (instances != null) {
            for (HierarchicalInstance instance : instances) {
                if (instance != null) {
                    instance.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public Void visit(GenerateBlockName generateBlockName) {
        if (generateBlockName.getIdentifier() != null) {
    //        generateBlockName.getIdentifier().accept(this);
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
                    System.out.println("Redefinition of function \"" + id.getName() +"\" in line " + id.getLine());
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
            System.out.println("Redefinition of function \"" + hierarchicalInstance.getName() +"\" in line " + hierarchicalInstance.getLine());
        }

        if (hierarchicalInstance.getNameOfInstance() != null) {
            hierarchicalInstance.getNameOfInstance().accept(this);
        }

        if (hierarchicalInstance.getListOfPortConnections() != null) {
            for(NamedPortConnection namedPortConnection: hierarchicalInstance.getListOfPortConnections())
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
            System.out.println("Redefinition of function \"" + paramAssignment.getName() +"\" in line " + paramAssignment.getLine());
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
    //        namedParameterAssignment.getIdentifier().accept(this);
        }
        if (namedParameterAssignment.getParamExpression() != null) {
            namedParameterAssignment.getParamExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ComponentHeader componentHeader) {
        if (componentHeader.getIdentifier() != null) {
    //        componentHeader.getIdentifier().accept(this);
        }
        if (componentHeader.getParameterPortList() != null) {
            componentHeader.getParameterPortList().accept(this);
        }
        if (componentHeader.getListOfPortDeclarations() != null) {
            for (PortDecl portDecl: componentHeader.getListOfPortDeclarations())
                portDecl.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ConfigurationHeader configurationHeader) {
        if (configurationHeader.getIdentifier() != null) {
    //        configurationHeader.getIdentifier().accept(this);
        }
        if (configurationHeader.getParameterPortList() != null) {
            configurationHeader.getParameterPortList().accept(this);
        }
        if (configurationHeader.getListOfPortDeclarations() != null) {
            for (PortDecl portDecl: configurationHeader.getListOfPortDeclarations())
                portDecl.accept(this);
        }
        return null;
    }

    // -------------------------------------------------------------
    // Ports Declaration children
    // -------------------------------------------------------------
    @Override
    public Void visit(DataTypePortDecl dataTypePortDecl) {
        PortDecSymbolTableItem PortDecItem = new PortDecSymbolTableItem(dataTypePortDecl);
        try {
            SymbolTable.top.put(PortDecItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Redefinition of function \"" + dataTypePortDecl.getName() +"\" in line " + dataTypePortDecl.getLine());
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
            System.out.println("Redefinition of function \"" + implicitDataPortDecl.getName() +"\" in line " + implicitDataPortDecl.getLine());
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
            System.out.println("Redefinition of function \"" + netTypePortDecl.getName() +"\" in line " + netTypePortDecl.getLine());
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
                SymbolTable.top.getItem(DataDecSymbolTableItem.START_KEY + identifier.getName());
    //        SymbolTable.top.getItem(identifier.getName());
            }
        }
        catch (ItemNotFoundException e) {
            System.out.println("Line:" + identifier.getLine() + "-> " + identifier.getName() + " not declared");
        }
        return null;
    }

    @Override
    public Void visit(SourceText sourceText) {
        predefindedNames.add("FOREVER");
        predefindedNames.add("display");
        SymbolTable.top = new SymbolTable();
        SymbolTable.root = SymbolTable.top;
        sourceText.setSymbolTable(SymbolTable.top);
        for (ComponentDeclaration cd : sourceText.getComponentDeclarationList()) {
            cd.accept(this);
        }
        return null;
    }
}
