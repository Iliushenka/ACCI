package ru.iliushenka.acci.parser.common.expression;

import ru.iliushenka.acci.parser.common.expression.actions.SendMessage;
import ru.iliushenka.acci.parser.common.expression.selector.*;
import ru.iliushenka.acci.parser.common.expression.variables.*;

public class ExpressionHandler {

    // Обращаемся к названию и получаем объект нужного действия
    public static Action getAction(String parent, String name) {
        switch (parent) {
            case "player":
                return playerAction(name);
            case "var":
                return variableAction(name);
            case "select":
                return selectAction(name);
            default:
                System.out.println("Такого блока не существует");
                System.exit(-1);
        }
        return null;
    }

    private static Action selectAction(String name) {
        Action action = null;
        switch (name) {
            case "playerDefault":
                action = new DefaultPlayer();
                break;
            case "entityDefault":
                action = new DefaultEntity();
                break;
            case "randomPlayer":
                action = new RandomPlayer();
                break;
            case "randomMob":
                action = new RandomMob();
                break;
            case "randomEntity":
                action = new RandomEntity();
                break;
            case "all":
                action = new AllPlayer();
                break;
            case "allMob":
                action = new AllMob();
                break;
            case "allEntity":
                action = new AllEntity();
                break;
            case "lastMob":
                action = new LastSpawnedMob();
                break;
            case "randomFilter":
                action = new RandomlyFilterSelection();
                break;
            case "equal":
                action = new IfVariableValueEqual();
                break;
            case "notEqual":
                action = new IfVariableValueNotEqual();
                break;
            case "compareNumber":
                action = new IfVariableCompareNumber();
                break;
            case "compareInterval":
                action = new IfVariableCompareInterval();
                break;
            case "textEqual":
                action = new IfVariableTextEqual();
                break;
            case "textContain":
                action = new IfVariableTextContain();
                break;
            case "exist":
                action = new IfVariableExist();
                break;
            case "inRegion":
                action = new IfVariableLocationInRegion();
                break;
            case "startWith":
                action = new IfVariableTextStartWith();
                break;
            case "endWith":
                action = new IfVariableTextEndWith();
                break;
            default:
                System.out.println("Такой выборки не существует");
                System.exit(-1);
        }
        action.isNot = true;
        return action;
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
        Action action = null;
        switch (name) {
            case "send":
                action = new SendMessage();
                break;
            default:
                System.out.println("Такого действия не существует");
                System.exit(-1);
        }
        action.isSelected = true;
        return action;
    }

}
