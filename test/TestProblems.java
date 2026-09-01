/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import uq.comp3506.a1.Problems;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TestProblems {

    // The series of tests that need to be implemented
    public static void testSumOdd() {
        System.out.println("Testing 'Sum Odd Numbers'");
        long[] test1 = {6, 4, 9};
        long ex1 = 12;
        long actual = Problems.sumOddNumbers(test1);
        boolean pass = ex1 == actual;
        System.out.println("T1: Ex = " + ex1 + ", Actual = " + actual + "\nPass?: " + pass);

        long[] test2 = {1, 2, 3, 4, 5, 6};
        long ex2 = 0;
        long actual2 = Problems.sumOddNumbers(test2);
        boolean pass2 = ex2 == actual2;
        System.out.println("T1: Ex = " + ex2 + ", Actual = " + actual2 + "\nPass?: " + pass2);

        long[] test3 = {5};
        long ex3 = 0;
        long actual3 = Problems.sumOddNumbers(test3);
        boolean pass3 = ex2 == actual2;
        System.out.println("T1: Ex = " + ex3 + ", Actual = " + actual3 + "\nPass?: " + pass3);

    }

    public static void testToBeXOR() {
        System.out.println("Testing 'To be XOR NOT to be'");
    }

    public static void testStopStalling() {
        System.out.println("Testing 'Stop Stalling'");
    }

    public static void testRivalDealer() {
        System.out.println("Testing 'Rival Dealer Revealer'");
    }

    // Try to call the given test based on the input
    public static void dispatch(String str) {
        switch (str.toLowerCase()) {
            case "sumodd": 
                testSumOdd();
                return;
            case "xor":
                testToBeXOR();
                return;
            case "stalls":
                testStopStalling();
                return;
            case "rival":
                testRivalDealer();
                return;
            default:
                throw new IllegalArgumentException("Unknown command: " + str);
        }
    }

    // Does what it says on the tin 
    private static void usage() {
        System.out.println("Usage: java TestProblems <commands>");
        System.out.println("Commands:");
        System.out.println("  sumodd");
        System.out.println("  xor");
        System.out.println("  stalls");
        System.out.println("  rival");
    }

    public static void main(String[] args) {
        
        // Basic checking - make sure a command is provided
        if (args.length == 0) {
            usage();
            return;
        }

        // Walk the commands and try to dispatch them
        for (int i = 0; i < args.length; ++i) {
            dispatch(args[i]);
        }

        // profit??
    }

}
