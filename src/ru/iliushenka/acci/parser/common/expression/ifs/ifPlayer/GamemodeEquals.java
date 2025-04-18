package ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class GamemodeEquals extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("switch", 1, "NUMBER", 13, ParameterAction.CLICK)
    ));
    private static final String type = "GAMEMODE_EQUALS";

    public GamemodeEquals() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
