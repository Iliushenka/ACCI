package ru.iliushenka.acci.parser.common.expression.ifs.ifVar;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class VariableExists extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("value", 1, "ALL")
            ));
    private static final String type = "VARIABLE_EXISTS";

    public VariableExists() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
