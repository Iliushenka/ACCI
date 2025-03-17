package ru.iliushenka.acci.parser.common.expression.actions;

import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.Value;
import ru.iliushenka.acci.parser.common.expression.Action;

import java.util.ArrayList;

public class SendMessage extends Action {

    private static final ArrayList<Parameter> parameters = new ArrayList<>();
    private static final String type = "SEND_MESSAGE";

    public SendMessage() {
        super(type, parameters);
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }
}
