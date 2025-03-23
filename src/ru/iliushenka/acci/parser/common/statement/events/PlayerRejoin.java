package ru.iliushenka.acci.parser.common.statement.events;

import ru.iliushenka.acci.parser.common.statement.EventStatement;

public class PlayerRejoin extends EventStatement {

    public PlayerRejoin() {
        this.type = "PLAYER_REJOIN";
        this.cancellable = false;
    }
}
