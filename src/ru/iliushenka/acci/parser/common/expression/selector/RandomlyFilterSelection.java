package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class RandomlyFilterSelection extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("volume", 1, "NUMBER")
    ));
    private static final String type = "RANDOMLY_FILTER_SELECTION";

    public RandomlyFilterSelection() {
        super(type, parameters);
    }
}
