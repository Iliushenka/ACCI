package ru.iliushenka.acci.parser.common.expression.ifs;

import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifEntity.TypeEquals;
import ru.iliushenka.acci.parser.common.expression.ifs.ifGame.BlockEquals;
import ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer.NameEquals;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.*;

public class ifHandler {

    /**
     *
     * @param parent Основной блок условия
     * @param name Условие
     * @return Действие в виде объекта
     */
    public static Action getIf(String parent, String name) {
        Action ifAction = null;
        switch (parent) {
            case "player":
                ifAction = ifPlayer(name);
                break;
            case "entity":
                ifAction = ifEntity(name);
                break;
            case "game":
                ifAction = ifGame(name);
                break;
            case "var":
                ifAction = ifVar(name);
                break;
            default:
                System.out.println("Такого блока для условия нет!");
                System.exit(-1);
        }
        // Устанавливаем, то что можно ставить НЕ
        ifAction.isNot = true;
        return ifAction;
    }

    private static Action ifPlayer(String name) {
        Action ifAction = null;
        switch (name) {
            case "nameEqual":
                ifAction = new NameEquals();
                break;
            default:
                System.out.println("Такого условия у игрока нет!");
                System.exit(-1);
        }
        // Устанавливаем, то что можем сделать внутр. выборку у блока
        ifAction.isSelected = true;
        return ifAction;
    }

    private static Action ifEntity(String name) {
        switch (name) {
            case "typeEqual":
                return new TypeEquals();
        }
        System.out.println("Такого условия у существа нет!");
        System.exit(-1);

        return null;
    }

    private static Action ifGame(String name) {
        switch (name) {
            case "blockEqual":
                return new BlockEquals();
        }
        System.out.println("Такого условия у игры нет!");
        System.exit(-1);

        return null;
    }

    private static Action ifVar(String name) {
        switch (name) {
            case "equal":
                return new ValueEquals();
            case "notEquals":
                return new ValueNotEquals();
            case "textEquals":
                return new TextEquals();
            case "startWith":
                return new TextStartWith();
            case "endWith":
                return new TextEndWith();
            case "exist":
                return new VariableExists();
            case "textContains":
                return new TextContains();
            case "compareNumber":
                return new CompareNumbers();
            case "compareInterval":
                return new CompareInterval();
            case "inRegion":
                return new LocationInRegion();
        }
        System.out.println("Такого условия у переменной нет!");
        System.exit(-1);

        return null;
    }
}
