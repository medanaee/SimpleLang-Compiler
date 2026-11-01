package main.ast.nodes.ComponentItem;

import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;
import main.ast.nodes.Identifier;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * AST node for a continuous set statement:
 *     set <list_of_net_assignments> ;
 *
 * Follows the same conventions as the other AST nodes in your project.
 */
public class ComponentProgramInterfaceInstantiation extends GenerateItem {

    private Identifier identifier;
    // optional: #(...) part
    private ParameterValueAssignment parameterValueAssignment;
    // one or more hierarchical_instance
    private List<HierarchicalInstance> hierarchicalInstances;

    public ComponentProgramInterfaceInstantiation() {
        this.hierarchicalInstances = new ArrayList<>();
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public void setParameterValueAssignment(ParameterValueAssignment parameterValueAssignment) {
        this.parameterValueAssignment = parameterValueAssignment;
    }

    public ParameterValueAssignment getParameterValueAssignment() {
        return this.parameterValueAssignment;
    }

    public void addHierarchicalInstance(HierarchicalInstance instance) {
        this.hierarchicalInstances.add(instance);
    }

    public void setHierarchicalInstances(List<HierarchicalInstance> hierarchicalInstances) {
        this.hierarchicalInstances = hierarchicalInstances;
    }

    public String getName()
    {
        return identifier.getName();
    }

    public int getNumOfPorts()
    {
        return this.hierarchicalInstances.get(0).getNumOfPorts();
    }

    public String getInsName()
    {
        return this.hierarchicalInstances.get(0).getName();
    }

    public List<HierarchicalInstance> getHierarchicalInstances() {
        return this.hierarchicalInstances;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
