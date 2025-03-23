package ru.iliushenka.acci.parser.common.expression.ifs.ifEntity;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class TypeEquals extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("type", 36, "ALL")));
    private static final String type = "NAME_EQUALS";

    public TypeEquals() {
        super(type, parameters);
    }
}
