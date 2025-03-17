package ru.iliushenka.acci.parser.common.expression;

import ru.iliushenka.acci.parser.common.expression.actions.SendMessage;

public class ExpressionHandler {

    // Обращаемся к названию и получаем объект нужного действия
    public static Action getAction(String parent, String name) {
        switch (parent) {
            case "player":
                return playerAction(name);
            default:
                System.out.println("Такого блока не существует");
                System.exit(-1);
        }
        return null;
    }

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
