package ru.iliushenka.acci.parser.common.expression.ifs;

import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifEntity.TypeEquals;
import ru.iliushenka.acci.parser.common.expression.ifs.ifGame.BlockEquals;
import ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer.NameEquals;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.ValueEquals;

public class ifHandler {

    /**
     *
     * @param parent Основной блок условия
     * @param name Условие
     * @return Действие в виде объекта
     */
    public static Action getIf(String parent, String name) {
        switch (parent) {
            case "player":
                return ifPlayer(name);
            case "entity":
                return ifEntity(name);
            case "game":
                return ifGame(name);
            case "var":
                return ifVar(name);
        }
        System.out.println("Такого блока для условия нет!");
        System.exit(-1);

        return null;
    }

    private static Action ifPlayer(String name) {
        switch (name) {
            case "nameEqual":
                return new NameEquals();
        }
        System.out.println("Такого условия у игрока нет!");
        System.exit(-1);

        return null;
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
            case "valueEqual":
                return new ValueEquals();
        }
        System.out.println("Такого условия у переменной нет!");
        System.exit(-1);

        return null;
    }
}
