package uk.ac.nulondon;

public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public MyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
    }

    // Adds an element to the beginning of the list.
    public void addFirst(T data) {
        if (data == null) {
            return;
        }
        Node<T> newHead = new Node<>(data);
        Node<T> oldHead = new Node<>(head.element);
        if (head.element == null) {
            head.next.prev = newHead;
        }
        else {
            head.next.prev = oldHead;
            newHead.next = oldHead;
            oldHead.prev = newHead;
            oldHead.next = head.next;
        }
        head = newHead;
    }

    // Adds an element to the end of the list.
    public void addLast(T data) {
        if (data == null) {
            return;
        }
        Node<T> newTail = new Node<>(data);
        //Node<T> oldTail = new Node<>(tail.element);
        if (tail.element == null) {
            tail.prev.next = newTail;
            newTail.prev = tail.prev;
        }
        else {
            //tail.prev.next = oldTail;
            //oldTail.prev = tail.prev;
            tail.next = newTail;
            newTail.prev = tail;
        }
        tail = newTail;
    }

    // Removes the first element and returns the value
// of the element that was removed.
    public T removeFirst() {
       T toReturn = head.element;
        if (head.next != null) {
            head.next.prev = null;
            head = head.next;
        }
        else {
            head = new Node<>(null);
        }

       return toReturn;
    }

    // Removes the last element and returns the value
// of the element that was removed.
    public T removeLast() {
        if (tail.prev.prev == null) {
            
        }
        T toReturn = tail.element;
        tail.prev.next = null;
        tail = tail.prev;
        return toReturn;
    }

    // Returns the value of the first element.
    public T getFirst() {
        return head.element;
    }

    // Returns the value of the last element.
    public T getLast() {
        return tail.element;
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder();
        Node<T> currentNode = head;
        returnString.append(head.element);
        while (currentNode.next != null) {
            returnString.append(" ").append(currentNode.next.element);
            currentNode = currentNode.next;
        }
        return String.valueOf(returnString);
    }
}
