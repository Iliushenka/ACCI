package ru.iliushenka.acci.utility;

import ru.iliushenka.acci.parser.Lexer;
import ru.iliushenka.acci.parser.Parser;
import ru.iliushenka.acci.parser.common.statement.EventStatement;
import ru.iliushenka.acci.parser.common.statement.Statement;

import java.util.ArrayList;

public class Manager {

    public static String filename;
    public static int index;

    /**
     * Функция, которая начинает компиляцию файла
     * <p>
     * Указывать название файла без .acci
     *
     *
     * @author iliushenka
     * @param name Название файла
     */
    public static void setup(String name) {
        filename = name;
        index = 0;

        String path = name + ".acci";
        Lexer lexer = new Lexer(Reader.read(path));
        ArrayList<Token> tokens = lexer.tokenize();
        Parser parser = new Parser(tokens);
        ArrayList<Statement> statements = parser.parse();
        for (Statement statement : statements) {
            if (statement instanceof EventStatement) {
                ((EventStatement) statement).execute();
            }
        }
    }
}
