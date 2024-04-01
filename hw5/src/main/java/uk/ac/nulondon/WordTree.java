package uk.ac.nulondon;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WordTree {
    Node head;

    public WordTree() {
        head = new Node();
    }
    // returns true if the word is in the tree
    public boolean search(String x) {
        Node currentNode = head;
        for (int i = 0; i < x.length(); i++) {
            if (currentNode.children.get(x.substring(i, i+1)) == null) {
                return false;
            }
            currentNode = currentNode.children.get(x.substring(i, i+1));
        }
        return true;
    }

    // returns the number of instances of the word x in the tree
    public int count(String x) {
        Node currentNode = head;
        for (int i = 0; i < x.length(); i++) {
            if (currentNode.children.get(x.substring(i, i+1)) == null) {
                return 0;
            }
            currentNode = currentNode.children.get(x.substring(i, i+1));
        }
        return currentNode.endWord;
    }

    // creates the necessary nodes to store the word or increments the count if it exists.
    public boolean insert(String x) {
        Node currentNode = head;
        for (int i = 0; i < x.length(); i++) {
            if (currentNode.children.get(x.substring(i, i+1)) == null) {
                currentNode.addChild(x.substring(i, i+1));
            }
            currentNode = currentNode.children.get(x.substring(i, i+1));
        }
        currentNode.incrementEndWord();
        return true;
    }

    // decrements the count of words in the tree, it does not remove nodes
    public boolean remove(String x) {
        Node currentNode = head;
        for (int i = 0; i < x.length(); i++) {
            if (currentNode.children.get(x.substring(i, i+1)) == null) {
                return false;
            }
            currentNode = currentNode.children.get(x.substring(i, i+1));
        }
        currentNode.decreaseEndWord();
        return true;
    }

// toString will create a string representation of the trie.
// The method should indent at each level in the tree, the number of
// spaces should be the depth in the tree.
// It should print the character and the count

// So for the cat dog tree above we would get the String
    public String toString() {
        return stringBuilder(head, 0);
    }

    public String stringBuilder(Node currentNode, int depth) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            s.append("  ");
        }
        if (currentNode.children.isEmpty()) {
            return String.valueOf(s.append(currentNode.value).append(" (").append(currentNode.endWord).append(")\n"));
        }
        s.append(currentNode.value).append(" (").append(currentNode.endWord).append(")\n");
        Iterator<Map.Entry<String, Node>> nodeIterator = currentNode.children.entrySet().iterator();
        while (nodeIterator.hasNext()) {
            System.out.println(currentNode.children.entrySet());
            s.append(stringBuilder(nodeIterator.next().getValue(), depth+1));
            nodeIterator.remove();
        }
        return s.toString();
    }
}
