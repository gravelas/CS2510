package uk.ac.nulondon;

public final class Node<T> {

    T element;
    Node<T> next;
    Node<T> prev;
    public Node(T e) {
        element = e;
        next = null;
        prev = null;
    }


}
