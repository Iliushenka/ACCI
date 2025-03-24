package ru.iliushenka.acci.parser.common.expression.ifs.ifVar;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class CompareNumbers extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("value1", 1, "ALL"),
            new Parameter("switch", 1, "NUMBER", 13, ParameterAction.CLICK),
            new Parameter("value2", 1, "ALL")
            ));
    private static final String type = "COMPARE_NUMBER";

    public CompareNumbers() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
