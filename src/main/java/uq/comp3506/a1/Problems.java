// @edu:student-assignment

package uq.comp3506.a1;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

/**
 * The class containing all problem stubs. Refer to the spec for formal definitions and explanations.
 */
public class Problems {

    /**
     * Problem 1: Return the sum of the missing odd numbers in the range of interest.
     *
     * <p>Expected test sizes:
     * <ul>
     *   <li>Basic: up to {@code n = 10,000} numbers, with generated values up to
     *       {@code 10,000}.</li>
     *   <li>Exhaustive: {@code n = 10,000} numbers, with generated values up to
     *       {@code 1,000,000}.</li>
     *   <li>Welcome to COMP3506: {@code n = 5,000,000} numbers, with generated values up to
     *       {@code 1,000,000,000}.</li>
     * </ul>
     */
    public static long sumOddNumbers(long[] numbers) {
        return -1;
    }

    /**
     * Problem 2: Find the pair of distinct numbers that minimizes their XOR; return them and their XOR'd value.
     *
     * <p>Expected test sizes:
     * <ul>
     *   <li>Basic: {@code n = 1,000} numbers.</li>
     *   <li>Exhaustive: {@code n = 10,000} numbers.</li>
     *   <li>Welcome to COMP3506: {@code n = 1,000,000} numbers.</li>
     * </ul>
     */
    public static XorPair xor(long[] numbers) {
        return new XorPair(0, 0, 0);
    }
    
    /**
     * Problem 3: Find and return the maximum number of vendors serving any single point.
     *
     * <p>Expected test sizes:
     * <ul>
     *   <li>Basic: up to {@code n = 5,000} intervals.</li>
     *   <li>Exhaustive: up to {@code n = 250,000} intervals.</li>
     *   <li>Welcome to COMP3506: {@code n = 2,000,000} intervals.</li>
     * </ul>
     */
    public static long stalls(Interval[] intervals) {
        return -1;
    }

    /**
     * Problem 4: Return a ProcessedPoints object that can answer arbitrary RivalDealer queries.
     *
     * <p>Expected test sizes, where {@code n} is the number of points and {@code q} is the
     * number of queries made against the returned object:
     * <ul>
     *   <li>Basic: {@code n = 1,000}, {@code q = 2,000}.</li>
     *   <li>Exhaustive: up to {@code n = 250,000}, {@code q = 250,000}.</li>
     *   <li>Welcome to COMP3506: {@code n = 5,000,000}, up to {@code q = 3,000,000}.</li>
     * </ul>
     */
    public static ProcessedPoints rivalDealer(long[] points) {
        return new ProcessedPoints();
    }

}
