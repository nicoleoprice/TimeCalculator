package model;

/**
 * This is the node setup for the singly linked list
 * Singly linked lists move in one direction, so there is only one pointer (next)
 * @param <Time>
 */
public class Node<Time> {
    public Time data;
    public Node<Time> next;

    public Node (Time data) {
        this.data = data;
        this.next = null;
    }
}
