package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.TextEquals;

import java.util.ArrayList;
import java.util.List;

public class IfVariableTextEqual extends Action {

    private static final ArrayList<Parameter> parameters = TextEquals.extend();
    private static final String type = "IF_VARIABLE_TEXT_EQUALS";

    public IfVariableTextEqual() {
        super(type, parameters);
    }
}
