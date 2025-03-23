package ru.iliushenka.acci.parser.common.expression;

import ru.iliushenka.acci.parser.common.expression.actions.SendMessage;
import ru.iliushenka.acci.parser.common.expression.variables.*;

public class ExpressionHandler {

    // Обращаемся к названию и получаем объект нужного действия
    public static Action getAction(String parent, String name) {
        switch (parent) {
            case "player":
                return playerAction(name);
            case "var":
                return variableAction(name);
            default:
                System.out.println("Такого блока не существует");
                System.exit(-1);
        }
        return null;
    }

    // Блок работы со значениями
    private static Action variableAction(String name) {
        switch (name) {
            case "plus":
                return new Plus();
            case "minus":
                return new Minus();
            case "multiply":
                return new Multiply();
            case "division":
                return new Division();
            case "assign":
                return new Assign();
            default:
                System.out.println("Такого действия не существует");
                System.exit(-1);
        }
        return null;
    }

    // Блок действия игрока
    private static Action playerAction(String name) {
        switch (name) {
            case "send":
                return new SendMessage();
            default:
                System.out.println("Такого действия не существует");
                System.exit(-1);
        }
        return null;
    }

}
