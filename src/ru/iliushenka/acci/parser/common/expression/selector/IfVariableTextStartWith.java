package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.TextStartWith;

import java.util.ArrayList;
import java.util.List;

public class IfVariableTextStartWith extends Action {

    private static final ArrayList<Parameter> parameters = TextStartWith.extend();
    private static final String type = "IF_VARIABLE_TEXT_START_WITH";

    public IfVariableTextStartWith() {
        super(type, parameters);
    }
}
