package ru.iliushenka.acci.utility;

import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.NodeValue;

import java.util.ArrayList;

public class Node extends NodeValue {

    private final Action action;
    private ArrayList<NodeValue> values;
    private NodeValue other;

    public Node(Action action) {
        this.type = "Node";

        this.action = action;
    }

    public Node(Action action, ArrayList<NodeValue> values) {
        this.type = "Node";

        this.action = action;
        this.values = values;
    }

    public Node(Action action, ArrayList<NodeValue> values, NodeValue other) {
        this.type = "Node";

        this.action = action;
        this.values = values;
        this.other = other;
    }

    public void setValues(ArrayList<NodeValue> values) {
        this.values = values;
    }

    public void setOther(NodeValue other) {
        this.other = other;
    }

    public String toString() {
        if (this.other != null) {
            return "Node{" + this.action.toString() + " " + this.other.toString() + " " + this.values + "}";
        } else {
            return "Node{" + this.action.toString() + " " + this.values + "}";
        }
    }
}
