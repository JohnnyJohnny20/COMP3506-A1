// @edu:student-assignment

package uq.comp3506.a1.structures;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.


/**
 * Maintains a mutable genome sequence containing only the bases {@code A},
 * {@code T}, {@code C}, and {@code G}.
 *
 * <p> For full marks, {@link #length()}, {@link #charAt(int)}, {@link #insert(int, String)},
 * {@link #delete(int, int)}, and {@link #mutate(int, char)} should run faster
 * than {@code O(N)} (amortized) for a genome of length {@code N}.
 * {@link #substring(int, int)} and {@link #toString()} should run in
 * {@code O(N)} in the worst case.
 * As a general approach, you might like to implement a more simple ``everything is
 * O(N)'' approach and then optimize it further if you can.
 * 
 * <p> Your implementation should expect that {@link #length()}, {@link #charAt(int)}, {@link #insert(int, String)},
 * {@link #delete(int, int)}, and {@link #mutate(int, char)} will be called in arbitrary order
 * approximately the same number of times.
 */
public final class GenomeEditor {

    /**
     * Creates an empty genome editor.
     */
    public GenomeEditor() {
        // Do whatever you need to initialise the editor
    }

    /**
     * Returns the current length of the genome.
     *
     * @return the number of bases in the genome
     */
    public int length() {
        return -1;
    }

    /**
     * Returns the base at the specified zero-based index.
     *
     * @param index the index of the base; must satisfy
     *              {@code 0 <= index < length()}
     * @return the base at {@code index}
     * @throws IndexOutOfBoundsException if {@code index} is outside the genome
     */
    public char charAt(int index) {
        return '?';
    }

    /**
     * Returns the genome subsequence in the half-open interval
     * {@code [left, right)}.
     *
     * <p>For example, {@code substring(2, 6)} returns {@code "GTTG"} for the
     * genome {@code "ACGTTGCA"}.
     *
     * @param left the inclusive start index
     * @param right the exclusive end index
     * @return the requested genome subsequence
     * @throws IndexOutOfBoundsException if the range does not satisfy
     *         {@code 0 <= left <= right <= length()}
     */
    public String substring(int left, int right) {
        return "";
    }

    /**
     * Inserts a genome fragment immediately before the specified position.
     * Position {@code 0} inserts at the beginning, while {@code length()}
     * appends to the genome.
     *
     * <p>For example, inserting {@code "TT"} at position {@code 2} in
     * {@code "ACGT"} produces {@code "ACTTGT"}.
     *
     * @param position the insertion position; must satisfy
     *                 {@code 0 <= position <= length()}
     * @param fragment the fragment to insert
     * @throws IndexOutOfBoundsException if {@code position} is invalid
     */
    public void insert(int position, String fragment) {

    }

    /**
     * Deletes a sequence of bases starting at the specified position.
     *
     * <p>For example, deleting {@code 3} bases at position {@code 2} from
     * {@code "ACGTTGCA"} returns {@code "GTT"} and leaves {@code "ACGCA"}.
     *
     * @param position the index of the first base to delete
     * @param length the number of bases to delete
     * @return the deleted sequence
     * @throws IndexOutOfBoundsException if {@code position < 0},
     *         {@code length < 0}, or {@code position + length} exceeds the
     *         current genome length
     */
    public String delete(int position, int length) {
        return "";
    }

    /**
     * Replaces the base at the specified position.
     *
     * <p>For example, replacing the base at position {@code 1} in
     * {@code "ACGT"} with {@code 'T'} produces {@code "ATGT"}.
     *
     * @param position the index of the base to replace
     * @param newBase the replacement base; one of {@code A}, {@code C},
     *                {@code G}, or {@code T}
     * @throws IndexOutOfBoundsException if {@code position} is outside the
     *         genome
     * @throws IllegalArgumentException if {@code newBase} is invalid
     */
    public void mutate(int position, char newBase) {

    }

    /**
     * Returns the current genome sequence.
     *
     * @return the genome as a string
     */
    @Override
    public String toString() {
        return "";
    }
}
