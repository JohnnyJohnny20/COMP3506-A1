// @edu:student-assignment

package uq.comp3506.a1.structures;

// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 * An abstract data type representing the ``list'' operations to represent
 * an ordered collection of elements of type T.
 *
 * A note on the use of Boolean return types for common methods like append:
 * You might be wondering when we'd ever return false when we append an element
 * to a doubly linked list for example.
 * In general, the answer is "never" or only in exceptional cases like if
 * the machine is out of memory. For this assignment, you are safe to assume
 * that when there is no obvious reason to return false, you probably do not
 * need to worry about it. The reason we do this is because the ListInterface
 * is modelled off of Java's own version of the interface which also uses this
 * Boolean contract.
 */
public interface ListInterface<T> {

    /**
     * size is the logical size of the structure; that is, how many valid
     * elements are stored within.
     *
     * @return the number of elements stored inside the given structure
     */
    int size();

    /**
     * If size is non-zero, then this should return false, as the container
     * cannot be empty.
     *
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Appends t to the end of the list.
     *
     * @param t element to add
     * @return true if added, false otherwise
     */
    boolean append(T t);

    /**
     * Prepends t to the beginning of the list.
     *
     * @param t element to add
     * @return true if added, false otherwise
     */
    boolean prepend(T t);

    /**
     * Adds t to the list at the specific idx
     *
     * @param t   element to add
     * @param idx index to insert at
     * @return true if added, false otherwise
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    boolean add(int idx, T t);

    /**
     * Returns the element at a given index
     *
     * @param idx index to access
     * @return element at the given index
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    T get(int idx);

    /**
     * Overwrites the element at a given index and returns the old element
     *
     * @param t   element to add
     * @param idx index to access
     * @return element previously at idx
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    T set(int idx, T t);

    /**
     * Removes and returns the element at a given index
     *
     * @param idx index to access
     * @return element removed from the given index
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    T remove(int idx);

    /**
     * Finds and removes the first matching element
     * Does nothing to the list if there is no such element
     *
     * @return true if the element was present in the list; false otherwise
     */
    boolean removeFirst(T t);

    /**
     * Clears all elements from the list. That means, after calling clear(),
     * the return of size() should be 0, and the data structure should appear
     * to be "empty"
     */
    void clear();

}
