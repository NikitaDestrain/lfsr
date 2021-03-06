package com.lfsr.runner.lfsr;

import com.lfsr.combined.A51;
import com.lfsr.combined.CombinedLFSR;
import com.lfsr.util.FileUtils;

public class Main {

    private static final int PSEUDO_RANDOM_SEQUENCE_SIZE = 1000000;
    private static final int TEST_COUNT = 1;

    private static void printPseudoRandomValue(byte[] pseudoRandomValue) {
        for (int i = 0; i < pseudoRandomValue.length; i++) {
            System.out.print(pseudoRandomValue[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FileUtils fu = FileUtils.getInstance();
        CombinedLFSR combinedLFSR = CombinedLFSR.getInstance();
        A51 a51 = A51.getInstance();

        for (int i = 0; i < TEST_COUNT; i++) {
            long startTime = System.currentTimeMillis();
            //byte[] pseudoRandomValue = combinedLFSR.getPseudoRandomSequence(PSEUDO_RANDOM_SEQUENCE_SIZE);
            byte[] pseudoRandomValue = a51.getPseudoRandomSequence(PSEUDO_RANDOM_SEQUENCE_SIZE);
            //System.out.println("Pseudo random value " + i + ":");
            //printPseudoRandomValue(pseudoRandomValue);
            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println("time: " + timeSpent + " ms");
            System.out.println();

            fu.writeBytesToFile(Constants.RESOURCE_PATH, Constants.LFSR_FILE_NAME, pseudoRandomValue);
        }
    }
}
