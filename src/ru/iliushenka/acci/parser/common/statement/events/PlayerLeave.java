package ru.iliushenka.acci.parser.common.statement.events;

import ru.iliushenka.acci.parser.common.statement.EventStatement;

public class PlayerLeave extends EventStatement {

    public PlayerLeave() {
        this.type = "PLAYER_LEAVE";
        this.cancellable = false;
    }
}
