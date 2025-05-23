package ru.iliushenka.acci.parser.common.expression.variables;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.Value;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.Arrays;

public class Multiply extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(Arrays.asList(
            new Parameter("var", 1, "VARIABLE"),
            new Parameter("values", 27, "MATH")));
    private static final String type = "MULTIPLY";

    public Multiply() {
        super(type, parameters);
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }
}
