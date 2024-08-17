package parser;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGenerator;
import MiniJava.codeGenerator.CodeGeneratorFacade;
import MiniJava.scanner.lexicalAnalyzer;
import MiniJava.scanner.token.Token;
import MiniJava.errorHandler.ErrorHandler;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserFacade {
    private static ParserFacade instance = null;
    private final Parser parser;
    private ParseTable parseTable;
    private final lexicalAnalyzer lexicalAnalyzer;
    private final CodeGeneratorFacade codeGeneratorFacade;

    public ParserFacade(java.util.Scanner scanner) {
        this.parser = new Parser();
        try {
            this.parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.lexicalAnalyzer = new lexicalAnalyzer(scanner);
        this.codeGeneratorFacade = new CodeGeneratorFacade();
    }

    public static ParserFacade getInstance(java.util.Scanner scanner) {
        if (instance == null) {
            instance = new ParserFacade(scanner);
        }
        return instance;
    }

    public void parse() {
        parser.initializeParser();
        Token lookAhead = lexicalAnalyzer.getNextToken();
        boolean finish = false;
        Action currentAction;
        while (!finish) {
            try {
                Log.print(lookAhead.toString() + "\t" + parser.getParsStack().peek());
                currentAction = parseTable.getActionTable(parser.getParsStack().peek(), lookAhead);
                switch (currentAction.action) {
                    case shift:
                        parser.shiftAction(currentAction);
                        lookAhead = lexicalAnalyzer.getNextToken();
                        break;
                    case reduce:
                        parser.reduceAction(currentAction, lookAhead, this.parseTable, this.codeGeneratorFacade);
                        break;
                    case accept:
                        finish = true;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!ErrorHandler.hasError) {
            codeGeneratorFacade.printMemory();
        }
    }
}