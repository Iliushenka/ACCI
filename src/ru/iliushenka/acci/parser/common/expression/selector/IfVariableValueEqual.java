package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.ValueEquals;

import java.util.ArrayList;
import java.util.List;

public class IfVariableValueEqual extends Action {

    private static final ArrayList<Parameter> parameters = ValueEquals.extend();
    private static final String type = "IF_VARIABLE_VAlUE_EQUALS";

    public IfVariableValueEqual() {
        super(type, parameters);
    }
}
