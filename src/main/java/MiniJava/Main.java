package MiniJava;
import MiniJava.parser.ParserFacade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // start parsing
            ParserFacade.getInstance(new Scanner(new File("src/main/resources/code"))).parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
