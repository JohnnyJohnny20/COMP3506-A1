// @edu:student-assignment

package uq.comp3506.a1;

import java.util.Arrays;

/**
 * Represents a data structure for storing and processing a set of points, 
 */
public class ProcessedPoints {
    private final long[] sortedPoints;

    public ProcessedPoints(long[] points) {
        this.sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
    }
    /**
     * Determines the number of points that lie within a specified distance from a given point.
     *
     * @param x The reference point (center) for the query.
     * @param r The maximum distance (radius) to search around the reference point.
     * @return The number of points that are within distance {@code r} of point {@code x}.
     */
    public long query(long x, long r) {
        int defaultEndIdx = sortedPoints.length - 1;
        int leftMostIdx = leftMost(sortedPoints, 0, defaultEndIdx, x - r);
        int rightMostIdx = rightMost(sortedPoints,0, defaultEndIdx, x + r);
        if (this.sortedPoints[leftMostIdx] < x - r || this.sortedPoints[rightMostIdx] > x + r) {
            return 0;
        }
        return (long) rightMostIdx- leftMostIdx + 1;
    }

    private int leftMost(long[] points, int startIdx, int endIdx, long key) {
        if (startIdx >= endIdx) {
            return startIdx;
        }
        int mid = startIdx + (endIdx - startIdx) / 2; // round down version
        if(points[mid] < key) {
            return leftMost(points, mid + 1, endIdx, key);
        } else if (points[mid] >= key) {
            return leftMost(points, startIdx, mid, key); // include mid
        }
        return -1;
    }

    private int rightMost(long[] points, int startIdx, int endIdx, long key) {
        if (startIdx >= endIdx) {
            return startIdx;
        }
        int mid = startIdx + (endIdx - startIdx + 1) / 2; // round up version
        if (points[mid] <= key) {
            return rightMost(points, mid, endIdx, key);
        } else if (points[mid] > key) {
            return rightMost(points, startIdx, mid - 1, key);
        }
        return -1;
    }
}
