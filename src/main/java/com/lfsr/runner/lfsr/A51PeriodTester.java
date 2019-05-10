package com.lfsr.runner.lfsr;

import com.lfsr.domain.LFSR;
import com.lfsr.tester.pseudorandom.PeriodTester;

public class A51PeriodTester {
    public static void main(String[] args) {
        PeriodTester pt = PeriodTester.getInstance();
        System.out.println("---------------------FIRST_POLYNOMIAL-----------------------------");
        LFSR lfsr1 = new LFSR(Constants.FIRST_SEED, Constants.FIRST_POLYNOMIAL, 0, true);
        pt.periodForLFSR(lfsr1, Constants.FIRST_SEED);

        System.out.println("---------------------SECOND_POLYNOMIAL----------------------------");
        LFSR lfsr2 = new LFSR(Constants.SECOND_SEED, Constants.SECOND_POLYNOMIAL, 0, true);
        pt.periodForLFSR(lfsr2, Constants.SECOND_SEED);

        System.out.println("---------------------THIRD_POLYNOMIAL-----------------------------");
        LFSR lfsr3 = new LFSR(Constants.THIRD_SEED, Constants.THIRD_POLYNOMIAL, 0, true);
        pt.periodForLFSR(lfsr3, Constants.THIRD_SEED);

        System.out.println("----------------------------A5_1----------------------------------");
        long startTime = System.currentTimeMillis();
        pt.periodForA51();
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("time: " + timeSpent + " ms");
    }
}
