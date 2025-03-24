package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;

public class RandomMob extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>();
    private static final String type = "RANDOM_MOB";

    public RandomMob() {
        super(type, parameters);
    }
}
