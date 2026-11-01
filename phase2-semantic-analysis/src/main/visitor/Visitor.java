package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.ComponentDeclaration.*;

import main.ast.nodes.ComponentItem.Dimension.PackedDimension;
import main.ast.nodes.ComponentItem.Dimension.RangePackedDimension;
import main.ast.nodes.ComponentItem.Dimension.UnsizedDimension;
import main.ast.nodes.ComponentItem.ListOfPortConnections.*;
import main.ast.nodes.ComponentItem.ListOfPortConnections.PortAssign;
import main.ast.nodes.ComponentItem.ListOfPortConnections.WildcardNamedPortConnection;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.ComponentItem.NetDeclaration.*;
import main.ast.nodes.Headers.*;
import main.ast.nodes.Expression.*;
import main.ast.nodes.ListOfParameterAssignments.*;
import main.ast.nodes.ParameterPortDeclaration.*;
import main.ast.nodes.PortDecl.DataTypePortDecl;
import main.ast.nodes.PortDecl.ImplicitDataPortDecl;
import main.ast.nodes.PortDecl.NetTypePortDecl;
import main.ast.nodes.PortDecl.PortDecl;
import main.ast.nodes.PortDirection.*;
import main.ast.nodes.ParameterPortList.*;
import main.ast.nodes.ComponentItem.GenerateItem.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.Types.*;
import main.ast.nodes.Types.Number;

import java.util.List;

public abstract class Visitor<T> implements IVisitor<T> {
    protected void merge(T into, T from) {
    }

    protected T defaultResult() {
        return null;
    }

    boolean weAreInExpression = false;

    // -------------------------------------------------------------
    // Root
    // -------------------------------------------------------------

    @Override
    public T visit(SourceText sourceText) {
        T result = defaultResult();
        List<ComponentDeclaration> components = sourceText.getComponentDeclarationList();
        if (components != null) {
            for (ComponentDeclaration componentDeclaration : components) {
                if (componentDeclaration != null) {
                    merge(result, componentDeclaration.accept(this));
                }
            }
        }
        return result;
    }

    // -------------------------------------------------------------
    // Component declarations
    // -------------------------------------------------------------

    @Override
    public T visit(ItemHeaderDeclaration node) {
        T result = defaultResult();
        if (node.getComponentHeader() != null) {
            merge(result, node.getComponentHeader().accept(this));
        }
        List<ComponentItem> componentItems = node.getComponentItemList();
        if (componentItems != null) {
            for (ComponentItem item : componentItems) {
                if (item != null)
                    merge(result, item.accept(this));
            }
        }
        if (node.getComponentName() != null) {
            merge(result, node.getComponentName().accept(this));
        }
        return result;
    }

    @Override
    public T visit(IdentifierComponent identifierComponent) {
        T result = defaultResult();
        if (identifierComponent.getIdentifier() != null) {
            merge(result, identifierComponent.getIdentifier().accept(this));
        }

        List<ComponentItem> componentItems = identifierComponent.getComponentItemList();
        if (componentItems != null) {
            for (ComponentItem componentItem : componentItems) {
                if (componentItem != null) {
                    merge(result, componentItem.accept(this));
                }
            }
        }

        if (identifierComponent.getComponentName() != null) {
            merge(result, identifierComponent.getComponentName().accept(this));
        }

        return result;
    }

    @Override
    public T visit(ConfigHeaderDeclaration node) {
        T result = defaultResult();

        if (node.getConfigurationHeader() != null) {
            merge(result, node.getConfigurationHeader().accept(this));
        }
        List<ComponentItem> componentItems = node.getComponentItemList();
        if (componentItems != null) {
            for (ComponentItem item : componentItems) {
                if (item != null) {
                    merge(result, item.accept(this));
                }
            }
        }
        if (node.getComponentName() != null) {
            merge(result, node.getComponentName().accept(this));
        }

        return result;
    }

    @Override
    public T visit(ConfigDeclaration configDeclaration) {
        T result = defaultResult();

        if (configDeclaration.getIdentifier() != null) {
            merge(result, configDeclaration.getIdentifier().accept(this));
        }

        List<ComponentItem> componentItems = configDeclaration.getComponentItemList();
        if (componentItems != null) {
            for (ComponentItem item : componentItems) {
                if (item != null) {
                    merge(result, item.accept(this));
                }
            }
        }
        if (configDeclaration.getComponentName() != null) {
            merge(result, configDeclaration.getComponentName().accept(this));
        }
        return result;
    }

    // -------------------------------------------------------------
    // Headers & names
    // -------------------------------------------------------------

    @Override
    public T visit(ComponentHeader componentHeader) {
        T result = defaultResult();
        if (componentHeader.getIdentifier() != null) {
            merge(result, componentHeader.getIdentifier().accept(this));
        }
        if (componentHeader.getParameterPortList() != null) {
            merge(result, componentHeader.getParameterPortList().accept(this));
        }
        if (componentHeader.getListOfPortDeclarations() != null) {
            for (PortDecl portDecl : componentHeader.getListOfPortDeclarations())
                merge(result, portDecl.accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConfigurationHeader configurationHeader) {
        T result = defaultResult();
        if (configurationHeader.getIdentifier() != null) {
            merge(result, configurationHeader.getIdentifier().accept(this));
        }
        if (configurationHeader.getParameterPortList() != null) {
            merge(result, configurationHeader.getParameterPortList().accept(this));
        }
        if (configurationHeader.getListOfPortDeclarations() != null) {
            for (PortDecl portDecl : configurationHeader.getListOfPortDeclarations())
                merge(result, portDecl.accept(this));
        }
        return result;
    }

    // -------------------------------------------------------------
    // Parameter-port list & declarations
    // -------------------------------------------------------------

    @Override
    public T visit(ListBased listBased) {
        T result = defaultResult();
        if (listBased.getListOfParamAssignments() != null) {
            for (ParamAssignment paramAssignment : listBased.getListOfParamAssignments())
                merge(result, paramAssignment.accept(this));
        }
        if (listBased.getParameterPortDeclarations() != null) {
            for (ParameterPortDeclaration decl : listBased.getParameterPortDeclarations()) {
                if (decl != null) {
                    merge(result, decl.accept(this));
                }
            }
        }

        return result;
    }

    @Override
    public T visit(ParamAssignment paramAssignment) {
        T result = defaultResult();
        if (paramAssignment.getIdentifier() != null) {
            merge(result, paramAssignment.getIdentifier().accept(this));
        }

        List<UnpackedDimension> dimensions = paramAssignment.getUnpackedDimensions();
        if (dimensions != null) {
            for (UnpackedDimension dim : dimensions) {
                if (dim != null) {
                    merge(result, dim.accept(this));
                }
            }
        }

        return result;
    }

    @Override
    public T visit(DeclarationBased declarationBased) {
        T result = defaultResult();
        List<ParameterPortDeclaration> decls = declarationBased.getParameterPortDeclarations();
        if (decls != null) {
            for (ParameterPortDeclaration d : decls) {
                if (d != null) {
                    merge(result, d.accept(this));
                }
            }
        }
        return result;
    }

    @Override
    public T visit(EmptyBased emptyBased) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(ParameterAssignmentBased parameterAssignmentBased) {
        T result = defaultResult();
        if (parameterAssignmentBased.getDataType() != null) {
            merge(result, parameterAssignmentBased.getDataType().accept(this));
        }
        if (parameterAssignmentBased.getListOfParamAssignments() != null) {
            for (ParamAssignment paramAssignment : parameterAssignmentBased.getListOfParamAssignments())
                merge(result, paramAssignment.accept(this));
        }
        return result;
    }

    @Override
    public T visit(ParameterDeclaration parameterDeclaration) {
        T result = defaultResult();
        if (parameterDeclaration.getDataTypeOrImplicit() != null) {
            merge(result, parameterDeclaration.getDataTypeOrImplicit().accept(this));
        }

        if (parameterDeclaration.getListOfParamAssignments() != null) {
            for (ParamAssignment paramAssignment : parameterDeclaration.getListOfParamAssignments())
                merge(result, paramAssignment.accept(this));
        }

        return result;
    }

    @Override
    public T visit(ParameterDeclarationBased paramDeclBased) {
        T result = defaultResult();
        if (paramDeclBased.getParameterDeclaration() != null) {
            merge(result, paramDeclBased.getParameterDeclaration().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ParameterPortDeclaration parameterPortDecl) {
        T result = defaultResult();
        return result;
    }

    // -------------------------------------------------------------
    // Ports
    // -------------------------------------------------------------

    @Override
    public T visit(ImplicitDataPortDecl implicitDataPortDecl) {
        T result = defaultResult();
        if (implicitDataPortDecl.getPortDirection() != null) {
            merge(result, implicitDataPortDecl.getPortDirection().accept(this));
        }
        if (implicitDataPortDecl.getImplicitDataType() != null) {
            merge(result, implicitDataPortDecl.getImplicitDataType().accept(this));
        }
        if (implicitDataPortDecl.getIdentifier() != null) {
            merge(result, implicitDataPortDecl.getIdentifier().accept(this));
        }
        for (UnpackedDimension dim : implicitDataPortDecl.getUnpackedDimensions()) {
            if (dim != null) {
                merge(result, dim.accept(this));
            }
        }
        if (implicitDataPortDecl.getConstantExpression() != null) {
            merge(result, implicitDataPortDecl.getConstantExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(NetTypePortDecl netTypePortDecl) {
        T result = defaultResult();
        if (netTypePortDecl.getPortDirection() != null) {
            merge(result, netTypePortDecl.getPortDirection().accept(this));
        }
        if (netTypePortDecl.getDataTypeOrImplicit() != null) {
            merge(result, netTypePortDecl.getDataTypeOrImplicit().accept(this));
        }
        if (netTypePortDecl.getIdentifier() != null) {
            merge(result, netTypePortDecl.getIdentifier().accept(this));
        }
        for (UnpackedDimension dim : netTypePortDecl.getUnpackedDimensions()) {
            if (dim != null) {
                merge(result, dim.accept(this));
            }
        }
        if (netTypePortDecl.getConstantExpression() != null) {
            merge(result, netTypePortDecl.getConstantExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(DataTypePortDecl dataTypePortDecl) {
        T result = defaultResult();
        if (dataTypePortDecl.getPortDirection() != null) {
            merge(result, dataTypePortDecl.getPortDirection().accept(this));
        }
        if (dataTypePortDecl.getDataType() != null) {
            merge(result, dataTypePortDecl.getDataType().accept(this));
        }
        if (dataTypePortDecl.getIdentifier() != null) {
            merge(result, dataTypePortDecl.getIdentifier().accept(this));
        }
        if (dataTypePortDecl.getConstantExpression() != null) {
            merge(result, dataTypePortDecl.getConstantExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(InPortDirection inPortDirection) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(OutPortDirection outPortDirection) {
        T result = defaultResult();
        return result;
    }

    // -------------------------------------------------------------
    // Net declarations & dimensions
    // -------------------------------------------------------------

    @Override
    public T visit(NetTypeNetDeclaration netTypeNetDeclaration) {
        T result = defaultResult();
        if (netTypeNetDeclaration.getDataTypeOrImplicit() != null) {
            merge(result, netTypeNetDeclaration.getDataTypeOrImplicit().accept(this));
        }
        if (netTypeNetDeclaration.getListOfNetDeclAssignments() != null) {
            for (NetDeclAssignment netDeclAssignment : netTypeNetDeclaration.getListOfNetDeclAssignments())
                merge(result, netDeclAssignment.accept(this));
        }
        return result;
    }

    @Override
    public T visit(IdentifierNetDeclaration identifierNetDeclaration) {
        T result = defaultResult();
        if (identifierNetDeclaration.getIdentifier() != null) {
            merge(result, identifierNetDeclaration.getIdentifier().accept(this));
        }
        if (identifierNetDeclaration.getListOfNetDeclAssignments() != null) {
            for (NetDeclAssignment netDeclAssignment : identifierNetDeclaration.getListOfNetDeclAssignments())
                merge(result, netDeclAssignment.accept(this));
        }
        return result;
    }

    @Override
    public T visit(NetDeclAssignment netDeclAssignment) {
        T result = defaultResult();
        if (netDeclAssignment.getIdentifier() != null) {
            merge(result, netDeclAssignment.getIdentifier().accept(this));
        }
        List<UnpackedDimension> dims = netDeclAssignment.getUnpackedDimensions();
        if (dims != null) {
            for (UnpackedDimension ud : dims) {
                if (ud != null) {
                    merge(result, ud.accept(this));
                }
            }
        }
        if (netDeclAssignment.getExpression() != null) {
            merge(result, netDeclAssignment.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(RangeUnpackedDimension rangeUnpackedDimension) {
        T result = defaultResult();
        if (rangeUnpackedDimension.getConstantRange() != null) {
            merge(result, rangeUnpackedDimension.getConstantRange().accept(this));
        }
        return result;
    }

    @Override
    public T visit(SingleUnpackedDimension singleUnpackedDimension) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(RangePackedDimension rangePackedDimension) {
        T result = defaultResult();
        if (rangePackedDimension.getConstantRange() != null) {
            merge(result, rangePackedDimension.getConstantRange().accept(this));
        }
        return result;
    }

    @Override
    public T visit(UnsizedDimension unsizedDimension) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(ImplicitDataType implicitDataType) {
        T result = defaultResult();
        List<PackedDimension> dims = implicitDataType.getPackedDimensions();
        if (dims != null) {
            for (PackedDimension pd : dims) {
                if (pd != null) {
                    merge(result, pd.accept(this));
                }
            }
        }
        return result;
    }

    // -------------------------------------------------------------
    // Generate region / generate items
    // -------------------------------------------------------------

    @Override
    public T visit(GenerateRegion generateRegion) {
        T result = defaultResult();
        List<GenerateItem> items = generateRegion.getGenerateItems();
        if (items != null) {
            for (GenerateItem item : items) {
                if (item != null) {
                    merge(result, item.accept(this));
                }
            }
        }

        return result;
    }

    @Override
    public T visit(LoopGenerateConstruct loopGenerateConstruct) {
        T result = defaultResult();
        if (loopGenerateConstruct.getGenvarInitialization() != null) {
            merge(result, loopGenerateConstruct.getGenvarInitialization().accept(this));
        }
        if (loopGenerateConstruct.getGenvarExpression() != null) {
            merge(result, loopGenerateConstruct.getGenvarExpression().accept(this));
        }
        if (loopGenerateConstruct.getGenvarIteration() != null) {
            merge(result, loopGenerateConstruct.getGenvarIteration().accept(this));
        }
        if (loopGenerateConstruct.getGenerateBlock() != null) {
            merge(result, loopGenerateConstruct.getGenerateBlock().accept(this));
        }
        return result;
    }

    @Override
    public T visit(AssignGenvarIteration assignGenvarIteration) {
        T result = defaultResult();
        if (assignGenvarIteration.getIdentifier() != null) {
            merge(result, assignGenvarIteration.getIdentifier().accept(this));
        }

        if (assignGenvarIteration.getGenvarExpression() != null) {
            merge(result, assignGenvarIteration.getGenvarExpression().accept(this));
        }

        return result;
    }

    @Override
    public T visit(PreIncDecGenvarIteration preIncDecGenvarIteration) {
        T result = defaultResult();

        if (preIncDecGenvarIteration.getIdentifier() != null) {
            merge(result, preIncDecGenvarIteration.getIdentifier().accept(this));
        }

        return result;
    }

    @Override
    public T visit(PostIncDecGenvarIteration postIncDecGenvarIteration) {
        T result = defaultResult();
        if (postIncDecGenvarIteration.getIdentifier() != null) {
            merge(result, postIncDecGenvarIteration.getIdentifier().accept(this));
        }

        return result;
    }

    @Override
    public T visit(GenvarDeclaration genvarDeclaration) {
        T result = defaultResult();
        if (genvarDeclaration.getListOfGenvarIdentifiers() != null) {
            for (Identifier id : genvarDeclaration.getListOfGenvarIdentifiers()) {
                merge(result, id.accept(this));
            }
        }
        return result;
    }

    @Override
    public T visit(GenvarInitialization genvarInitialization) {
        T result = defaultResult();
        if (genvarInitialization.getIdentifier() != null) {
            merge(result, genvarInitialization.getIdentifier().accept(this));
        }

        if (genvarInitialization.getConstantExpression() != null) {
            merge(result, genvarInitialization.getConstantExpression().accept(this));
        }

        return result;
    }

    @Override
    public T visit(DoGenerateBlock doGenerateBlock) {
        T result = defaultResult();
        if (doGenerateBlock.getGenerateBlockLabel() != null) {
            merge(result, doGenerateBlock.getGenerateBlockLabel().accept(this));
        }
        if (doGenerateBlock.getBeginGenerateBlockName() != null) {
            merge(result, doGenerateBlock.getBeginGenerateBlockName().accept(this));
        }

        List<GenerateItem> items = doGenerateBlock.getGenerateItems();
        if (items != null) {
            for (GenerateItem item : items) {
                if (item != null) {
                    merge(result, item.accept(this));
                }
            }
        }

        if (doGenerateBlock.getEndGenerateBlockName() != null) {
            merge(result, doGenerateBlock.getEndGenerateBlockName().accept(this));
        }

        return result;
    }

    @Override
    public T visit(GenerateBlockLabel generateBlockLabel) {
        T result = defaultResult();
        if (generateBlockLabel.getIdentifier() != null) {
            merge(result, generateBlockLabel.getIdentifier().accept(this));
        }
        return result;
    }

    @Override
    public T visit(GenerateBlockName generateBlockName) {
        T result = defaultResult();
        if (generateBlockName.getIdentifier() != null) {
            merge(result, generateBlockName.getIdentifier().accept(this));
        }
        return result;
    }

    @Override
    public T visit(EmptyGenerateItem emptyGenerateItem) {
        T result = defaultResult();
        return result;
    }

    public T visit(GenerateItem node) {
        T result = defaultResult();
        return result;
    }

    // -------------------------------------------------------------
    // Continuous assignment (set) and net assignments
    // -------------------------------------------------------------

    @Override
    public T visit(ContinuousSet continuousSet) {
        T result = defaultResult();
        if (continuousSet.getListOfNetAssignments() != null) {
            for (NetAssignment netAssignment : continuousSet.getListOfNetAssignments())
                merge(result, netAssignment.accept(this));
        }
        return result;
    }

    @Override
    public T visit(NetAssignment netAssignment) {
        T result = defaultResult();
        if (netAssignment.getNetLvalue() != null) {
            merge(result, netAssignment.getNetLvalue().accept(this));
        }
        if (netAssignment.getExpression() != null) {
            merge(result, netAssignment.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(IdentifierNetLvalue identifierNetLvalue) {
        T result = defaultResult();
        if (identifierNetLvalue.getIdentifier() != null) {
            merge(result, identifierNetLvalue.getIdentifier().accept(this));
        }
        if (identifierNetLvalue.getConstantSelect() != null) {
            merge(result, identifierNetLvalue.getConstantSelect().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConcatenationNetLvalue concatenationNetLvalue) {
        T result = defaultResult();
        List<NetLvalue> lvalues = concatenationNetLvalue.getNetLvalues();
        if (lvalues != null) {
            for (NetLvalue nl : lvalues) {
                if (nl != null) {
                    merge(result, nl.accept(this));
                }
            }
        }
        return result;
    }

    // -------------------------------------------------------------
    // Component-program / interface instantiation
    // -------------------------------------------------------------
    @Override
    public T visit(ComponentProgramInterfaceInstantiation componentProgramInterfaceInstantiation) {
        T result = defaultResult();
        if (componentProgramInterfaceInstantiation.getIdentifier() != null) {
            merge(result, componentProgramInterfaceInstantiation.getIdentifier().accept(this));
        }
        if (componentProgramInterfaceInstantiation.getParameterValueAssignment() != null) {
            merge(result, componentProgramInterfaceInstantiation.getParameterValueAssignment().accept(this));
        }
        List<HierarchicalInstance> instances = componentProgramInterfaceInstantiation.getHierarchicalInstances();
        if (instances != null) {
            for (HierarchicalInstance instance : instances) {
                if (instance != null) {
                    merge(result, instance.accept(this));
                }
            }
        }
        return result;
    }

    @Override
    public T visit(ParameterValueAssignment parameterValueAssignment) {
        T result = defaultResult();
        if (parameterValueAssignment.getListOfParameterAssignments() != null) {
            merge(result, parameterValueAssignment.getListOfParameterAssignments().accept(this));
        }
        return result;
    }

    @Override
    public T visit(UnnamedListOfParameterAssignments unnamedList) {
        T result = defaultResult();
        List<ParamExpression> paramExpressions = unnamedList.getParamExpressions();
        if (paramExpressions != null) {
            for (ParamExpression pe : paramExpressions) {
                if (pe != null) {
                    merge(result, pe.accept(this));
                }
            }
        }
        return result;
    }

    @Override
    public T visit(NamedListOfParameterAssignments namedList) {
        T result = defaultResult();
        List<NamedParameterAssignment> namedAssignments = namedList.getNamedParameterAssignments();
        if (namedAssignments != null) {
            for (NamedParameterAssignment npa : namedAssignments) {
                if (npa != null) {
                    merge(result, npa.accept(this));
                }
            }
        }
        return result;
    }

    @Override
    public T visit(NamedParameterAssignment namedParameterAssignment) {
        T result = defaultResult();
        if (namedParameterAssignment.getIdentifier() != null) {
            merge(result, namedParameterAssignment.getIdentifier().accept(this));
        }
        if (namedParameterAssignment.getParamExpression() != null) {
            merge(result, namedParameterAssignment.getParamExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(HierarchicalInstance hierarchicalInstance) {
        T result = defaultResult();
        if (hierarchicalInstance.getNameOfInstance() != null) {
            merge(result, hierarchicalInstance.getNameOfInstance().accept(this));
        }
        if (hierarchicalInstance.getListOfPortConnections() != null) {
            for (NamedPortConnection namedPortConnection : hierarchicalInstance.getListOfPortConnections())
                merge(result, namedPortConnection.accept(this));
        }
        return result;
    }

    @Override
    public T visit(NameOfInstance nameOfInstance) {
        T result = defaultResult();
        if (nameOfInstance.getIdentifier() != null) {
            merge(result, nameOfInstance.getIdentifier().accept(this));
        }
        List<UnpackedDimension> dims = nameOfInstance.getUnpackedDimensions();
        if (dims != null) {
            for (UnpackedDimension ud : dims) {
                if (ud != null) {
                    merge(result, ud.accept(this));
                }
            }
        }
        return result;
    }

    @Override
    public T visit(DotNamedPortConnection dotNamedPortConnection) {
        T result = defaultResult();
        if (dotNamedPortConnection.getIdentifier() != null) {
            merge(result, dotNamedPortConnection.getIdentifier().accept(this));
        }
        if (dotNamedPortConnection.getPortAssign() != null) {
            merge(result, dotNamedPortConnection.getPortAssign().accept(this));
        }
        return result;
    }

    @Override
    public T visit(WildcardNamedPortConnection wildcardNamedPortConnection) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(PortAssign portAssign) {
        T result = defaultResult();
        if (portAssign.getExpression() != null) {
            merge(result, portAssign.getExpression().accept(this));
        }
        return result;
    }

    // -------------------------------------------------------------
    // Loops (loop_statement)
    // -------------------------------------------------------------

    @Override
    public T visit(ForeverLoop foreverLoop) {
        T result = defaultResult();
        if (foreverLoop.getStatementOrNull() != null) {
            merge(result, foreverLoop.getStatementOrNull().accept(this));
        }
        return result;
    }

    @Override
    public T visit(RepeatLoop repeatLoop) {
        T result = defaultResult();
        if (repeatLoop.getExpression() != null) {
            merge(result, repeatLoop.getExpression().accept(this));
        }
        if (repeatLoop.getPeriodDeclaration() != null) {
            merge(result, repeatLoop.getPeriodDeclaration().accept(this));
        }
        if (repeatLoop.getStatementOrNull() != null) {
            merge(result, repeatLoop.getStatementOrNull().accept(this));
        }
        return result;
    }

    @Override
    public T visit(WhileLoop whileLoop) {
        T result = defaultResult();
        if (whileLoop.getExpression() != null) {
            merge(result, whileLoop.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(PeriodDeclaration periodDeclaration) {
        T result = defaultResult();
        if (periodDeclaration.getExpression() != null) {
            merge(result, periodDeclaration.getExpression().accept(this));
        }
        return result;
    }
    // -------------------------------------------------------------
    // Expressions / ranges / selects
    // -------------------------------------------------------------

    @Override
    public T visit(MintypmaxExpression mintypmaxExpression) {
        T result = defaultResult();
        if (mintypmaxExpression.getMinExpression() != null) {
            merge(result, mintypmaxExpression.getMinExpression().accept(this));
        }
        if (mintypmaxExpression.getTypExpression() != null) {
            merge(result, mintypmaxExpression.getTypExpression().accept(this));
        }
        if (mintypmaxExpression.getMaxExpression() != null) {
            merge(result, mintypmaxExpression.getMaxExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantRange constantRange) {
        T result = defaultResult();
        if (constantRange.getLeft() != null) {
            merge(result, constantRange.getLeft().accept(this));
        }
        if (constantRange.getRight() != null) {
            merge(result, constantRange.getRight().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantBitSelect constantBitSelect) {
        T result = defaultResult();
        for (ConstantExpression expr : constantBitSelect.getConstantExpressions()) {
            merge(result, expr.accept(this));
        }
        return result;
    }

    @Override
    public T visit(Expression expression) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(ParamExpression paramExpression) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(PrimaryLiteral primaryLiteral) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(BitSelect bitSelect) {
        T result = defaultResult();
        if (bitSelect.getExpressions() != null) {
            for (Expression expr : bitSelect.getExpressions()) {
                if (expr != null) {
                    merge(result, expr.accept(this));
                }
            }
        }
        return result;
    }

    @Override
    public T visit(RangeOnlySelect rangeOnlySelect) {
        T result = defaultResult();
        if (rangeOnlySelect.getPartSelectRange() != null) {
            merge(result, rangeOnlySelect.getPartSelectRange().accept(this));
        }
        return result;
    }

    @Override
    public T visit(BitSelectWithRange bitSelectWithRange) {
        T result = defaultResult();
        if (bitSelectWithRange.getBitSelect() != null) {
            merge(result, bitSelectWithRange.getBitSelect().accept(this));
        }
        if (bitSelectWithRange.getPartSelectRange() != null) {
            merge(result, bitSelectWithRange.getPartSelectRange().accept(this));
        }
        return result;
    }

    @Override
    public T visit(HierarchicalVariableLvalue hierarchicalVariableLvalue) {
        T result = defaultResult();
        if (hierarchicalVariableLvalue.getHierarchicalIdentifier() != null) {
            merge(result, hierarchicalVariableLvalue.getHierarchicalIdentifier().accept(this));
        }
        if (hierarchicalVariableLvalue.getSelect_() != null) {
            merge(result, hierarchicalVariableLvalue.getSelect_().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConcatenationVariableLvalue concatenation) {
        T result = defaultResult();
        for (VariableLvalue v : concatenation.getVariableLvalues()) {
            if (v != null) {
                merge(result, v.accept(this));
            }
        }
        return result;
    }

    @Override
    public T visit(OrderedArg orderedArg) {
        T result = defaultResult();
        if (orderedArg.getExpression() != null) {
            merge(result, orderedArg.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(NamedArg namedArg) {
        T result = defaultResult();
        if (namedArg.getIdentifier() != null) {
            merge(result, namedArg.getIdentifier().accept(this));
        }
        if (namedArg.getExpression() != null) {
            merge(result, namedArg.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(MixedListOfArguments mixedListOfArguments) {
        T result = defaultResult();
        for (OrderedArg orderedArg : mixedListOfArguments.getOrderedArgs()) {
            if (orderedArg != null) {
                merge(result, orderedArg.accept(this));
            }
        }
        for (NamedArg namedArg : mixedListOfArguments.getNamedArgs()) {
            if (namedArg != null) {
                merge(result, namedArg.accept(this));
            }
        }
        return result;
    }

    @Override
    public T visit(NamedOnlyListOfArguments namedOnlyListOfArguments) {
        T result = defaultResult();
        for (NamedArg namedArg : namedOnlyListOfArguments.getNamedArgs()) {
            if (namedArg != null) {
                merge(result, namedArg.accept(this));
            }
        }
        return result;
    }

    @Override
    public T visit(ArgList argList) {
        T result = defaultResult();
        if (argList.getListOfArguments() != null) {
            merge(result, argList.getListOfArguments().accept(this));
        }
        return result;
    }

    @Override
    public T visit(SubroutineCall subroutineCall) {
        T result = defaultResult();
        if (subroutineCall.getIdentifier() != null) {
            merge(result, subroutineCall.getIdentifier().accept(this));
        }
        if (subroutineCall.getArgList() != null) {
            merge(result, subroutineCall.getArgList().accept(this));
        }
        return result;
    }

    @Override
    public T visit(SimpleSubroutineCallStatement stmt) {
        T result = defaultResult();
        if (stmt.getSubroutineCall() != null) {
            merge(result, stmt.getSubroutineCall().accept(this));
        }
        return result;
    }

    @Override
    public T visit(VoidSubroutineCallStatement stmt) {
        T result = defaultResult();
        if (stmt.getSubroutineCall() != null) {
            merge(result, stmt.getSubroutineCall().accept(this));
        }
        return result;
    }

    @Override
    public T visit(NonEmptyConcatenation nonEmptyConcatenation) {
        T result = defaultResult();
        for (Expression expr : nonEmptyConcatenation.getExpressions()) {
            if (expr != null) {
                merge(result, expr.accept(this));
            }
        }
        return result;
    }

    @Override
    public T visit(EmptyConcatenation emptyConcatenation) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(ConstantConcatenation constantConcatenation) {
        T result = defaultResult();
        for (ConstantExpression expr : constantConcatenation.getConstantExpressions()) {
            if (expr != null) {
                merge(result, expr.accept(this));
            }
        }
        return result;
    }

    @Override
    public T visit(Statement statement) {
        T result = defaultResult();
        if (statement.getBlockLabel() != null) {
            merge(result, statement.getBlockLabel().accept(this));
        }
        if (statement.getStatementItem() != null) {
            merge(result, statement.getStatementItem().accept(this));
        }
        return result;
    }

    @Override
    public T visit(NonEmptyStatementOrNull nonEmptyStatementOrNull) {
        T result = defaultResult();
        if (nonEmptyStatementOrNull != null)
            merge(result, nonEmptyStatementOrNull.getStatement().accept(this));
        return result;
    }

    @Override
    public T visit(EmptyStatementOrNull emptyStatementOrNull) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(CondPredicate condPredicate) {
        T result = defaultResult();
        List<Expression> expressions = condPredicate.getExpressions();
        if (expressions != null) {
            for (Expression expression : expressions) {
                if (expression != null) {
                    merge(result, expression.accept(this));
                }
            }
        }
        return result;
    }

    @Override
    public T visit(ConditionalStatement conditionalStatement) {
        T result = defaultResult();
        if (conditionalStatement.getCondPredicate() != null) {
            merge(result, conditionalStatement.getCondPredicate().accept(this));
        }

        if (conditionalStatement.getThenStatement() != null) {
            merge(result, conditionalStatement.getThenStatement().accept(this));
        }

        if (conditionalStatement.getElseStatement() != null) {
            merge(result, conditionalStatement.getElseStatement().accept(this));
        }

        return result;
    }

    @Override
    public T visit(VariableAssignment variableAssignment) {
        T result = defaultResult();
        if (variableAssignment.getVariableLvalue() != null) {
            merge(result, variableAssignment.getVariableLvalue().accept(this));
        }

        if (variableAssignment.getExpression() != null) {
            merge(result, variableAssignment.getExpression().accept(this));
        }

        return result;
    }

    @Override
    public T visit(ProceduralContinuousSet proceduralContinuousSet) {
        T result = defaultResult();
        if (proceduralContinuousSet.getVariableAssignment() != null) {
            merge(result, proceduralContinuousSet.getVariableAssignment().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantMintypmaxExpression constantMintypmaxExpression) {
        T result = defaultResult();
        if (constantMintypmaxExpression.getMinExpression() != null) {
            merge(result, constantMintypmaxExpression.getMinExpression().accept(this));
        }
        if (constantMintypmaxExpression.getTypExpression() != null) {
            merge(result, constantMintypmaxExpression.getTypExpression().accept(this));
        }
        if (constantMintypmaxExpression.getMaxExpression() != null) {
            merge(result, constantMintypmaxExpression.getMaxExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantMintypmaxPrimary constantMintypmaxPrimary) {
        T result = defaultResult();
        if (constantMintypmaxPrimary.getConstantMintypmaxExpression() != null) {
            merge(result, constantMintypmaxPrimary.getConstantMintypmaxExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantNullPrimary constantNullPrimary) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(NonblockingAssignment nonblockingAssignment) {
        T result = defaultResult();
        if (nonblockingAssignment.getVariableLvalue() != null) {
            merge(result, nonblockingAssignment.getVariableLvalue().accept(this));
        }
        if (nonblockingAssignment.getExpression() != null) {
            merge(result, nonblockingAssignment.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantLiteralPrimary constantLiteralPrimary) {
        T result = defaultResult();
        if (constantLiteralPrimary.getPrimaryLiteral() != null) {
            merge(result, constantLiteralPrimary.getPrimaryLiteral().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantIdentifierPrimary constantIdentifierPrimary) {
        T result = defaultResult();
        if (constantIdentifierPrimary.getIdentifier() != null) {
            merge(result, constantIdentifierPrimary.getIdentifier().accept(this));
        }
        if (constantIdentifierPrimary.getConstantSelect() != null) {
            merge(result, constantIdentifierPrimary.getConstantSelect().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantCallPrimary constantCallPrimary) {
        T result = defaultResult();
        if (constantCallPrimary.getIdentifier() != null) {
            merge(result, constantCallPrimary.getIdentifier().accept(this));
        }
        if (constantCallPrimary.getArgList() != null) {
            merge(result, constantCallPrimary.getArgList().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantPrimaryCast constantPrimaryCast) {
        T result = defaultResult();
        if (constantPrimaryCast.getConstantPrimary() != null)
            merge(result, constantPrimaryCast.getConstantPrimary().accept(this));
        if (constantPrimaryCast.getExpression() != null)
            merge(result, constantPrimaryCast.getExpression().accept(this));
        return result;
    }

    @Override
    public T visit(ConstantTypeCastPrimary constantTypeCastPrimary) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(ConstantTernaryExpression constantTernaryExpression) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(ConstantUnaryExpression constantUnaryExpression) {
        T result = defaultResult();
        return result;
    }

    // -------------------------------------------------------------
    // Literals & numbers
    // -------------------------------------------------------------

    @Override
    public T visit(Number number) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(TimeLiteral timeLiteral) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(StringLiteral stringLiteral) {
        T result = defaultResult();
        return result;
    }

    // -------------------------------------------------------------
    // Identifiers
    // -------------------------------------------------------------

    @Override
    public T visit(Identifier identifier) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(BlockLabel blockLabel) {
        T result = defaultResult();
        if (blockLabel.getIdentifier() != null) {
            merge(result, blockLabel.getIdentifier().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ComponentCommonItem componentCommonItem) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(ComponentItemDeclarationEmpty componentItemDeclarationEmpty) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(AssignmentExpression assignmentExpression) {
        T result = defaultResult();
        if (assignmentExpression.getOperatorAssignment() != null) {
            merge(result, assignmentExpression.getOperatorAssignment().accept(this));
        }
        return result;
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
        weAreInExpression = true;
        T result = defaultResult();
        if (binaryExpression.getLeft() != null) {
            merge(result, binaryExpression.getLeft().accept(this));
        }
        if (binaryExpression.getRight() != null) {
            merge(result, binaryExpression.getRight().accept(this));
        }
        weAreInExpression = false;
        return result;
    }

    @Override
    public T visit(CallPrimary callPrimary) {
        T result = defaultResult();
        if (callPrimary.getIdentifier() != null) {
            merge(result, callPrimary.getIdentifier().accept(this));
        }
        if (callPrimary.getArgList() != null) {
            merge(result, callPrimary.getArgList().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ConstantBinaryExpression constantBinaryExpression) {
        T result = defaultResult();
        if (constantBinaryExpression.getLeft() != null) {
            merge(result, constantBinaryExpression.getLeft().accept(this));
        }
        if (constantBinaryExpression.getRight() != null) {
            merge(result, constantBinaryExpression.getRight().accept(this));
        }
        return result;
    }

    @Override
    public T visit(HierarchicalPrimary hierarchicalPrimary) {
        weAreInExpression = true;
        T result = defaultResult();
        if (hierarchicalPrimary.getHierarchicalIdentifier() != null) {
            merge(result, hierarchicalPrimary.getHierarchicalIdentifier().accept(this));
        }
        if (hierarchicalPrimary.getSelect() != null) {
            merge(result, hierarchicalPrimary.getSelect().accept(this));
        }
        weAreInExpression = false;
        return result;
    }

    @Override
    public T visit(IfExpressionExpression ifExpressionExpression) {
        T result = defaultResult();
        if (ifExpressionExpression.getCondition() != null) {
            merge(result, ifExpressionExpression.getCondition().accept(this));
        }
        if (ifExpressionExpression.getThenExpression() != null) {
            merge(result, ifExpressionExpression.getThenExpression().accept(this));
        }
        if (ifExpressionExpression.getElseExpression() != null) {
            merge(result, ifExpressionExpression.getElseExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(IncOrDecStatement incOrDecStatement) {
        T result = defaultResult();
        if (incOrDecStatement.getIncOrDecExpression() != null) {
            merge(result, incOrDecStatement.getIncOrDecExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(MintypmaxPrimary mintypmaxPrimary) {
        T result = defaultResult();
        if (mintypmaxPrimary.getMintypmaxExpression() != null) {
            merge(result, mintypmaxPrimary.getMintypmaxExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(NonblockingAssignmentStatement nonblockingAssignmentStatement) {
        T result = defaultResult();
        if (nonblockingAssignmentStatement.getNonblockingAssignment() != null) {
            merge(result, nonblockingAssignmentStatement.getNonblockingAssignment().accept(this));
        }
        return result;
    }

    @Override
    public T visit(OperatorAssignment operatorAssignment) {
        T result = defaultResult();
        if (operatorAssignment.getVariableLvalue() != null) {
            merge(result, operatorAssignment.getVariableLvalue().accept(this));
        }
        if (operatorAssignment.getRightExpression() != null) {
            merge(result, operatorAssignment.getRightExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(OperatorAssignmentStatement operatorAssignmentStatement) {
        T result = defaultResult();
        if (operatorAssignmentStatement.getOperatorAssignment() != null) {
            merge(result, operatorAssignmentStatement.getOperatorAssignment().accept(this));
        }
        return result;
    }

    @Override
    public T visit(PostfixIncOrDecExpression postfixIncOrDecExpression) {
        T result = defaultResult();
        if (postfixIncOrDecExpression.getVariableLvalue() != null) {
            merge(result, postfixIncOrDecExpression.getVariableLvalue().accept(this));
        }
        return result;
    }

    @Override
    public T visit(PrefixIncOrDecExpression prefixIncOrDecExpression) {
        T result = defaultResult();
        if (prefixIncOrDecExpression.getVariableLvalue() != null) {
            merge(result, prefixIncOrDecExpression.getVariableLvalue().accept(this));
        }
        return result;
    }

    @Override
    public T visit(PrimaryCast primaryCast) {
        T result = defaultResult();
        if (primaryCast.getPrimary() != null) {
            merge(result, primaryCast.getPrimary().accept(this));
        }
        if (primaryCast.getExpression() != null) {
            merge(result, primaryCast.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(ProceduralContinuousSetStatement proceduralContinuousSetStatement) {
        T result = defaultResult();
        if (proceduralContinuousSetStatement.getProceduralContinuousSet() != null) {
            merge(result, proceduralContinuousSetStatement.getProceduralContinuousSet().accept(this));
        }
        return result;
    }

    @Override
    public T visit(UnaryExpression unaryExpression) {
        T result = defaultResult();
        if (unaryExpression.getExpression() != null) {
            merge(result, unaryExpression.getExpression().accept(this));
        }
        return result;
    }

    @Override
    public T visit(TypeCastPrimary typeCastPrimary) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(SimpleType simpleType) {
        T result = defaultResult();
        if (simpleType.getIdentifier() != null) {
            merge(result, simpleType.getIdentifier().accept(this));
        }
        return result;
    }

    @Override
    public T visit(IntegerType integerType) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(IntegerDataType integerDataType) {
        T result = defaultResult();
        for (PackedDimension pd : integerDataType.getPackedDimensions()) {
            merge(result, pd.accept(this));
        }
        return result;
    }

    @Override
    public T visit(NonIntegerDataType nonIntegerDataType) {
        T result = defaultResult();
        return result;
    }

    @Override
    public T visit(IdentifierDataType identifierDataType) {
        T result = defaultResult();
        if (identifierDataType.getIdentifier() != null) {
            merge(result, identifierDataType.getIdentifier().accept(this));
        }
        for (PackedDimension dim : identifierDataType.getPackedDimensions()) {
            merge(result, dim.accept(this));
        }
        return result;
    }

    @Override
    public T visit(StringDataType stringDataType) {
        T result = defaultResult();
        return result;
    }

}
