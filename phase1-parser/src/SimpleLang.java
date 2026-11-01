import main.ast.nodes.SourceText;
import main.grammar.SimpleLangLexer;
import main.grammar.SimpleLangParser;
//import main.visitor.TestVisitor;
import main.visitor.TestVisitor;
import main.visitor.EvaluationVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class SimpleLang {
    public static void main(String[] args) throws IOException {
        CharStream reader = CharStreams.fromFileName(args[0]);
        SimpleLangLexer simpleLangLexer = new SimpleLangLexer(reader);
        CommonTokenStream tokens = new CommonTokenStream(simpleLangLexer);
        SimpleLangParser flParser = new SimpleLangParser(tokens);
        SourceText sourceText = flParser.source_text().SourceTextRet;
        System.out.println();

        EvaluationVisitor evaluationVisitor = new EvaluationVisitor();
        evaluationVisitor.visit(sourceText);
        evaluationVisitor.printSortedOutputs();
        System.out.println();
    }
}