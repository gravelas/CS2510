package uk.ac.nulondon;

import java.util.List;
import java.util.Stack;

public class MySingleLinkList<T> {

    private Node<T> head;

    private static class Node<T> {
        T data;
        Node<T> next;
        private Node(T elem) {
            data = elem;
            next = null;
        }
    }

    public MySingleLinkList() {
        head = null;
    }

    // Add an item to the front of the list
    public void add(T data) {
        // Make a new Node with data
        Node<T> temp = new Node<>(data);

        if (head == null) {
            head = temp;
        }
        // List is not empty
        else {
            temp.next = head;
            head = temp;
        }
    }

    // Get the head node a list
    public Node<T> getHead() {
        return head;
    }

    /**
     * Searches the list for a match and returns where the given element is in the list
     * @param element the data we're looking for
     * @return the index of the given element, -1 if empty
     */
    public int indexOf(T element) {
        int i = 0;
        // Your code here.
        Node<T> currentNode = head;
        if (currentNode == null) {
            return -1;
        }
        while (currentNode.next != null && currentNode.data != element) {
            currentNode = currentNode.next;
            i++;
        }
        return i;
    }

    /**
     * Interleave the given list with this list
     * @param givenList the list we want to interleave
     */
    public MySingleLinkList<T> interleave(Node<T> givenList) {
        MySingleLinkList<T> result = new MySingleLinkList<>();

        // Your code here.
        Node<T> currentList = head;
        while (currentList != null || givenList != null) {
            if (currentList != null) {
                result.add(currentList.data);
                currentList = currentList.next;
            }
            if (givenList != null) {
                result.add(givenList.data);
                givenList = givenList.next;
            }
        }

        return result;
    }

    /**
     * Function that shifts this list by one,
     * making the tail the head and the head second
     */
    public void shiftByOne() {
        // Your code here.
        Node<T> currentNode = head;
        while (currentNode.next.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next.next = head;
        head = currentNode.next;
        currentNode.next = null;


    }

    /**
     * Determines if this list is a palindrome
     * @return true if this list is a palindrome, false if not
     */
    public boolean palindrome() {

        // Your code here.
        if (head == null) {
            return true;
        }
        Stack<T> frontStack = new Stack<>();
        Node<T> frontIter = head;
        while (frontIter != null) {
            frontStack.add(frontIter.data);
            frontIter = frontIter.next;
        }
        List<T> backStack = frontStack.reversed();
        System.out.println(frontStack);
        System.out.println(backStack);
        return (frontStack.equals(backStack));

    }

    /**
     * Convert the linked list to a string with space-separated values
     *
     * @return a string with every value in the list
     */
    @Override
    public String toString() {
        // If empty we return empty string
        if(head == null) {
            return "";
        }

        StringBuilder val = new StringBuilder();

        // Iterate but stop at last node
        Node<T> iter = head;
        while(iter.next != null) {
            val.append(iter.data.toString());
            val.append(" ");
            iter = iter.next;
        }

        // Copy value from tail and add newline
        val.append(iter.data.toString());
        return val.toString();
    }

    public static void main(String[] args) {

    }
}
