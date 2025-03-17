package ru.iliushenka.acci.parser.common;

public class ValueParameter extends NodeValue {

    private final NodeValue value;

    public ValueParameter(String name, NodeValue value) {
        this.type = name;
        this.value = value;
    }

    public NodeValue getValue() {
        return this.value;
    }

    public String toString() {
        return "Parameter{" + this.type + ", " + this.value.toString() + "}";
    }
}
