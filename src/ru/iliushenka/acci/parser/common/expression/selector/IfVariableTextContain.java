package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.TextContains;

import java.util.ArrayList;
import java.util.List;

public class IfVariableTextContain extends Action {

    private static final ArrayList<Parameter> parameters = TextContains.extend();
    private static final String type = "IF_VARIABLE_TEXT_CONTAINS";

    public IfVariableTextContain() {
        super(type, parameters);
    }
}
