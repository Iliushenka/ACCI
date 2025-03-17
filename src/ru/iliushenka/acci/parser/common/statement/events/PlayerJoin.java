package ru.iliushenka.acci.parser.common.statement.events;

import ru.iliushenka.acci.parser.common.statement.EventStatement;

public class PlayerJoin extends EventStatement {

    public PlayerJoin() {
        this.type = "PLAYER_JOIN";
    }
}
