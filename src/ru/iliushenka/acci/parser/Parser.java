package ru.iliushenka.acci.parser;

import ru.iliushenka.acci.parser.common.NodeValue;
import ru.iliushenka.acci.parser.common.Value;
import ru.iliushenka.acci.parser.common.ValueParameter;
import ru.iliushenka.acci.parser.common.ValueParameterArray;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ExpressionHandler;
import ru.iliushenka.acci.parser.common.expression.actions.SendMessage;
import ru.iliushenka.acci.parser.common.statement.EventHandler;
import ru.iliushenka.acci.parser.common.statement.EventStatement;
import ru.iliushenka.acci.parser.common.statement.Statement;
import ru.iliushenka.acci.utility.Node;
import ru.iliushenka.acci.utility.Token;
import ru.iliushenka.acci.utility.TokenType;

import java.util.ArrayList;

public class Parser {

    private final ArrayList<Token> tokens;
    private final int size;
    private Token curToken;
    private int index = 0;

    private final ArrayList<Statement> statements = new ArrayList<>();

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.size = this.tokens.size();

        this.curToken = this.get();
    }

    public ArrayList<Statement> parse() {
        while (!match(TokenType.EOF)) {
            Statement statement = this.statement();
            this.statements.add(statement);
        }
        return this.statements;
    }

    private Statement statement() {
        // Здесь проверяем запись на событие
        if (match(TokenType.VARIABLE) && curToken.getValue().equals("event")) {
            eat(TokenType.VARIABLE);
            eat(TokenType.OPEN_PARENT);

            String eventName = curToken.getValue();
            eat(TokenType.VARIABLE);

            EventStatement event = EventHandler.getEvent(eventName);

            eat(TokenType.CLOSE_PARENT);

            eat(TokenType.OPEN_BRACKET);
            event.setNode(this.expression());
            eat(TokenType.CLOSE_BRACKET);
            return event;
        }
        // Здесь проверяем запись на функцию
        else if (match(TokenType.VARIABLE) && curToken.getValue().equals("function")) {
            eat(TokenType.VARIABLE);
            // ...
        }
        // Здесь проверяем запись на цикл
        else if (match(TokenType.VARIABLE) && curToken.getValue().equals("cycle")) {
            eat(TokenType.VARIABLE);
            // ...
        }
        System.out.println("Такого statement нет!");
        System.exit(-1);
        return null;
    }

    // Тут обрабаываются записи в { ... } событии, функции или цикле
    private Node expression() {
        if (get(0).getType() == TokenType.VARIABLE &&
                get(1).getType() == TokenType.DOT &&
                get(2).getType() == TokenType.VARIABLE) {
            String blockName = get().getValue();
            eat(TokenType.VARIABLE);
            eat(TokenType.DOT);
            String actionName = get().getValue();
            eat(TokenType.VARIABLE);
            eat(TokenType.OPEN_PARENT);

            ArrayList<NodeValue> parameters = parameter();

            eat(TokenType.CLOSE_PARENT);
            eat(TokenType.SEMICOLON);
            Node retNode = new Node(ExpressionHandler.getAction(blockName, actionName));
            retNode.setValues(parameters);
            retNode.setOther(this.expression());
            return retNode;
        }

        return null;
    }

    private ArrayList<NodeValue> parameter() {
        ArrayList<NodeValue> values = new ArrayList<>();
        while (!(match(TokenType.EOF) || match(TokenType.CLOSE_PARENT))) {
            if (match(TokenType.VARIABLE) && get(1).getType() == TokenType.ASSIGN) {
                String name = curToken.getValue();
                eat(TokenType.VARIABLE);
                eat(TokenType.ASSIGN);
                if (match(TokenType.OPEN_SQUARE_BRACKET)) {
                    eat(TokenType.OPEN_SQUARE_BRACKET);
                    ArrayList<NodeValue> array = new ArrayList<>();
                    while (!(match(TokenType.EOF) || match(TokenType.CLOSE_SQUARE_BRACKET))) {
                        array.add(additive());
                        if (!match(TokenType.CLOSE_SQUARE_BRACKET)) {
                            eat(TokenType.COMMA);
                        }
                    }
                    values.add(new ValueParameterArray(name, array));
                    eat(TokenType.CLOSE_SQUARE_BRACKET);
                } else {
                    values.add(new ValueParameter(name, additive()));
                }
            } else {
                values.add(additive());
            }
            if (!match(TokenType.CLOSE_PARENT)) {
                eat(TokenType.COMMA);
            }
        }
        return values;
    }

    // + и -
    private NodeValue additive() {
        return multiply();
    }

    // * и /
    private NodeValue multiply() {
        return primary();
    }

    // Получение значения
    private NodeValue primary() {
        Token token = this.curToken;
        switch (token.getType()) {
            case TokenType.VARIABLE:
                next();
                return new Value("VARIABLE", token.getValue());
            case TokenType.NUMBER:
                next();
                return new Value("NUMBER", token.getValue());
            case TokenType.STRING:
                next();
                return new Value("STRING", token.getValue());
            case TokenType.STRING_COMPONENT:
                next();
                return new Value("STRING_COMPONENT", token.getValue());
            default:
                System.out.println("Ошибка в парсинге primary такого типа данных нет " + curToken.getType().toString());
                System.exit(-1);
        }
        return null;
    }

    private Token get() {
        if (this.index >= this.size) {
            return new Token(TokenType.EOF, "");
        }
        return this.tokens.get(this.index);
    }

    private Token get(int relative_index) {
        int index = this.index + relative_index;
        if (index >= this.size) {
            return new Token(TokenType.EOF, "");
        }
        return this.tokens.get(index);
    }

    private void next() {
        this.index++;
        this.curToken = this.get();
    }

    private boolean match(TokenType type) {
        return this.curToken.getType() == type;
    }

    private void eat(TokenType type) {
        if (this.match(type)) {
            this.next();
        } else {
            System.out.println("Ожидался другой тип токена" + " имеем " +
                    this.curToken.getType().toString() + " ожидали " + type.toString() + " " + curToken.getValue());
            System.exit(-1);
        }
    }
}
