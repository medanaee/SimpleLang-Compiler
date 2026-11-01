package main.Code;

import main.ast.nodes.Node;

public class Code {

    public Node node;
    public String code;

    public Code(Node node, String code) {
        this.node = node;
        this.code = code;
    }

    public Node getNode() {
        return node;
    }

    public String getCode() {
        return code;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setCode(String code) {
        this.code = code;
    }
}