package com.lfsr.tester;

import com.lfsr.domain.LFSR;

public class PeriodTester {

    private static PeriodTester instance;

    private PeriodTester() {
    }

    public static PeriodTester getInstance() {
        if (instance == null) instance = new PeriodTester();
        return instance;
    }

    // can be refactored
    private int periodExpert(byte[] testSequence, byte[] periodValue) {
        int tmpRunner = 0;
        int endFlag = periodValue.length - 1;
        boolean isFirstPeriodIn = true;
        boolean isFirst = true;
        int start = 0;
        int end = 0;
        for (int i = 0; i < testSequence.length; i++) {
            if (testSequence[i] == periodValue[tmpRunner]) {
                if (isFirst) {
                    end = i;
                    isFirst = false;
                }
                if (tmpRunner == endFlag) {
                    if (isFirstPeriodIn) {
                        start = i - endFlag;
                        isFirstPeriodIn = false;
                    } else {
                        break;
                    }
                }
                if (tmpRunner < endFlag) {
                    tmpRunner++;
                } else {
                    tmpRunner = 0;
                }
            } else {
                isFirst = true;
                tmpRunner = 0;
            }
        }
        return end - start;
    }

    // can be change to StringBuffer
    private void printHumanReadable(byte[] seed, byte[] periodValue, int theoreticalPeriod, int practicePeriod) {
        System.out.print("Seed: ");
        for (int i = 0; i < seed.length; i++) {
            System.out.print(seed[i]);
        }
        System.out.println();
        System.out.print("Check value: ");
        for (int i = 0; i < periodValue.length; i++) {
            System.out.print(periodValue[i]);
        }
        System.out.println();
        System.out.println("Theoretical period: " + theoreticalPeriod);
        System.out.println("Practice period: " + practicePeriod);
        System.out.println(practicePeriod == theoreticalPeriod ? "Polynomial is primitive" : "Polynomial is not primitive");
    }

    public boolean polynomialPrimitiveTestByPeriod(byte[] seed, byte[] polynomial, boolean printResult) {
        LFSR lfsr = new LFSR(seed, polynomial, 0, true);
        int registerCount = seed.length;
        int theoreticalPeriod = (int) Math.pow(2, registerCount) - 1;
        int testSize = theoreticalPeriod * 4;
        
        byte[] testSequence = new byte[testSize];
        for (int i = 0; i < testSize; i++) {
            testSequence[i] = lfsr.getNextValue();
        }
        
        byte[] periodValue = new byte[registerCount];
        System.arraycopy(testSequence, theoreticalPeriod * 2, periodValue, 0, registerCount);
        int practicePeriod = periodExpert(testSequence, periodValue);
        if (printResult) {
            printHumanReadable(seed, periodValue, theoreticalPeriod, practicePeriod);
        }
        return practicePeriod == theoreticalPeriod;
    }
}
