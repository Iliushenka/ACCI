package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;

public class DefaultPlayer extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>();
    private static final String type = "DEFAULT_PLAYER";

    public DefaultPlayer() {
        super(type, parameters);
    }
}
