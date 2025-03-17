package ru.iliushenka.acci.parser.common;

import java.util.ArrayList;

public class ValueParameterArray extends NodeValue {

    private final ArrayList<NodeValue> values;

    public ValueParameterArray(String name, ArrayList<NodeValue> values) {
        this.type = name;
        this.values = values;
    }

    public int getLength() {
        return this.values.size();
    }

    public ArrayList<NodeValue> getValues() {
        return this.values;
    }

    public String toString() {
        return "ArrayParameter{" + this.type + ", " + this.values.toString() + "}";
    }
}
