package ru.iliushenka.acci.parser.common.expression;

import ru.iliushenka.acci.parser.common.Parameter;

import java.util.ArrayList;

public class NullAction extends Action{

    private static final ArrayList<Parameter> parameters = new ArrayList<>();
    private static final String type = "NULL";

    public NullAction() {
        super(type, parameters);
    }
}
