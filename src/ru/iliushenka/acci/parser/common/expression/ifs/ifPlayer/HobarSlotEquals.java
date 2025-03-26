package ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class HobarSlotEquals extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("values", 9, "MATH")
    ));
    private static final String type = "HOTBAR_SLOT_EQUALS";

    public HobarSlotEquals() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
