import main.ast.nodes.SourceText;
import main.grammar.SimpleLangLexer;
import main.grammar.SimpleLangParser;
//import main.visitor.TestVisitor;
import main.scheduler.ScheduledEvent;
import main.visitor.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class SimpleLang {
    public static void main(String[] args) throws IOException {
        CharStream reader = CharStreams.fromFileName(args[0]);
        SimpleLangLexer simpleLangLexer = new SimpleLangLexer(reader);
        CommonTokenStream tokens = new CommonTokenStream(simpleLangLexer);
        SimpleLangParser flParser = new SimpleLangParser(tokens);
        SourceText sourceText = flParser.source_text().SourceTextRet;

        NameAnalyzerVisitor nameAnalyzerVisitor = new NameAnalyzerVisitor();
        nameAnalyzerVisitor.visit(sourceText);
        TypeCheckerVisitor typeCheckerVisitor = new TypeCheckerVisitor();
        typeCheckerVisitor.visit(sourceText);
        SchedulerVisitor schedulerVisitor = new SchedulerVisitor();
        schedulerVisitor.visit(sourceText);

        //output test
        schedulerVisitor.getEventQueue().sort(Comparator.comparingLong(ScheduledEvent::getTime));

        // System.out.println("--- Scheduled Events Order ---");
        // for (ScheduledEvent event : schedulerVisitor.getEventQueue()) {
        //     System.out.println("Time: " + event.getTime() + " | Statement: " + event.getStatement());
        // }

        CodeGenerator codeGen = new CodeGenerator((ArrayList<ScheduledEvent>)schedulerVisitor.getEventQueue());
        codeGen.visit(sourceText);

        runJasminCode();
    }

    private static void runJasminCode() {
        try {
            File dir = new File("./codeGenOutput");
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
            String[] jasminCmd;

            if (isWindows) {
                jasminCmd = new String[]{"cmd", "/c", "java -jar jasmin.jar *.j"};
            } else {
                jasminCmd = new String[]{"sh", "-c", "java -jar jasmin.jar *.j"};
            }
            Process jasminProcess = Runtime.getRuntime().exec(jasminCmd, null, dir);
            printResults(jasminProcess.getInputStream());
            printResults(jasminProcess.getErrorStream());

            int exitCode = jasminProcess.waitFor();
            if (exitCode != 0) {
                System.err.println("Jasmin Compilation Failed! Exit code: " + exitCode);
                return;
            }
            Process runProcess = Runtime.getRuntime().exec(
                    new String[]{"java", "-cp", ".", "Main"},
                    null,
                    dir
            );
            printResults(runProcess.getInputStream());
            printResults(runProcess.getErrorStream());
            runProcess.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void printResults(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}