package com.lfsr;

public class Main {

    private static final int PSEUDO_RANDOM_SEQUENCE_SIZE = 1000;
    private static final int TEST_COUNT = 3;

    private static void printPseudoRandomValue(byte[] pseudoRandomValue) {
        for (int i = 0; i < pseudoRandomValue.length; i++) {
            System.out.print(pseudoRandomValue[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CombinedLFSR combinedLFSR = CombinedLFSR.getInstance();

        for (int i = 0; i < TEST_COUNT; i++) {
            long startTime = System.currentTimeMillis();
            byte[] pseudoRandomValue = combinedLFSR.getPseudoRandomSequence(PSEUDO_RANDOM_SEQUENCE_SIZE);
            System.out.println("Pseudo random value " + i + ":");
            printPseudoRandomValue(pseudoRandomValue);
            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println("time: " + timeSpent + " ms");
            System.out.println();
        }
    }
}
