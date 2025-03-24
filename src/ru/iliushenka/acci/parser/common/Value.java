package ru.iliushenka.acci.parser.common;

public class Value extends NodeValue {

    private final String value;
    private boolean save;

    public Value(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public String toString() {
        return "Value(" + this.type + ", " + this.value + ", save=" + this.save + ")";
    }
}
