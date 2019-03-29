package com.lfsr;

import com.lfsr.tester.PeriodTester;

public class PeriodTesterRunner {

    public static void main(String[] args) {
        PeriodTester pt = PeriodTester.getInstance();
        System.out.println("---------------------FIRST_POLYNOMIAL-----------------------------");
        pt.polynomialPrimitiveTestByPeriod(Constants.FIRST_SEED, Constants.FIRST_POLYNOMIAL, true);
        System.out.println("---------------------SECOND_POLYNOMIAL----------------------------");
        pt.polynomialPrimitiveTestByPeriod(Constants.SECOND_SEED, Constants.SECOND_POLYNOMIAL, true);
        System.out.println("---------------------THIRD_POLYNOMIAL-----------------------------");
        pt.polynomialPrimitiveTestByPeriod(Constants.THIRD_SEED, Constants.THIRD_POLYNOMIAL, true);
        System.out.println("---------------------A5_1_FIRST_POLYNOMIAL------------------------");
        pt.polynomialPrimitiveTestByPeriod(Constants.A5_1_FIRST_SEED, Constants.A5_1_FIRST_POLYNOMIAL, true);
        System.out.println("---------------------A5_1_SECOND_POLYNOMIAL-----------------------");
        pt.polynomialPrimitiveTestByPeriod(Constants.A5_1_SECOND_SEED, Constants.A5_1_SECOND_POLYNOMIAL, true);
        System.out.println("---------------------A5_1_THIRD_POLYNOMIAL------------------------");
        pt.polynomialPrimitiveTestByPeriod(Constants.A5_1_THIRD_SEED, Constants.A5_1_THIRD_POLYNOMIAL, true);
    }
}
