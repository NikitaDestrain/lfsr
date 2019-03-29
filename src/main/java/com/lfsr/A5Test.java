package com.lfsr;

import com.lfsr.domain.LFSR;

public class A5Test {
    public static void main(String[] args) {

        LFSR lfsr = new LFSR(Constants.A5_1_FIRST_SEED,
                Constants.A5_1_FIRST_POLYNOMIAL,
                Constants.A5_1_FIRST_EXTRA_INDEX,
                true);

        for (int i = 0; i < 100; i++) {
            System.out.print(lfsr.getNextValue() + " ");
            if (i % 4 == 0 && i != 0) {
                System.out.println();
            }
        }
    }
}
