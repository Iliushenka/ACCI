package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.VariableExists;

import java.util.ArrayList;
import java.util.List;

public class IfVariableExist extends Action {

    private static final ArrayList<Parameter> parameters = VariableExists.extend();
    private static final String type = "IF_VARIABLE_EXISTS";

    public IfVariableExist() {
        super(type, parameters);
    }
}
