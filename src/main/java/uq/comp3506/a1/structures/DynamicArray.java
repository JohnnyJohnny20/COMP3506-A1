// @edu:student-assignment

package uq.comp3506.a1.structures;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 * <p>
 * NOTE: You should go and carefully read the documentation provided in the
 * ListInterface.java file - this explains some of the required functionality.
 */
public class DynamicArray<T extends Comparable<T>> implements ListInterface<T> {

    /**
     * size tracks the total number of slots being used in the data array
     */
    private int size = 0;

    /**
     * capacity tracks the total number of slots (used or unused) in the data array
     */
    private int capacity = 0;

    /**
     * data stores the raw objects
     */
    private Object[] data;

    /**
     * Constructs an empty Dynamic Array
     */
    public DynamicArray() {
        // XXX todo
        // Confused about how to resize? Check the Ed lessons...
        this.capacity = 32;
        this.size = 0;
        this.data = new Object[this.capacity];
    }

    // See ListInterface
    @Override
    public int size() {
        return this.size;
    }

    // See ListInterface
    @Override
    public boolean isEmpty() { return this.size() == 0; }

    /**
     * Has the size reached the current capacity?
     * Return true if so, false otherwise.
     * This is merely a convenience function for you. We will not be
     * testing it explicitly.
     */
    public boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * Get current capacity.
     * Again, this is merely a convenience function for you. We will not
     * be testing it explicitly.
     */
    public int getCapacity() {
        return this.capacity;
    }

    private void ensureCapacity() {
        if (this.capacity == this.size) {
            this.capacity *= 2;
            Object[] newArray = new Object[this.capacity];
            if (this.size >= 0) System.arraycopy(this.data, 0, newArray, 0, this.size);
            this.data = newArray;
        }
    }

    /**
     * Add an element to the end of the array. Returns true if successful,
     * false otherwise. [See the note in the ListInterface class about when
     * false would be returned.]
     * Time complexity for full marks: O(1*)
     * That is, O(1) *amortized*.
     */
    @Override
    public boolean append(T element) {
        ensureCapacity();
        this.data[this.size++] = element;
        return true;
    }


    private void downShift(int idx) {
        for (int i = this.size; i > idx; i--) {
            this.data[i] = this.data[i - 1];
        }
    }

    /**
     * Add an element to the beginning of the array. Returns true if successful,
     * false otherwise. 
     * Time complexity for full marks: O(N)
     * See: "add" below for more information
     */
    @Override
    public boolean prepend(T element) {
        ensureCapacity();
        downShift(0);
        this.data[0] = element;
        this.size++;
        return true;
    }

    private boolean notInExistingBounds(int ix) {
        return ix < 0 || ix > this.size - 1;
    }

    /**
     * Add element to index ix.
     * Note: This does not overwrite the element at index ix - that is what
     * the set() method is for, see below. Instead, this function is similar
     * to append or prepend, but it adds the element at a desired index.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Acceptable bounds are [0, size] where 0 will be prepend, size will
     * be append, and anything in between will need to shuffle elements around.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean add(int ix, T element) {
        ensureCapacity();
        if (ix < 0 || ix > this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (ix == 0) {
            this.prepend(element);
        } else if (ix == this.size) {
            this.append(element);
        } else {
            downShift(ix);
            this.data[ix] = element;
            this.size++;
        }
        return true;
    }

    /**
     * Return the element at index ix.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T get(int ix) {
        if (notInExistingBounds(ix)) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.data[ix];
    }

    /**
     * Overwrite the "old" value at ix with element, and return the old value.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T set(int ix, T element) {
        if (notInExistingBounds(ix)) {
            throw new IndexOutOfBoundsException();
        }
        T existingElem = this.get(ix);
        this.data[ix] = element;
        return existingElem;
    }

    private void upShift(int idx) {
        for (int i = idx; i < this.size; i++) {
            this.data[i] = this.data[i + 1];
        }
    }

    /**
     * Remove and return the value at index ix
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(N)
     */
    @Override
    public T remove(int ix) {
        if (notInExistingBounds(ix)) {
            throw new IndexOutOfBoundsException();
        }
        T removedElem = this.get(ix);
        this.data[ix] = null;
        upShift(ix);
        this.size--;
        return removedElem;
    }

    /**
     * Find and remove the first value in the array that equals t (the one
     * with the smallest index).
     * Return true if successful, false otherwise.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean removeFirst(T t) {
        int idx = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(t)) {
                this.data[i] = null;
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return false;
        }
        upShift(idx);
        this.size--;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.data[i] = null;
        }
        this.size = 0;
    }

    /**
     * Sort all of the elements inside the array.
     * <p>
     * Time complexity for full marks: O(NlogN).
     * That is, we expect you to implement a sorting algorithm that runs in
     * "n log n" time. This may be in expectation, or guaranteed worst case.
     * <p>
     * A note on comparisons:
     * <p>
     * You may assume that any type stored inside the DynamicArray already
     * implements Comparable<T> which means you can just use compareTo()
     * in order to sort elements.
     * <p>
     * We will assume sorting in ascending, so you will want to do something
     * like: if (data[i].compareTo(data[j]) < 0) { // data[i] < data[j] }
     */
    public void sort() {

    }
}
