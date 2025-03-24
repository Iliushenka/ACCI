package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.CompareNumbers;

import java.util.ArrayList;
import java.util.List;

public class IfVariableCompareNumber extends Action {

    private static final ArrayList<Parameter> parameters = CompareNumbers.extend();
    private static final String type = "IF_VARIABLE_COMPARE_NUMBER";

    public IfVariableCompareNumber() {
        super(type, parameters);
    }
}
