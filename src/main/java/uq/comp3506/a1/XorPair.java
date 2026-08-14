// @edu:student-assignment

package uq.comp3506.a1;

/**
 * Represents a pair of values alongside an associated XOR answer.
 * This immutable class serves as a data container for storing the result 
 * of an XOR-related operation and the two elements that produced it.
 */
public class XorPair {
    private final long answer;
    private final long x;
    private final long y;

    /**
     * Constructs a new {@code XorPair} with the specified answer and pair values.
     *
     * @param answer The resulting XOR answer associated with the elements.
     * @param x      The first element of the pair.
     * @param y      The second element of the pair.
     */
    public XorPair(long answer, long x, long y) {
        this.answer = answer;
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the stored answer value.
     *
     * @return The {@code answer} associated with this pair.
     */
    public long answer() {
        return this.answer;
    }

    /**
     * Retrieves the first element of the pair.
     *
     * @return The {@code x} value.
     */
    public long x() {
        return this.x;
    }

    /**
     * Retrieves the second element of the pair.
     *
     * @return The {@code y} value.
     */
    public long y() {
        return this.y;
    }
}
