package ru.iliushenka.acci.parser.common;

import java.util.ArrayList;
import java.util.List;

public class OutputValue {

    private final ParameterAction action;
    private final ArrayList<NodeValue> values;
    private final int tag;
    private final boolean save;

    public OutputValue(ParameterAction action, ArrayList<NodeValue> values, int tag) {
        this.action = action;
        this.values = values;
        this.tag = tag;
        this.save = false;
    }

    public OutputValue(ParameterAction action, NodeValue value, int tag) {
        this.action = action;
        this.values = new ArrayList<>(List.of(value));
        this.tag = tag;
        this.save = false;
    }

    @Override
    public String toString() {
        return "{" + this.action.toString() + ", " + this.values + ", " + this.tag + "}";
    }
}
