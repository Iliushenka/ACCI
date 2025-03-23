package ru.iliushenka.acci.parser.common.expression.ifs.ifVar;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class ValueEquals extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("value", 1, "ALL"),
            new Parameter("values", 27, "ALL")));
    private static final String type = "NAME_EQUALS";

    public ValueEquals() {
        super(type, parameters);
    }
}
