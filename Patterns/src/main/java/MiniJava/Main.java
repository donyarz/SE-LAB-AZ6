package MiniJava;
import parser.ParserFacade;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.parser.Parser;


public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            ParserFacade.getInstance(new Scanner(new File("src/main/resources/code"))).parse();
        } catch (FileNotFoundException e) {
            ErrorHandler.printError(e.getMessage());
        }
    }
}
