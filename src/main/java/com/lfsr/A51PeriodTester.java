package com.lfsr;

import com.lfsr.domain.LFSR;
import com.lfsr.tester.PeriodTester;

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

        System.out.println("---------------------A5_1_FIRST_POLYNOMIAL------------------------");
        LFSR a511 = new LFSR(Constants.A5_1_FIRST_SEED, Constants.A5_1_FIRST_POLYNOMIAL, 0, true);
        pt.periodForLFSR(a511, Constants.A5_1_FIRST_SEED);

        System.out.println("---------------------A5_1_SECOND_POLYNOMIAL-----------------------");
        LFSR a512 = new LFSR(Constants.A5_1_SECOND_SEED, Constants.A5_1_SECOND_POLYNOMIAL, 0, true);
        pt.periodForLFSR(a512, Constants.A5_1_SECOND_SEED);

        System.out.println("---------------------A5_1_THIRD_POLYNOMIAL------------------------");
        LFSR a513 = new LFSR(Constants.A5_1_THIRD_SEED, Constants.A5_1_THIRD_POLYNOMIAL, 0, true);
        pt.periodForLFSR(a513, Constants.A5_1_THIRD_SEED);

        System.out.println("----------------------------A5_1----------------------------------");
        pt.periodForA51();
    }
}
