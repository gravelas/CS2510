package uk.ac.nulondon;

import java.util.Map;
import java.util.TreeMap;

public class Node {
    Map<String, Node> children;
    String value;
    int endWord;

    public Node(String value) {
        this.value = value;
        children = new TreeMap<>();
    }

    public Node() {
        this.value = "root";
        children = new TreeMap<>();
    }

    public Node(String value, Map<String, Node> children) {
        this.value = value;
        this.children = children;
    }

    public void incrementEndWord() {
        endWord++;
    }
    public void decreaseEndWord() {
        endWord--;
    }

    public void addChild(String child) {
        children.put(child, new Node(child));
    }
}
