package ru.iliushenka.acci.parser.common.statement;

import ru.iliushenka.acci.parser.Parser;
import ru.iliushenka.acci.parser.common.*;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.utility.Manager;
import ru.iliushenka.acci.utility.Node;

import java.util.ArrayList;

public class Statement {

    protected String type;
    protected Node node;

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return this.node;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        return "Statement{" + "node=" + this.node.toString() + "}";
    }

    public ArrayList<NodeValue> toFlatten() {
        ArrayList<NodeValue> output = new ArrayList<>();

        ArrayList<NodeValue> objects = new ArrayList<>();
        objects.add(node);

        while (!objects.isEmpty()) {
            int index = objects.size() - 1;
            NodeValue node = objects.get(index);
            if (node instanceof Node) {
                if (((Node) node).flag && !((Node) node).getValues().isEmpty()) {
                    ArrayList<NodeValue> nodeValues = ((Node) node).getValues();
                    for (int i = nodeValues.size() - 1; i > -1; i--) {
                        NodeValue nodeValue = nodeValues.get(i);
                        if (nodeValue instanceof Node) {
                            ((Node) node).replaceNode(i, replaceNode(nodeValue));
                        }
                        objects.add(nodeValue);
                    }

                    ((Node) node).flag = false;
                } else {
                    objects.remove(index);
                    output.add(node);

                    objects.add(((Node) node).getOther());
                    ((Node) node).setOther(null);

                    ArrayList<NodeValue> construction = ((Node) node).getConstruction();
                    ((Node) node).setConstruction(null);
                    for (int i = construction.size() - 1; i > -1; i--) {
                        objects.add(construction.get(i));
                    }
                }
            } else if (node instanceof ValueParameter) {
                NodeValue nodeValue = ((ValueParameter) node).getValue();
                if (nodeValue instanceof Node) {
                    objects.add(nodeValue);
                    ((ValueParameter) node).setValue(replaceNode(nodeValue));
                }
                objects.remove(index);
            } else if (node instanceof ValueParameterArray) {
                ArrayList<NodeValue> nodeValues = ((ValueParameterArray) node).getValues();
                for (int i = nodeValues.size() - 1; i > -1; i--) {
                    NodeValue nodeValue = nodeValues.get(i);
                    if (nodeValue instanceof Node) {
                        ((ValueParameterArray) node).replaceValue(i, replaceNode(nodeValue));
                    }
                    objects.add(nodeValue);
                }
                objects.remove(index);
            } else if (node instanceof ValueArray) {
                ArrayList<NodeValue> nodeValues = ((ValueArray) node).getValues();
                for (int i = nodeValues.size() - 1; i > -1; i--) {
                    NodeValue nodeValue = nodeValues.get(i);
                    if (nodeValue instanceof Node) {
                        ((ValueArray) node).replaceValue(i, replaceNode(nodeValue));
                    }
                    objects.add(nodeValue);
                }
                objects.remove(index);
            } else {
                objects.remove(index);
            }
        }
        return output;
    }

    protected Value replaceNode(NodeValue node) {
        Value nodeValue = new Value("VARIABLE", Manager.filename + "_" + Integer.toString(Manager.index));
        Manager.index++;
        ((Node) node).setNameVariable(nodeValue);
        return nodeValue;
    }
}
