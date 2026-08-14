// @edu:student-assignment

package uq.comp3506.a1;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

/**
 * Represents a discrete interval with a defined start and end point.
 * This immutable class serves as a data container for storing the boundaries 
 * of the interval.
 */
public class Interval {
    private final long start;
    private final long end;

    /**
     * Constructs a new {@code Interval} with the specified start and end points.
     *
     * @param start The starting point of the interval.
     * @param end   The ending point of the interval.
     */
    public Interval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Retrieves the starting point of the interval.
     *
     * @return The {@code start} value.
     */
    public long start() {
        return this.start;
    }

    /**
     * Retrieves the ending point of the interval.
     *
     * @return The {@code end} value.
     */
    public long end() {
        return this.end;
    }
}
