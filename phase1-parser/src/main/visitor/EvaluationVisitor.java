package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.ComponentDeclaration.*;
import main.ast.nodes.ComponentItem.*;
import main.ast.nodes.ComponentItem.LoopStatement.*;
import main.ast.nodes.Headers.ComponentHeader;
import main.ast.nodes.Headers.ConfigurationHeader;
import main.ast.nodes.ListOfPortDeclarations.ListOfPortDeclarations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EvaluationVisitor extends Visitor<Void> {

    private static class OutputResult implements Comparable<OutputResult> {
        int line;
        String text;

        public OutputResult(int line, String text) {
            this.line = line;
            this.text = text;
        }

        @Override
        public int compareTo(OutputResult o) {
            return Integer.compare(this.line, o.line);
        }

        @Override
        public String toString() {
            return text;
        }
    }

    private List<OutputResult> outputs = new ArrayList<>();

    public void printSortedOutputs() {
        Collections.sort(outputs);
        for (OutputResult out : outputs) {
            System.out.println(out);
        }
    }

    @Override
    public Void visit(SourceText sourceText) {
        for (ComponentDeclaration cd : sourceText.getComponentDeclarations()) {
            cd.accept(this);
        }
        return null;
    }

    // Checking IdentifierComponent (componenttype with ID)
    @Override
    public Void visit(IdentifierComponent identifierComponent) {
        int line = identifierComponent.getStartLine();
        String name = (identifierComponent.getIdentifier() != null) ? identifierComponent.getIdentifier().getName()
                : "Unknown";

        int numPorts = 0;
        

        List<ComponentItem> items = identifierComponent.getComponentItemList();
        int numItems = (items != null) ? items.size() : 0;

        int numRepeats = 0;
        if (items != null) {
            for (ComponentItem item : items) {
                if (item instanceof RepeatLoop) {
                    numRepeats++;
                }
                // Visit children
                item.accept(this);
            }
        }

        String output = "Line: " + line + " | Name: " + name +
                " | Number of ports: " + numPorts +
                " | Number of component items: " + numItems +
                " | Number of repeat loops: " + numRepeats;
        outputs.add(new OutputResult(line, output));
        return null;
    }

    // Checking ItemHeaderDeclaration (componenttype with header)
    @Override
    public Void visit(ItemHeaderDeclaration itemHeaderDeclaration) {
        ComponentHeader header = itemHeaderDeclaration.getComponentHeader();
        int line = itemHeaderDeclaration.getStartLine();
        String name = (header != null && header.getIdentifier() != null) ? header.getIdentifier().getName() : "Unknown";

        int numPorts = 0;
        if (header != null && header.getListOfPortDeclarations() != null) {
            numPorts = header.getListOfPortDeclarations().getPortDeclList().size();
        }

        List<ComponentItem> items = itemHeaderDeclaration.getComponentItemList();
        int numItems = (items != null) ? items.size() : 0;

        int numRepeats = 0;
        if (items != null) {
            for (ComponentItem item : items) {
                if (item instanceof RepeatLoop) {
                    numRepeats++;
                }
                item.accept(this);
            }
        }

        String output = "Line: " + line + " | Name: " + name +
                " | Number of ports: " + numPorts +
                " | Number of component items: " + numItems +
                " | Number of repeat loops: " + numRepeats;
        outputs.add(new OutputResult(line, output));
        return null;
    }

    // Checking ConfigDeclaration (configuration ID)
    @Override
    public Void visit(ConfigDeclaration configDeclaration) {
        int line = configDeclaration.getStartLine();
        String name = (configDeclaration.getIdentifier() != null) ? configDeclaration.getIdentifier().getName()
                : "Unknown";

        int numPorts = 0; 

        List<ComponentItem> items = configDeclaration.getComponentItemList();
        int numItems = (items != null) ? items.size() : 0;

        int numRepeats = 0;
        if (items != null) {
            for (ComponentItem item : items) {
                if (item instanceof RepeatLoop) {
                    numRepeats++;
                }
                item.accept(this);
            }
        }

        String output = "Line: " + line + " | Name: " + name +
                " | Number of ports: " + numPorts +
                " | Number of component items: " + numItems +
                " | Number of repeat loops: " + numRepeats;
        outputs.add(new OutputResult(line, output));
        return null;
    }

    // Checking ConfigHeaderDeclaration (configuration with header)
    @Override
    public Void visit(ConfigHeaderDeclaration configHeaderDeclaration) {
        ConfigurationHeader header = configHeaderDeclaration.getConfigurationHeader();
        int line = configHeaderDeclaration.getStartLine();
        String name = (header != null && header.getIdentifier() != null) ? header.getIdentifier().getName() : "Unknown";

        int numPorts = 0;
        if (header != null && header.getListOfPortDeclarations() != null) {
            numPorts = header.getListOfPortDeclarations().getPortDeclList().size();
        }

        List<ComponentItem> items = configHeaderDeclaration.getComponentItemList();
        int numItems = (items != null) ? items.size() : 0;

        int numRepeats = 0;
        if (items != null) {
            for (ComponentItem item : items) {
                if (item instanceof RepeatLoop) {
                    numRepeats++;
                }
                item.accept(this);
            }
        }

        String output = "Line: " + line + " | Name: " + name +
                " | Number of ports: " + numPorts +
                " | Number of component items: " + numItems +
                " | Number of repeat loops: " + numRepeats;
        outputs.add(new OutputResult(line, output));
        return null;
    }

    @Override
    public Void visit(ComponentProgramInterfaceInstantiation instantiation) {
        int line = -1;
        if (instantiation.getIdentifier() != null)
            line = instantiation.getIdentifier().getLine();
        // Or check if instantiation itself has line set. Node has line.
        if (line == 0 || line == -1)
            line = instantiation.getLine();

        String type = (instantiation.getIdentifier() != null) ? instantiation.getIdentifier().getName() : "Unknown";

        if (instantiation.getInstances() != null) {
            for (HierarchicalInstance instance : instantiation.getInstances()) {
                String instanceName = (instance.getInstanceName() != null) ? instance.getInstanceName().getName()
                        : "Unknown";
                int numPorts = (instance.getPortConnections() != null) ? instance.getPortConnections().size() : 0;

                String output = "Line: " + line + " | ComponentType: " + type +
                        " | Instances: " + instanceName +
                        " | Number of ports: " + numPorts;
                outputs.add(new OutputResult(line, output));
            }
        }
        return null;
    }

    @Override
    public Void visit(ForLoopStatement forLoopStatement) {
        int line = forLoopStatement.getLine();
        String name = (forLoopStatement.getLoopIdentifier() != null) ? forLoopStatement.getLoopIdentifier().getName()
                : "Unknown";

        int numStatements = 0;
        if (forLoopStatement.getInstantiations() != null) {
            numStatements = forLoopStatement.getInstantiations().size();
        }

        String output = "Line: " + line + " | Name: " + name +
                " | Number of statements : " + numStatements;
        outputs.add(new OutputResult(line, output));

        if (forLoopStatement.getInstantiations() != null) {
            for (ComponentProgramInterfaceInstantiation inst : forLoopStatement.getInstantiations()) {
                inst.accept(this);
            }
        }
        return null;
    }

    // Helper to traverse other containers
    @Override
    public Void visit(ExpandLoop expandLoop) {
        if (expandLoop.getForLoopStatements() != null) {
            for (ForLoopStatement fl : expandLoop.getForLoopStatements()) {
                fl.accept(this);
            }
        }
        return null;
    }
}
