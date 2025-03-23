package ru.iliushenka.acci.parser.common;

import java.util.ArrayList;

public class ValueArray extends NodeValue {

    private final ArrayList<NodeValue> values;

    public ValueArray(ArrayList<NodeValue> values) {
        this.values = values;
    }

    public ArrayList<NodeValue> getValues() {
        return this.values;
    }

    public String toString() {
        return "Values(" + this.values + ")";
    }

    public void replaceValue(int index, NodeValue nodeValue) {
        this.values.set(index, nodeValue);
    }
}
