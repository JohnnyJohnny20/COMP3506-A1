/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import uq.comp3506.a1.Problems;

public class TestProblems {

    // The series of tests that need to be implemented
    public static void testSumOdd() {
        System.out.println("Testing 'Sum Odd Numbers'");
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
