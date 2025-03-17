package ru.iliushenka.acci.parser.common;

public class Value extends NodeValue {

    private final String value;

    public Value(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "Value(" + this.type + ", " + this.value + ")";
    }
}
