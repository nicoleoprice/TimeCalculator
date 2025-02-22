package model;

/**
 * This is the node setup for the singly linked list
 * Singly linked lists move in one direction, so there is only one pointer (next)
 * @param <T>
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node (T data) {
        this.data = data;
        this.next = null;
    }
}
