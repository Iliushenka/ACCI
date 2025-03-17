package ru.iliushenka.acci.parser.common.statement;

import ru.iliushenka.acci.parser.common.statement.events.PlayerJoin;
import ru.iliushenka.acci.parser.common.statement.events.PlayerLeave;
import ru.iliushenka.acci.parser.common.statement.events.PlayerRejoin;

public class EventHandler {

    // Обращаемся к названию и получаем объект нужного события
    public static EventStatement getEvent(String name) {
        switch (name) {
            case "join":
                return new PlayerJoin();
            case "leave":
                return new PlayerLeave();
            case "rejoin":
                return new PlayerRejoin();
            default:
                System.out.println("Такого события не существует");
                System.exit(-1);
        }
        return null;
    }
}
