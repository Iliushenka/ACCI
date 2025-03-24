package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.CompareInterval;

import java.util.ArrayList;
import java.util.List;

public class IfVariableCompareInterval extends Action {

    private static final ArrayList<Parameter> parameters = CompareInterval.extend();
    private static final String type = "IF_VARIABLE_VAlUE_NOT_EQUALS";

    public IfVariableCompareInterval() {
        super(type, parameters);
    }
}
