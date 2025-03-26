package ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class ItemEquals extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("item", 27, "ITEM"),
            new Parameter("switch1", 1, "NUMBER", 45, ParameterAction.CLICK),
            new Parameter("switch2", 1, "NUMBER", 46, ParameterAction.CLICK),
            new Parameter("switch3", 1, "NUMBER", 47, ParameterAction.CLICK),
            new Parameter("switch4", 1, "NUMBER", 48, ParameterAction.CLICK),
            new Parameter("switch5", 1, "NUMBER", 50, ParameterAction.CLICK),
            new Parameter("switch6", 1, "NUMBER", 51, ParameterAction.CLICK),
            new Parameter("switch7", 1, "NUMBER", 52, ParameterAction.CLICK),
            new Parameter("switch8", 1, "NUMBER", 53, ParameterAction.CLICK)
    ));
    private static final String type = "ITEM_EQUALS";

    public ItemEquals() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
