package ru.iliushenka.acci.parser.common.statement;

import ru.iliushenka.acci.utility.Node;

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
}
