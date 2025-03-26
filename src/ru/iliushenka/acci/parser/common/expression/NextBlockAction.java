package ru.iliushenka.acci.parser.common.expression;

import ru.iliushenka.acci.parser.common.Parameter;

import java.util.ArrayList;

public class NextBlockAction extends Action{

    private static final ArrayList<Parameter> parameters = new ArrayList<>();
    private static final String type = "NON_TERMINAL_NEXT_BLOCK";

    public NextBlockAction() {
        super(type, parameters);
    }
}
