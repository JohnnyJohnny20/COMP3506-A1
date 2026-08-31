/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 */

import uq.comp3506.a1.structures.DynamicArray;
import uq.comp3506.a1.DoublyLinkedListTester;
import uq.comp3506.a1.structures.Oracle;

public class TestDynamicArray {

    public static void main(String[] args) {
        System.out.println("Testing DynamicArray Class...");
        Oracle<Integer> oracle = () -> new java.util.Random().nextInt(10000);
        DoublyLinkedListTester<Integer> tester = new DoublyLinkedListTester<>(oracle);
        DynamicArray<Integer> arr = new DynamicArray<>();

        boolean bugFound = tester.hasBugsAllMethods(arr);

        System.out.println("Bug?: " + bugFound);
    }

}
