package ru.iliushenka.acci.parser.common.expression.ifs.ifVar;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class TextEndWith extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("value", 1, "ALL"),
            new Parameter("switch1", 1, "NUMBER", 21, ParameterAction.CLICK),
            new Parameter("switch2", 1, "NUMBER", 23, ParameterAction.CLICK),
            new Parameter("values", 27, "ALL")
            ));
    private static final String type = "TEXT_END_WITH";

    public TextEndWith() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
