package model;

/**
 * The TimeList is a singly-linked list
 */
public class TimeList<Time> {
    public Node<Time> head;

    //constructor
    public TimeList() {
        this.head = null;
    }

    //start the list
    public void setHead(Node<Time> head) {
        this.head = head;
    }

    //append head
    public Node<Time> appendData(Time data) {
        Node<Time> newHead = new Node<>(data);
        Node<Time> current = this.head;

        //check if list is empty
        if(current == null) {
            this.head = newHead;
            return newHead;
        }

        //find last node
        while(current.next != null) {
            current = current.next;
        }

        //append to end of list
        current.next = newHead;

        return newHead;
    }

    //delete (skip over)
    public Node<Time> deleteData(Time data) {
        Node<Time> current = this.head;
        Node<Time> toDelete = null;

        if(current.data.equals(data)) {
            toDelete = current;
            current = current.next;
            return toDelete;
        }

        while(current.next != null) {
            if(current.next.data.equals(data)) {

                //skip the node
                current.next = toDelete.next;
                toDelete.next = null;
                return toDelete;
            }
            //move pointer
            current = current.next;
        }

        return toDelete;
    }

    public String toString() {
        Node<Time> toPrint = this.head;

        //use StringBuilder to append nodes
        StringBuilder sb = new StringBuilder();

        while(toPrint != null) {
            //append nodes to StringBuilder
            sb.append(toPrint.data);
            sb.append(" -> ");

            toPrint = toPrint.next;
        }

        //append null at the end
        sb.append("NULL");

        return sb.toString();
    }
}
