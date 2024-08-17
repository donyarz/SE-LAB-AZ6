package MiniJava.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGenerator;

import MiniJava.scanner.token.Token;

public class Parser {
    private ArrayList<Rule> rules;
    private Stack<Integer> parsStack;



    public void initializeParser() {
        parsStack = new Stack<Integer>();
        parsStack.push(0);
        rules = new ArrayList<Rule>();
        loadRules();
    }

    public void shiftAction(Action currentAction) {
        parsStack.push(currentAction.number);
    }

    public void reduceAction(Action currentAction, Token lookAhead, ParseTable parseTable, CodeGenerator cg) {
        Rule rule = rules.get(currentAction.number);
        for (int i = 0; i < rule.RHS.size(); i++) {
            parsStack.pop();
        }
        parsStack.push(parseTable.getGotoTable(parsStack.peek(), rule.LHS));
        try {
            cg.semanticFunction(rule.semanticAction, lookAhead);
        } catch (Exception e) {
            Log.print("Code Generator Error");
        }
    }

    private void loadRules() {
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stack<Integer> getParsStack() {
        return parsStack;
    }
}
