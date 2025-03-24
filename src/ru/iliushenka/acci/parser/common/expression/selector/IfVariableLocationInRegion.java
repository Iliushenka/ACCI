package ru.iliushenka.acci.parser.common.expression.selector;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.expression.ifs.ifVar.LocationInRegion;

import java.util.ArrayList;
import java.util.List;

public class IfVariableLocationInRegion extends Action {

    private static final ArrayList<Parameter> parameters = LocationInRegion.extend();
    private static final String type = "IF_VARIABLE_LOCATION_IN_REGION";

    public IfVariableLocationInRegion() {
        super(type, parameters);
    }
}
