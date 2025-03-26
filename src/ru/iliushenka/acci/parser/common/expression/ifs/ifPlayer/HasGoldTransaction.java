package ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class HasGoldTransaction extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("text", 1, "TEXT"),
            new Parameter("number", 1, "MATH")
    ));
    private static final String type = "HAS_GOLD_TRANSACTION";

    public HasGoldTransaction() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
