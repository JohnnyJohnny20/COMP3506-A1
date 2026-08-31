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

        for (int i = 0; i < 2500; i++) {
            T element = oracle.nextT();
            reference.add(element);
            testList.append(element);

            if (testList.isEmpty() ||  testList.size() != reference.size()) {
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
        if (!testList.isEmpty() ||  testList.size() != 0) {
            return true;
        }

        LinkedList<T> reference = new LinkedList<>();

        for (int i = 0; i < 2500; i++) {
            T element = oracle.nextT();
            reference.addFirst(element);
            testList.prepend(element);

            if (testList.isEmpty() ||  testList.size() != reference.size()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether {@link ListInterface#add(int, Object)}, {@link ListInterface#size()},
     * and {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAdd(ListInterface<T> testList) {

        try {
            testList.add(-1, oracle.nextT());
            return true;
        } catch (IndexOutOfBoundsException e) {
            if (!testList.isEmpty() ||  testList.size() != 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }

        LinkedList<T> reference = new LinkedList<>();

        for (int i = 0; i < 2500; i++) {
            T element = oracle.nextT();
            int idx = random.nextInt(reference.size() + 1);
            reference.add(idx, element);
            testList.add(idx, element);

            if (testList.isEmpty() ||  testList.size() != reference.size()) {
                return true;
            }
        }
        return false;
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

        try {
            testList.add(-1, oracle.nextT());
            return true;
        } catch (IndexOutOfBoundsException e) {
            if (!testList.isEmpty() ||  testList.size() != 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }

        try {
            testList.get(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        } catch (Exception e) {
            return true;
        }

        LinkedList<T> reference = new LinkedList<>();

        for (int i = 0; i < 2500; i++) {
            T element = oracle.nextT();
            int operation = random.nextInt(3);
            switch (operation) {
                case 0:
                    reference.add(element);
                    testList.append(element);
                    break;
                case 1:
                    reference.addFirst(element);
                    testList.prepend(element);
                    break;
                case 2:
                    int idx = random.nextInt(reference.size() + 1);
                    reference.add(idx, element);
                    testList.add(idx, element);
                    break;
            }
            if (testList.isEmpty() ||  testList.size() != reference.size()) {
                return true;
            }

            try {
                testList.get(reference.size());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }

            if (i % 500 == 0 || i == 2499) {
                for (int j = 0; j < reference.size(); j++) {
                    T fromReference = reference.get(j);
                    T fromTestList;
                    try {
                        fromTestList = testList.get(j);
                    } catch (Exception e) {
                        return true;
                    }
                    if (!fromTestList.equals(fromReference)) {
                        return true;
                    }
                }
            }
        }
        return false;
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
        try {
            testList.get(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            if (!testList.isEmpty() ||  testList.size() != 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }

        try {
            testList.set(-1, oracle.nextT());
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        } catch (Exception e) {
            return true;
        }

        LinkedList<T> reference = new LinkedList<>();

        for (int i = 0; i < 2500; i++) {
            T element = oracle.nextT();;
            reference.add(element);
            testList.append(element);

            try {
                testList.get(reference.size());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }
            try {
                testList.set(reference.size(), oracle.nextT());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }

            int idx = random.nextInt(reference.size());
            T fromTestList;
            try {
                fromTestList = testList.get(idx);
                if (!fromTestList.equals(reference.get(idx))) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }

            T setElement;
            try {
                setElement = oracle.nextT();
                if (!testList.set(idx, setElement).equals(fromTestList)) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
            reference.set(idx, setElement);
            if (!testList.get(idx).equals(setElement)) {
                return true;
            }

            if (testList.isEmpty() || testList.size() != reference.size()) {
                return true;
            }
        }
        return false;
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
        try {
            testList.remove(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            if (!testList.isEmpty() ||  testList.size() != 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        try {
            testList.set(-1, oracle.nextT());
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        } catch (Exception e) {
            return true;
        }
        try {
            testList.get(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        } catch (Exception e) {
            return true;
        }

        LinkedList<T> reference = new LinkedList<>();
        for (int i = 0; i < 2500; i++) {
            T element = oracle.nextT();
            testList.append(element);
            reference.add(element);

            try {
                testList.get(reference.size());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }
            try {
                testList.set(reference.size(), oracle.nextT());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }

            try {
                testList.remove(reference.size());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }

            int idx = random.nextInt(reference.size());
            T fromTestList;
            try {
                fromTestList = testList.get(idx);
                if (!fromTestList.equals(reference.get(idx))) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }

            T setElement;
            try {
                setElement = oracle.nextT();
                if (!testList.set(idx, setElement).equals(fromTestList)) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
            reference.set(idx, setElement);

            if (!testList.get(idx).equals(setElement)) {
                return true;
            }

            if (random.nextInt(10) == 0) {
                try {
                    testList.remove(idx);
                } catch (Exception e) {
                    return true;
                }
                reference.remove(idx);
            }

            if (testList.isEmpty() != reference.isEmpty() || testList.size() != reference.size()) {
                return true;
            }
            if (i % 500 == 0 || i == 2499) {
                for (int j = 0; j < reference.size(); j++) {
                    T fromReference = reference.get(j);
                    T getFromTestList;
                    try {
                        getFromTestList = testList.get(j);
                    } catch (Exception e) {
                        return true;
                    }
                    if (!getFromTestList.equals(fromReference)) {
                        return true;
                    }
                }
            }
        }
        return false;
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
        if (testList.removeFirst(oracle.nextT())) {
            return true;
        }

        try {
            testList.set(-1, oracle.nextT());
            return true;
        } catch (IndexOutOfBoundsException e) {
            if (!testList.isEmpty() ||  testList.size() != 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }

        try {
            testList.get(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        } catch (Exception e) {
            return true;
        }

        LinkedList<T> reference = new LinkedList<>();
        T neverInserted = oracle.nextT();

        for (int i = 0; i < 2500; i++) {
            T element = oracle.nextT();
            while (element.equals(neverInserted)) {
                element = oracle.nextT();
            }
            testList.append(element);
            reference.add(element);

            try {
                testList.get(reference.size());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }
            try {
                testList.set(reference.size(), oracle.nextT());
                return true;
            } catch (IndexOutOfBoundsException e) {
                // correct
            } catch (Exception e) {
                return true;
            }

            if (testList.removeFirst(neverInserted)) {
                return true;
            }

            int idx = random.nextInt(reference.size());
            T fromTestList;
            try {
                fromTestList = testList.get(idx);
                if (!fromTestList.equals(reference.get(idx))) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }

            T setElement;
            try {
                setElement = oracle.nextT();
                if (!testList.set(idx, setElement).equals(fromTestList)) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
            reference.set(idx, setElement);

            if (!testList.get(idx).equals(setElement)) {
                return true;
            }

            // do the test remove first success here
            if (random.nextInt(10) == 0) {
                if (testList.removeFirst(setElement)) {
                    reference.remove(setElement);
                } else {
                    return true;
                }
            }

            if (testList.isEmpty() != reference.isEmpty() || testList.size() != reference.size()) {
                return true;
            }

            if (i % 500 == 0 || i == 2499) {
                for (int j = 0; j < reference.size(); j++) {
                    T fromReference = reference.get(j);
                    T getFromTestList;
                    try {
                        getFromTestList = testList.get(j);
                    } catch (Exception e) {
                        return true;
                    }
                    if (!getFromTestList.equals(fromReference)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the full set of list operations behaves incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAllMethods(ListInterface<T> testList) {
        boolean result = false;

        result |= hasBugsAppend(testList);
        testList.clear();
        result |= hasBugsPrepend(testList);
        testList.clear();
        result |= hasBugsAdd(testList);
        testList.clear();
        result |= hasBugsAppendPrependAddGet(testList);
        testList.clear();
        result |= hasBugsAppendGetSet(testList);
        testList.clear();
        result |= hasBugsAppendGetSetRemove(testList);
        testList.clear();
        result |= hasBugsAppendGetSetRemoveFirst(testList);

        return result;
    }

}
