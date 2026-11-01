package main.ast.nodes.ComponentItem;

import main.ast.nodes.HierarchicalInstance;
import main.ast.nodes.Identifier;
import main.ast.nodes.ParamAssignment;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ComponentProgramInterfaceInstantiation extends ComponentCommonItem {
    private Identifier identifier; // The type name
    private List<ParamAssignment> parameterAssignments; // #(...)
    private List<HierarchicalInstance> instances;

    public ComponentProgramInterfaceInstantiation() {
        this.parameterAssignments = new ArrayList<>();
        this.instances = new ArrayList<>();
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setParameterAssignments(List<ParamAssignment> parameterAssignments) {
        this.parameterAssignments = parameterAssignments;
    }

    public List<ParamAssignment> getParameterAssignments() {
        return parameterAssignments;
    }

    public void addInstance(HierarchicalInstance instance) {
        this.instances.add(instance);
    }

    public void setInstances(List<HierarchicalInstance> instances) {
        this.instances = instances;
    }

    public List<HierarchicalInstance> getInstances() {
        return instances;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this); // Need to add to Visitor
    }
}
