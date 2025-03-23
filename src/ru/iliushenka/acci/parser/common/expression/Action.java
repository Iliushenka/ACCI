package ru.iliushenka.acci.parser.common.expression;

import ru.iliushenka.acci.parser.common.NodeValue;
import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.Value;

import java.util.ArrayList;

public class Action extends NodeValue {

    protected ArrayList<Value> values;
    protected final ArrayList<Parameter> parameters;

    protected boolean isNot = false;
    protected boolean not = false;

    protected boolean isSelected = false;
    protected boolean selected = false;

    public Action(String type, ArrayList<Parameter> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public ArrayList<Value> getValues() {
        return this.values;
    }

    public ArrayList<Parameter> getParameters() {
        return this.parameters;
    }

    public String toString() {
        return "Action{" + "type=" + this.type + ", " + this.values + "}";
    }
}
