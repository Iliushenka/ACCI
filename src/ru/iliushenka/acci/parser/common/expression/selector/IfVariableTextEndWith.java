package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.TextEndWith;

import java.util.ArrayList;
import java.util.List;

public class IfVariableTextEndWith extends Action {

    private static final ArrayList<Parameter> parameters = TextEndWith.extend();
    private static final String type = "IF_VARIABLE_TEXT_END_WITH";

    public IfVariableTextEndWith() {
        super(type, parameters);
    }
}
