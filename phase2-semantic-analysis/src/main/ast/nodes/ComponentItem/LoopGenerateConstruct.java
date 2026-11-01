package main.ast.nodes.ComponentItem;

import main.ast.nodes.ComponentItem.GenerateItem.GenerateItem;
import main.visitor.IVisitor;

/**
 * AST node for a continuous set statement:
 *     set <list_of_net_assignments> ;
 *
 * Follows the same conventions as the other AST nodes in your project.
 */
public class LoopGenerateConstruct extends GenerateItem {

    private GenvarInitialization genvarInitialization;
    private GenvarExpression genvarExpression;
    private GenvarIteration genvarIteration;
    private GenerateBlock generateBlock;

    public LoopGenerateConstruct(GenvarInitialization genvarInitialization, GenvarExpression genvarExpression, GenvarIteration genvarIteration, GenerateBlock generateBlock,int line) {
        this.genvarInitialization = genvarInitialization;
        this.genvarExpression = genvarExpression;
        this.genvarIteration = genvarIteration;
        this.generateBlock = generateBlock;
        this.setLine(line);
    }

    // Constructor
    public LoopGenerateConstruct() {
        // Initialize fields if necessary
    }

    // Getters and Setters
    public GenvarInitialization getGenvarInitialization() {
        return genvarInitialization;
    }

    public void setGenvarInitialization(GenvarInitialization genvarInitialization) {
        this.genvarInitialization = genvarInitialization;
    }

    public GenvarExpression getGenvarExpression() {
        return genvarExpression;
    }

    public void setGenvarExpression(GenvarExpression genvarExpression) {
        this.genvarExpression = genvarExpression;
    }

    public GenvarIteration getGenvarIteration() {
        return genvarIteration;
    }

    public void setGenvarIteration(GenvarIteration genvarIteration) {
        this.genvarIteration = genvarIteration;
    }

    public GenerateBlock getGenerateBlock() {
        return generateBlock;
    }

    public void setGenerateBlock(GenerateBlock generateBlock) {
        this.generateBlock = generateBlock;
    }

    // Accept method for Visitor pattern
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
