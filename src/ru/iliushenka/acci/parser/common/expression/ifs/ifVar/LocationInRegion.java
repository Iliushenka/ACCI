package ru.iliushenka.acci.parser.common.expression.ifs.ifVar;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;
import java.util.List;

public class LocationInRegion extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>(List.of(
            new Parameter("pos", 1, "LOCATION"),
            new Parameter("pos1", 1, "LOCATION"),
            new Parameter("pos2", 1, "LOCATION")
            ));
    private static final String type = "LOCATION_IN_REGION";

    public LocationInRegion() {
        super(type, parameters);
    }

    public static ArrayList<Parameter> extend() {
        return parameters;
    }
}
