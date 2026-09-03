// @edu:student-assignment

package uq.comp3506.a1;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

import java.util.Arrays;

/**
 * The class containing all problem stubs. Refer to the spec for formal definitions and explanations.
 */
public class Problems {

    private static final int START = 0;
    private static final int END = 1;

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
        long max = numbers[0];
        long min = numbers[0];
        long sumOfExistingOdds = 0;
        for (long number : numbers) {
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
            if (number % 2 != 0) {
                sumOfExistingOdds += number;
            }

        }
        long firstOdd = min;
        long lastOdd = max;
        if (firstOdd % 2 == 0) {
            firstOdd++;
        }
        if (lastOdd % 2 == 0) {
            lastOdd--;
        }

        long n = ((lastOdd - firstOdd) / 2 + 1);
        return n * (firstOdd + lastOdd) / 2 - sumOfExistingOdds;
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
        Arrays.sort(numbers);
        long xorRes = numbers[0] ^ numbers[1];
        long x =  numbers[0];
        long y = numbers[1];
        for (int i = 1; i < numbers.length - 1; i++) {
            long xor = numbers[i] ^ numbers[i + 1];
            if (xor < xorRes) {
                xorRes = xor;
                x = numbers[i];
                y = numbers[i + 1];
            }
        }
        return new XorPair(xorRes, x, y);
    }

    // 0 is start of queue, 1 is end. Utilized AI
    private record Event(long position, int type) implements Comparable<Event> {
        @Override
        public int compareTo(Event other) {
            if (this.position != other.position()) {
                return Long.compare(this.position, other.position);
            }
            return Integer.compare(other.type, this.type);
        }
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
        Event[] events = new Event[intervals.length * 2];
        int eventsCounter = 0;
        for (Interval interval : intervals) {
            Event evStart = new Event(interval.start(), START);
            Event evEnd = new Event(interval.end() + 1, END);
            events[eventsCounter] = evStart;
            events[eventsCounter + 1] = evEnd;
            eventsCounter += 2;
        }

        Arrays.sort(events);
        long evCount = 0;
        long max = 0;
        for (Event event : events) {
            if (event.type == START) {
                evCount++;
            } else {
                evCount--;
            }
            if (evCount > max) {
                max = evCount;
            }
        }

        return max;
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
        return new ProcessedPoints(points);
    }

}
