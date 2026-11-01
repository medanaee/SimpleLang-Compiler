package main.ast.nodes.ComponentItem;

import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;
import main.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class GenerateRegion extends ComponentItem {


    private int startLine;


    private int endLine;


    private List<GenerateItem> generateItems = new ArrayList<>();

    public GenerateRegion() {

    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public List<GenerateItem> getGenerateItems() {
        return generateItems;
    }

    public void setGenerateItems(List<GenerateItem> generateItems) {
        this.generateItems = generateItems;
    }

    public void addGenerateItem(GenerateItem item) {
        this.generateItems.add(item);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
