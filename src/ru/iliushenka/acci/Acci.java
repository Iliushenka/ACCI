package ru.iliushenka.acci;

import ru.iliushenka.acci.parser.Lexer;
import ru.iliushenka.acci.parser.Parser;
import ru.iliushenka.acci.parser.common.statement.Statement;
import ru.iliushenka.acci.utility.Reader;
import ru.iliushenka.acci.utility.Token;

import java.util.ArrayList;

public class Acci {

    public static void main(String[] args) {
        String path = "code.txt";
        Lexer lexer = new Lexer(Reader.read(path));
        ArrayList<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
        System.out.println();
        Parser parser = new Parser(tokens);
        ArrayList<Statement> statements = parser.parse();
        for (Statement statement : statements) {
            System.out.print(statement.getType() + " ");
            System.out.println(statement.toString());
        }
    }
}
