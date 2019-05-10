package com.lfsr.runner.lfsr;

import com.lfsr.domain.LFSR;

public class PrimitiveLFSRGenerator {

    private static int RANDOM_SEQUENCE_SIZE = 1000000;

    public static void main(String[] args) {

        LFSR lfsr = new LFSR(Constants.A5_1_FIRST_SEED,
                Constants.A5_1_FIRST_POLYNOMIAL,
                Constants.A5_1_FIRST_EXTRA_INDEX,
                true);
    }
}
