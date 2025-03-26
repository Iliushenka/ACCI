package ru.iliushenka.acci.parser.common.expression.ifs.ifPlayer;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.ParameterAction;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class InventoryFull extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>();
    private static final String type = "INVENTORY_FULL";

    public InventoryFull() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
