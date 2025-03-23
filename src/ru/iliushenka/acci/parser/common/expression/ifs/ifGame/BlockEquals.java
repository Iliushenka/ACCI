package ru.iliushenka.acci.parser.common.expression.ifs.ifGame;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class BlockEquals extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("loc", 1, "ALL"),
            new Parameter("type", 36, "ALL")));
    private static final String type = "NAME_EQUALS";

    public BlockEquals() {
        super(type, parameters);
    }
}
