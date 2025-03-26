package ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class OpenInventoryNameEquals extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("items", 27, "TEXT"),
            new Parameter("switch1", 1, "NUMBER", 39, ParameterAction.CLICK),
            new Parameter("switch2", 1, "NUMBER", 41, ParameterAction.CLICK)
    ));
    private static final String type = "OPEN_INVENTORY_NAME_EQUALS";

    public OpenInventoryNameEquals() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
