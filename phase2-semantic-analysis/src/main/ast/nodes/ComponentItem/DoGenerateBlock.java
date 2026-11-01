package main.ast.nodes.ComponentItem;

import java.util.ArrayList;
import java.util.List;

import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;
import main.ast.nodes.Node;
import main.visitor.IVisitor;

public class DoGenerateBlock extends GenerateBlock {

    private GenerateBlockLabel generateBlockLabel;        // optional
    private GenerateBlockName beginGenerateBlockName;     // optional
    private GenerateBlockName endGenerateBlockName;       // optional

    private List<GenerateItem> generateItems = new ArrayList<>();

    // line of 'do'
    private int startLine;

    // line of 'enddo'
    private int endLine;

    public DoGenerateBlock() {
    }

    // --------------------------------
    // Label
    // --------------------------------
    public void setGenerateBlockLabel(GenerateBlockLabel generateBlockLabel) {
        this.generateBlockLabel = generateBlockLabel;
    }

    public GenerateBlockLabel getGenerateBlockLabel() {
        return this.generateBlockLabel;
    }

    // --------------------------------
    // Begin name (after 'do')
    // --------------------------------
    public void setBeginGenerateBlockName(GenerateBlockName beginGenerateBlockName) {
        this.beginGenerateBlockName = beginGenerateBlockName;
    }

    public GenerateBlockName getBeginGenerateBlockName() {
        return this.beginGenerateBlockName;
    }

    // --------------------------------
    // End name (after 'enddo')
    // --------------------------------
    public void setEndGenerateBlockName(GenerateBlockName endGenerateBlockName) {
        this.endGenerateBlockName = endGenerateBlockName;
    }

    public GenerateBlockName getEndGenerateBlockName() {
        return this.endGenerateBlockName;
    }

    // --------------------------------
    // Items
    // --------------------------------
    public void addGenerateItem(GenerateItem item) {
        if (item != null) {
            this.generateItems.add(item);
        }
    }

    public List<GenerateItem> getGenerateItems() {
        return this.generateItems;
    }

    public void setGenerateItems(List<GenerateItem> generateItems) {
        this.generateItems = generateItems;
    }

    // --------------------------------
    // Start line (line of 'do')
    // --------------------------------
    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getStartLine() {
        return this.startLine;
    }

    // --------------------------------
    // End line (line of 'enddo')
    // --------------------------------
    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public int getEndLine() {
        return this.endLine;
    }

    // --------------------------------
    // Visitor
    // --------------------------------
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
