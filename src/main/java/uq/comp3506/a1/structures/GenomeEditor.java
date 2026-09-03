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

    private DynamicArray<Character>[] seq;
    private int chunksCount; // Active chunks
    private int seqLength;
    private int chunksCapacity = 16;
    private static final int CHUNK_IDX = 0;
    private static final int OFFSET = 1;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    /**
     * Creates an empty genome editor.
     */
    public GenomeEditor() {
        // Do whatever you need to initialise the editor
        this.seq = new DynamicArray[chunksCapacity];
        this.chunksCount = 0;
        this.seqLength = 0;
    }

    /**
     * Returns the current length of the genome.
     *
     * @return the number of bases in the genome
     */
    public int length() {
        return this.seqLength;
    }

    private int[] convertIdx(int index) {
        if (index < 0 || index >= this.seqLength) {
            throw new IndexOutOfBoundsException();
        }
        int targetChunk = 0;
        for (int i = 0; i < this.chunksCount; i++) {
            if (index >= seq[i].size()) {
                targetChunk++;
                index -= seq[i].size();
                continue;
            } else {
                break;
            }
        }
        return new int[]{targetChunk, index};
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
        int[] idx = convertIdx(index);
        return seq[idx[CHUNK_IDX]].get(idx[OFFSET]);
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
        if (left > right || left < 0 || right > this.seqLength) {
            throw new IndexOutOfBoundsException();
        }
        if (left == right) {
            return "";
        }
        StringBuilder sub = new StringBuilder();
        int total = right - left;
        int[] startIdx = convertIdx(left);
        int chunk = startIdx[CHUNK_IDX];
        int offset = startIdx[OFFSET];
        while (total != 0) {
            sub.append(seq[chunk].get(offset));
            total--;
            offset++;
            if (offset >= seq[chunk].size()) {
                offset = 0;
                chunk++;
            }
        }
        return sub.toString();
    }

    private int[] convertAddIdx(int index) {
        if (index < 0 || index > this.seqLength) {
            throw new IndexOutOfBoundsException();
        }
        int targetChunk = 0;
        for (int i = 0; i < chunksCount; i++) {
            if (index >= seq[i].size()) {
                targetChunk++;
                index -= seq[i].size();
                continue;
            } else {
                break;
            }
        }
        if (targetChunk == chunksCount) {
            targetChunk--;
            index = seq[targetChunk].size();
        }
        return new int[]{targetChunk, index};
    }

    private void ensureChunkCapacity() {
        if (this.chunksCapacity == this.chunksCount) {
            this.chunksCapacity *= 2;
            DynamicArray[] newArray = new DynamicArray[this.chunksCapacity];
            if (this.chunksCount >= 0) {
                System.arraycopy(this.seq, 0, newArray, 0, this.chunksCount);
            }
            this.seq = newArray;
        }
    }

    private void downShiftChunks(int idx) {
        System.arraycopy(this.seq, idx, this.seq, idx + 1, this.chunksCount - idx);
    }

    private void upShiftChunks(int idx) {
        System.arraycopy(this.seq, idx + 1, this.seq, idx, this.chunksCount - idx - 1);
    }


    private void splitChunk(int chunkIdx) {
        int chunkSize = seq[chunkIdx].size();
        if (chunkSize <= Math.sqrt(seqLength) * 2) {
            return;
        }

        int mid = chunkSize / 2;
        DynamicArray<Character> newChunk = new DynamicArray<>();
        Object[] moved = seq[chunkIdx].removeBulk(mid, seq[chunkIdx].size() - mid);
        Character[] movedChars = new Character[moved.length];
        for (int i = 0; i < moved.length; i++) {
            movedChars[i] = (Character) moved[i];
        }
        newChunk.addBulk(0, movedChars);
        ensureChunkCapacity();
        int newIdx = chunkIdx + 1;
        downShiftChunks(newIdx);
        this.seq[newIdx] = newChunk;
        chunksCount++;
        splitChunk(newIdx);
        splitChunk(chunkIdx);
    }

    private boolean verifyNeighbour(int chunkIdx, int neighbourChunkIdx, int threshold) {
        if (this.seq[chunkIdx].size() + this.seq[neighbourChunkIdx].size() < threshold) {
            return true;
        }
        return false;
    }

    private int findMergeNeighbour(int chunkIdx, int threshold) {
        if (chunksCount <= 1) {
            return -1;
        }
        if (chunkIdx == 0) {
            if (verifyNeighbour(chunkIdx, chunkIdx + 1, threshold)) {
                return RIGHT;
            }
        } else if (chunkIdx == chunksCount - 1) {
            if (verifyNeighbour(chunkIdx, chunkIdx - 1, threshold)) {
                return LEFT;
            }
        } else {
            if (verifyNeighbour(chunkIdx, chunkIdx + 1, threshold)) {
                return RIGHT;
            }
            if (verifyNeighbour(chunkIdx, chunkIdx - 1, threshold)) {
                return LEFT;
            }
        }
        return -1;
    }

    private void mergeChunk(int chunkIdx) {
        int chunkSize = seq[chunkIdx].size();
        int threshold = (int) Math.sqrt(seqLength) / 2;
        int splitThreshold = (int) Math.sqrt(seqLength) * 2;
        if (chunkSize > threshold) {
            return;
        }
        int neighbour = findMergeNeighbour(chunkIdx, splitThreshold);
        if (neighbour != -1) {
            Object[] moved = seq[chunkIdx].removeBulk(0, seq[chunkIdx].size());
            Character[] movedChars = new Character[moved.length];
            for (int i = 0; i < moved.length; i++) {
                movedChars[i] = (Character) moved[i];
            }
            int mergedIdx = -1;
            if (neighbour == LEFT) {
                mergedIdx = chunkIdx - 1;
                this.seq[mergedIdx].addBulk(seq[mergedIdx].size(), movedChars);
            } else {
                mergedIdx = chunkIdx + 1;
                this.seq[mergedIdx].addBulk(0, movedChars);
                mergedIdx = chunkIdx; // considering upshift
            }
            upShiftChunks(chunkIdx);
            chunksCount--;
            mergeChunk(mergedIdx);
        }
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
        if (position < 0 || position > seqLength) {
            throw new IndexOutOfBoundsException();
        }

        Character[] frCharArray = new Character[fragment.length()];
        for (int i = 0; i < fragment.length(); i++) {
            frCharArray[i] = fragment.charAt(i);
        }
        if (chunksCount == 0) {
            // CREATE CHUNKS
            seq[0] = new DynamicArray<>();
            seq[0].addBulk(0, frCharArray);
            seqLength += fragment.length();
            chunksCount++;
            splitChunk(0);
            return;
        }
        int[] idx = convertAddIdx(position);
        seq[idx[CHUNK_IDX]].addBulk(idx[OFFSET], frCharArray);
        seqLength += fragment.length();
        splitChunk(idx[CHUNK_IDX]);
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
        if (length < 0 || position + length > seqLength || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (length == 0) {
            return "";
        }
        int[] idx = convertIdx(position);
        int targetChunk = idx[CHUNK_IDX];
        int startChunk = idx[CHUNK_IDX];
        int offset = idx[OFFSET];
        seqLength -= length;
        StringBuilder str = new StringBuilder();
        while (length != 0) {
            int available = seq[targetChunk].size() - offset;
            int removeable = Math.min(length, available);
            Object[] removed = seq[targetChunk].removeBulk(offset, removeable);
            for (Object base : removed) {
                str.append(base);
            }
            length -= removeable;
            targetChunk++;
            offset = 0;
        }
        int endChunk = targetChunk - 1;
        for (int i = endChunk; i >= startChunk; i--) {
            mergeChunk(i);
        }
        return str.toString();
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
        if (newBase != 'A' && newBase != 'T' && newBase != 'G' && newBase != 'C') {
            throw new IllegalArgumentException();
        }
        int[] idx = convertIdx(position);
        seq[idx[CHUNK_IDX]].set(idx[OFFSET], newBase);
    }

    /**
     * Returns the current genome sequence.
     *
     * @return the genome as a string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < chunksCount; i++) {
            for (int j = 0; j < seq[i].size(); j++) {
                str.append(seq[i].get(j));
            }
        }
        return str.toString();
    }
}
