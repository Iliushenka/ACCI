package ru.iliushenka.acci.parser;

import ru.iliushenka.acci.parser.common.*;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ExpressionHandler;
import ru.iliushenka.acci.parser.common.expression.NextBlockAction;
import ru.iliushenka.acci.parser.common.expression.NullAction;
import ru.iliushenka.acci.parser.common.expression.actions.SendMessage;
import ru.iliushenka.acci.parser.common.expression.ifs.ifHandler;
import ru.iliushenka.acci.parser.common.expression.variables.*;
import ru.iliushenka.acci.parser.common.statement.EventHandler;
import ru.iliushenka.acci.parser.common.statement.EventStatement;
import ru.iliushenka.acci.parser.common.statement.Statement;
import ru.iliushenka.acci.utility.Manager;
import ru.iliushenka.acci.utility.Node;
import ru.iliushenka.acci.utility.Token;
import ru.iliushenka.acci.utility.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author iliushenka
 */
public class Parser {

    private final ArrayList<Token> tokens;
    private final int size;
    private Token curToken;
    private int index = 0;

    private final ArrayList<Statement> statements = new ArrayList<>();

    /**
     * @author iliushenka
     * @param tokens Токены
     */
    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.size = this.tokens.size();

        this.curToken = this.get();
    }

    /**
     * Выполняет парсинг
     * @return Вернет список Statement
     */
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

            String selected = null;
            boolean not = false;
            if (match(TokenType.OPEN_BRACE)) {
                eat(TokenType.OPEN_BRACE);
                if (match(TokenType.VARIABLE) && get().getValue().equals("not")) {
                    not = true;
                    eat(TokenType.VARIABLE);
                } else if (match(TokenType.VARIABLE)) {
                    selected = get().getValue();
                    eat(TokenType.VARIABLE);
                } else {
                    System.out.println("Вы забыли указать во внутр. выборке кого выбрать");
                    System.exit(-1);
                }
                eat(TokenType.CLOSE_BRACE);
            }
            if (match(TokenType.OPEN_BRACE)) {
                eat(TokenType.OPEN_BRACE);
                if (match(TokenType.VARIABLE) && get().getValue().equals("not")) {
                    not = true;
                    eat(TokenType.VARIABLE);
                } else if (match(TokenType.VARIABLE)) {
                    selected = get().getValue();
                    eat(TokenType.VARIABLE);
                } else {
                    System.out.println("Вы забыли указать во внутр. выборке кого выбрать");
                    System.exit(-1);
                }
                eat(TokenType.CLOSE_BRACE);
            }

            eat(TokenType.OPEN_PARENT);

            ArrayList<NodeValue> parameters = parameter();

            eat(TokenType.CLOSE_PARENT);
            eat(TokenType.SEMICOLON);
            Action action = ExpressionHandler.getAction(blockName, actionName);
            Node retNode = new Node(action);
            if (action.isNot && not) {
                action.not = true;
            } else if (not) {
                System.out.println("Данному действию нельзя присвоить инверсию");
                System.exit(-1);
            }
            if (action.isSelected && selected != null) {
                if (MiniSelector.checkSelected(selected)) {
                    action.selected = selected;
                } else {
                    System.out.println("Такой внутр. выборки не существует");
                    System.exit(-1);
                }
            } else if (selected != null){
                System.out.println("Данному действию нельзя установить внутр. выборку");
                System.exit(-1);
            }
            retNode.setValues(parameters);
            retNode.setOther(this.expression());
            return retNode;
        } else if (get(0).getType() == TokenType.VARIABLE &&
                    get(1).getType() == TokenType.ASSIGN) {
            String var_name = get().getValue();
            next();
            next();
            ArrayList<NodeValue> input = new ArrayList<>(List.of(new Value("VARIABLE", var_name),
                    new ValueArray(new ArrayList<>(List.of(this.additive()))
            )));
            Node retNode = new Node(new Assign(), input);
            eat(TokenType.SEMICOLON);
            retNode.setOther(this.expression());
            return retNode;
        } else if (get(0).getType() == TokenType.VARIABLE &&
                    get(0).getValue().equals("if")) {
            eat(TokenType.VARIABLE);
            eat(TokenType.OPEN_PARENT);

            boolean not = false;
            // Получаем обработку НЕ
            if (get().getType() == TokenType.VARIABLE && get().getValue().equals("not")) {
                eat(TokenType.VARIABLE);
                not = true;
            }

            String parent = get().getValue();
            eat(TokenType.VARIABLE);
            eat(TokenType.DOT);
            String name = get().getValue();
            eat(TokenType.VARIABLE);

            String selected = null;
            if (match(TokenType.OPEN_BRACE)) {
                eat(TokenType.OPEN_BRACE);
                if (match(TokenType.VARIABLE)) {
                    selected = get().getValue();
                    eat(TokenType.VARIABLE);
                } else {
                    System.out.println("Вы забыли указать во внутр. выборке кого выбрать");
                    System.exit(-1);
                }
                eat(TokenType.CLOSE_BRACE);
            }
            eat(TokenType.OPEN_PARENT);

            ArrayList<NodeValue> parameters = parameter();

            eat(TokenType.CLOSE_PARENT);
            eat(TokenType.CLOSE_PARENT);

            Action action = ifHandler.getIf(parent, name);
            Node retNode = new Node(action);
            retNode.setValues(parameters);
            if (action.isNot && not) {
                action.not = not;
            }
            if (action.isSelected && selected != null) {
                if (MiniSelector.checkSelected(selected)) {
                    action.selected = selected;
                } else {
                    System.out.println("Такой внутр. выборки не существует");
                    System.exit(-1);
                }
            } else if (selected != null){
                System.out.println("Данному условию нельзя установить внутр. выборку");
                System.exit(-1);
            }

            eat(TokenType.OPEN_BRACKET);

            ArrayList<NodeValue> values = new ArrayList<>();
            while (!(match(TokenType.EOF) || match(TokenType.CLOSE_BRACKET))) {
                values.add(this.expression());
            }
            eat(TokenType.CLOSE_BRACKET);
            values.add(new Node(new NextBlockAction()));
            retNode.setConstruction(values);
            retNode.setOther(this.expression());
            return retNode;
        } else if (get().getType() == TokenType.VARIABLE && get().getValue().equals("save")) {
            eat(TokenType.VARIABLE);
            while (!(match(TokenType.EOF) || match(TokenType.SEMICOLON))) {
                if (match(TokenType.VARIABLE)) {
                    Manager.variablesSaved.add(get().getValue());
                    eat(TokenType.VARIABLE);
                } else {
                    System.out.println("Требуется переменная!");
                    System.exit(-1);
                }
                if (!match(TokenType.SEMICOLON)) {
                    eat(TokenType.COMMA);
                }
            }
            eat(TokenType.SEMICOLON);
            Node node = new Node(new NullAction());
            node.setOther(expression());
            return node;
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
                if (match(TokenType.OPEN_SQUARE_BRACKET)) {
                    next();
                    ArrayList<NodeValue> array = new ArrayList<>();
                    while (!((match(TokenType.EOF)) || match(TokenType.CLOSE_SQUARE_BRACKET))) {
                        array.add(additive());
                        if (!match(TokenType.CLOSE_SQUARE_BRACKET)) {
                            eat(TokenType.COMMA);
                        }
                    }
                    values.add(new ValueArray(array));
                    eat(TokenType.CLOSE_SQUARE_BRACKET);
                } else {
                    values.add(additive());
                }
            }
            if (!match(TokenType.CLOSE_PARENT)) {
                eat(TokenType.COMMA);
            }
        }
        return values;
    }

    // + и -
    private NodeValue additive() {
        NodeValue value = this.multiply();
        while (match(TokenType.PLUS) || match(TokenType.MINUS)) {
            TokenType action = curToken.getType();
            next();
            ArrayList<NodeValue> values = new ArrayList<>();
            values.add(value);
            values.add(this.multiply());
            ArrayList<NodeValue> input = new ArrayList<>(List.of(new ValueArray(values)));
            if (action == TokenType.PLUS) {
                value = new Node(new Plus(), input);
            } else {
                value = new Node(new Minus(), input);
            }
        }
        return value;
    }

    // * и /
    private NodeValue multiply() {
        NodeValue value = this.unary();
        while (match(TokenType.MULTIPLY) || match(TokenType.DIVISION)) {
            TokenType action = curToken.getType();
            next();
            ArrayList<NodeValue> values = new ArrayList<>();
            values.add(value);
            values.add(this.unary());
            ArrayList<NodeValue> input = new ArrayList<>(List.of(new ValueArray(values)));
            if (action == TokenType.MULTIPLY) {
                value = new Node(new Multiply(), input);
            } else {
                value = new Node(new Division(), input);
            }
        }
        return value;
    }

    // Обработка отрицательного значения
    private NodeValue unary() {
        if (match(TokenType.MINUS)) {
            next();
            ArrayList<NodeValue> values = new ArrayList<>();

            values.add(new Value("NUMBER", "0"));
            values.add(this.primary());
            ArrayList<NodeValue> input = new ArrayList<>(List.of(new ValueArray(values)));
            return new Node(new Minus(), input);
        } else {
            return primary();
        }
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
