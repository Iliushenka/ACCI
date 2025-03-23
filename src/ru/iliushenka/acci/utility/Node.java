package ru.iliushenka.acci.utility;

import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.parser.common.NodeValue;

import java.util.ArrayList;

public class Node extends NodeValue {

    private final Action action;

    private ArrayList<NodeValue> values = new ArrayList<>();
    private ArrayList<NodeValue> construction = new ArrayList<>();
    private NodeValue other;

    public boolean flag = true;

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

    public void setConstruction(ArrayList<NodeValue> construction) {
        this.construction = construction;
    }

    public String toString() {
        if (this.other != null) {
            return "Node{" + this.action.toString()+ " " + this.values + " " + this.other.toString() + "}";
        } else {
            return "Node{" + this.action.toString() + " " + this.values + "}";
        }
    }

    public ArrayList<NodeValue> getValues() {
        return values;
    }

    public ArrayList<NodeValue> getConstruction() {
        return construction;
    }

    public NodeValue getOther() {
        return other;
    }

    public void setNameVariable(NodeValue nodeValue) {
        this.values.add(0, nodeValue);
    }

    public void replaceNode(int index, NodeValue nodeValue) {
        this.values.set(index, nodeValue);
    }

    public Action getAction() {
        return action;
    }
}
