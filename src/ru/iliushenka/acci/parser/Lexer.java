package ru.iliushenka.acci.parser;

import ru.iliushenka.acci.utility.Token;
import ru.iliushenka.acci.utility.TokenType;

import java.util.ArrayList;

public class Lexer {

    private final String context;
    private final int length;

    private int position;
    private char temp;

    private final ArrayList<Token> tokens = new ArrayList<>();

    public Lexer(String context) {
        this.context = context;
        this.length = this.context.length();

        this.temp = peek(0);
    }

    public ArrayList<Token> tokenize() {
        while (this.position < this.length) {
            if (temp == ' ' || temp == '\t' || temp == '\r' || temp == '\n') {
                next();
            } else if (Character.isLetter(temp) || temp == '_') {
                tokenizeVariable();
            } else if (Character.isDigit(temp) || temp == '.') {
                tokenizeNumber();
            } else if (this.peek(0) == '/' && this.peek(1) == '*') {
                tokenizeBigComment();
            } else if (this.peek(0) == '/' && this.peek(1) == '/') {
                tokenizeComment();
            } else if (temp == '\'') {
                tokenizeStringComponent();
            } else if (temp == '"') {
                tokenizeString();
            } else {
                tokenizeSymbol();
            }
        }
        return this.tokens;
    }

    private void tokenizeStringComponent() {
        next();
        boolean skip = false;
        StringBuilder tokenValue = new StringBuilder();
        while ((skip || temp != '\'') && temp != '\0') {
            if (!skip && temp == '\\') {
                skip = true;
            } else {
                skip = false;
                tokenValue.append(temp);
            }
            next();
        }
        if (temp == '\'') {
            next();
        } else {
            System.out.println("Ошибка в парсинге текстовой компоненты");
        }
        addToken(TokenType.STRING_COMPONENT, tokenValue.toString());
    }

    private void tokenizeString() {
        next();
        boolean skip = false;
        StringBuilder tokenValue = new StringBuilder();
        while ((skip || temp != '"') && temp != '\0') {
            if (!skip && temp == '\\') {
                skip = true;
            } else {
                skip = false;
                tokenValue.append(temp);
            }
            next();
        }
        if (temp == '"') {
            next();
        } else {
            System.out.println("Ошибка в парсинге текстовой компоненты");
        }
        addToken(TokenType.STRING, tokenValue.toString());
    }

    private void tokenizeComment() {
        next(2);
        while (temp != '\n' && temp != '\0') {
            System.out.print(peek(0));
            next();
        }
    }

    private void tokenizeBigComment() {
        next(2);
        while (this.peek(0) != '*' && this.peek(1) != '/' && temp != '\0') {
            next();
        }
        next(2);
    }

    private void tokenizeSymbol() {
        TokenType tokenType = TokenType.NULL;
        switch (temp) {
            case '.':
                tokenType = TokenType.DOT;
                break;
            case ',':
                tokenType = TokenType.COMMA;
                break;
            case '(':
                tokenType = TokenType.OPEN_PARENT;
                break;
            case ')':
                tokenType = TokenType.CLOSE_PARENT;
                break;
            case '{':
                tokenType = TokenType.OPEN_BRACKET;
                break;
            case '}':
                tokenType = TokenType.CLOSE_BRACKET;
                break;
            case '+':
                if (peek(1) == '=') {
                    tokenType = TokenType.PLUS_ASSIGN;
                    next();
                } else {
                    tokenType = TokenType.PLUS;
                }
                break;
            case '-':
                if (peek(1) == '=') {
                    tokenType = TokenType.MINUS_ASSIGN;
                    next();
                } else {
                    tokenType = TokenType.MINUS;
                }
                break;
            case '*':
                tokenType = TokenType.MULTIPLY;
                break;
            case '/':
                tokenType = TokenType.DIVISION;
                break;
            case ';':
                tokenType = TokenType.SEMICOLON;
                break;
            case ':':
                tokenType = TokenType.COLON;
                break;
            case '[':
                tokenType = TokenType.OPEN_SQUARE_BRACKET;
                break;
            case ']':
                tokenType = TokenType.CLOSE_SQUARE_BRACKET;
                break;
            case '=':
                tokenType = TokenType.ASSIGN;
                break;
        }
        addToken(tokenType);
        next();
    }

    private void tokenizeNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        while (Character.isDigit(temp) || temp == '.') {
            stringBuilder.append(temp);
            next();
        }
        String tokenValue = stringBuilder.toString();
        try {
            Float.parseFloat(tokenValue);
            addToken(TokenType.NUMBER, tokenValue);
        } catch (NumberFormatException ignore) {
            if (tokenValue.equals(".")) {
                next(-1);
                tokenizeSymbol();
            } else {
                System.out.println("Ошибка в парсинге из текста в число");
                System.exit(-1);
            }
        }
    }

    private void tokenizeVariable() {
        StringBuilder tokenValue = new StringBuilder();
        while (Character.isLetterOrDigit(temp) || temp == '_') {
            tokenValue.append(temp);
            next();
        }
        addToken(TokenType.VARIABLE, tokenValue.toString());
    }

    private char peek(int relative_position) {
        int position = this.position + relative_position;
        if (position >= this.length) {
            return '\0';
        }
        return context.charAt(position);
    }

    private void next() {
        nextLine();

        position++;
        temp = peek(0);
    }

    private void next(int relative_position) {
        nextLine();

        position += relative_position;
        temp = peek(0);
    }

    private void nextLine() {
        if (temp == '\r' || temp == '\n') {
            int i = 0;
        }
    }

    private void addToken(TokenType type) {
        tokens.add(new Token(type, ""));
    }

    private void addToken(TokenType type, String value) {
        tokens.add(new Token(type, value));
    }
}
