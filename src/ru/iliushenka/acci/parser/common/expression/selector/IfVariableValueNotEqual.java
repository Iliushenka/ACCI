package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.ValueNotEquals;

import java.util.ArrayList;
import java.util.List;

public class IfVariableValueNotEqual extends Action {

    private static final ArrayList<Parameter> parameters = ValueNotEquals.extend();
    private static final String type = "IF_VARIABLE_VAlUE_NOT_EQUALS";

    public IfVariableValueNotEqual() {
        super(type, parameters);
    }
}
