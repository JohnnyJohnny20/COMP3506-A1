// @edu:student-assignment

package uq.comp3506.a1;

import java.util.Random;

import uq.comp3506.a1.structures.ListInterface;
import uq.comp3506.a1.structures.Oracle;

// You ARE allowed to import the Java linked list to help with your testing
import java.util.LinkedList;

/**
 * A tester for detecting incorrect behaviour in implementations of
 * {@link ListInterface} that are intended to behave as doubly linked lists.
 *
 * <p>Each method should exercise the operations named in the method's
 * documentation and report whether those checks reveal a bug. The supplied
 * {@code testList} is the implementation under test.
 *
 * @param <T> the type of elements stored in the list being tested
 */
public class DoublyLinkedListTester<T> {

    /**
     * This oracle will generate random elements of type T.
     * Use it in your tests. Call oracle.nextT() to generate a random element T.
     */
    private Oracle<T> oracle;

    /**
     * You may use this random generator to generate integers, strings, etc. Anything but the generic type T
     */
    private Random random;

    /**
     * Construct with an oracle
     */
    public DoublyLinkedListTester(Oracle<T> oracle) {
        this.oracle = oracle;
        this.random = new Random();
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#size()},
     * and {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppend(ListInterface<T> testList) {
        if (!testList.isEmpty() ||  testList.size() != 0) {
            return true;
        }

        LinkedList<T> reference = new LinkedList<>();
        T element = oracle.nextT();
        reference.add(element);
        testList.append(element);

        if (testList.isEmpty() || testList.size() != 1) {
            return true;
        }

        T element2 = oracle.nextT();
        reference.add(element2);
        testList.append(element2);

        if (testList.isEmpty() ||  testList.size() != reference.size()) {
            return true;
        }

        for (int i = 0; i < reference.size(); i++) {
            T fromReference = reference.get(i);
            T fromTestList = testList.get(i);
            if (!fromTestList.equals(fromReference)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether {@link ListInterface#prepend(Object)}, {@link ListInterface#size()},
     * and {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsPrepend(ListInterface<T> testList) {
        return true;
    }

    /**
     * Checks whether {@link ListInterface#add(int, Object)}, {@link ListInterface#size()},
     * and {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAdd(ListInterface<T> testList) {
        return true;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#prepend(Object)},
     * {@link ListInterface#add(int, Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#size()}, and {@link ListInterface#isEmpty()} behave incorrectly when
     * used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendPrependAddGet(ListInterface<T> testList) {
        return true;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#set(int, Object)}, {@link ListInterface#size()}, and
     * {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendGetSet(ListInterface<T> testList) {
        return true;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#set(int, Object)}, {@link ListInterface#remove(int)},
     * {@link ListInterface#size()}, and {@link ListInterface#isEmpty()} behave incorrectly when
     * used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendGetSetRemove(ListInterface<T> testList) {
        return true;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#set(int, Object)}, {@link ListInterface#removeFirst(Object)},
     * {@link ListInterface#size()}, and {@link ListInterface#isEmpty()} behave incorrectly when
     * used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendGetSetRemoveFirst(ListInterface<T> testList) {
        return true;
    }

    /**
     * Checks whether the full set of list operations behaves incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAllMethods(ListInterface<T> testList) {
        return true;
    }

}
