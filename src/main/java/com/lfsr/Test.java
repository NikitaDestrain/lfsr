package com.lfsr;

import com.lfsr.tester.*;
import com.lfsr.util.FileUtils;

public class Test {

    private static int RANDOM_SEQUENCE_SIZE = 1000000;
    private static int BLOCK_SIZE = 500;

    public static void main(String[] args) {
        UniversalMaurerTest mt = UniversalMaurerTest.getInstance();
        LinearComplexityTest lct = LinearComplexityTest.getInstance();
        RandomExcursionsTest ret = RandomExcursionsTest.getInstance();

        FileUtils fru = FileUtils.getInstance();

        byte[] lfsr = fru.readFileToByteArray(Constants.RESOURCE_PATH, Constants.LFSR_FILE_NAME);
        byte[] e = fru.readFileToByteArray(Constants.RESOURCE_PATH, Constants.E_FILE_NAME);
        byte[] pi = fru.readFileToByteArray(Constants.RESOURCE_PATH, Constants.PI_FILE_NAME);

        System.out.println("--------------------------------");
        System.out.println("Universal Maurer's Test");
        System.out.println("Test e");
        mt.test(RANDOM_SEQUENCE_SIZE, e);
        System.out.println();
        System.out.println("Test pi");
        mt.test(RANDOM_SEQUENCE_SIZE, pi);
        System.out.println();
        System.out.println("Test combined LFSR");
        mt.test(RANDOM_SEQUENCE_SIZE, lfsr);
        System.out.println("--------------------------------");
        System.out.println();

        System.out.println("--------------------------------");
        System.out.println("Linear Complexity Test");
        System.out.println("Test e");
        lct.test(BLOCK_SIZE, RANDOM_SEQUENCE_SIZE, e);
        System.out.println();
        System.out.println("Test pi");
        lct.test(BLOCK_SIZE, RANDOM_SEQUENCE_SIZE, pi);
        System.out.println();
        System.out.println("Test combined LFSR");
        lct.test(BLOCK_SIZE, RANDOM_SEQUENCE_SIZE, lfsr);
        System.out.println("--------------------------------");
        System.out.println();
        
        System.out.println("--------------------------------");
        System.out.println("Linear Complexity Test");
        System.out.println("Test e");
        try {
            ret.test(RANDOM_SEQUENCE_SIZE, e);
        } catch (ExceedingTheMaxNumberOfCyclesExpectedException | InsufficientNumberOfCyclesException ex) {
            System.out.println("\tERROR: " + ex.getMessage());
        }
        System.out.println();
        System.out.println("Test pi");
        try {
            ret.test(RANDOM_SEQUENCE_SIZE, pi);
        } catch (ExceedingTheMaxNumberOfCyclesExpectedException | InsufficientNumberOfCyclesException ex) {
            System.out.println("\tERROR: " + ex.getMessage());
        }
        System.out.println();
        System.out.println("Test combined LFSR");
        try {
            ret.test(RANDOM_SEQUENCE_SIZE, lfsr);
        } catch (ExceedingTheMaxNumberOfCyclesExpectedException | InsufficientNumberOfCyclesException ex) {
            System.out.println("\tERROR: " + ex.getMessage());
        }
        System.out.println("--------------------------------");
    }
}

