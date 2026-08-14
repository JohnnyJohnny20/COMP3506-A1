// @edu:student-assignment

package uq.comp3506.a1.structures;

/**
 * The Node class. Not strictly required, but any student
 * building their own linked list might like to use it.
 * data: The data payload
 * prev: The reference to the previous node in the list
 * next: The reference to the next node in the list
 */
public class Node<T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;

    /**
     * Constructs the node type with the given data payload
     *
     * @param data The payload data to store inside this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Retrieves the data payload stored in this node.
     *
     * @return The data payload of type {@code T}.
     */
    public T getData() {
        return data;
    }

    /**
     * Updates the data payload stored in this node.
     *
     * @param newData The new data payload to be stored.
     */
    public void setData(T newData) {
        this.data = newData;
    }

    /**
     * Retrieves the reference to the previous node in the linked list.
     *
     * @return The previous {@code Node}, or {@code null} if this is the first node.
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Updates the reference to the previous node in the linked list.
     *
     * @param prev The new previous {@code Node} to be linked.
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    /**
     * Retrieves the reference to the next node in the linked list.
     *
     * @return The next {@code Node}, or {@code null} if this is the last node.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Updates the reference to the next node in the linked list.
     *
     * @param next The new next {@code Node} to be linked.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

}
