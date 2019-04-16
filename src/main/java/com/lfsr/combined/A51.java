package com.lfsr.combined;

import com.lfsr.Constants;
import com.lfsr.domain.LFSR;

public class A51 {

    private LFSR lfsr1;
    private LFSR lfsr2;
    private LFSR lfsr3;

    private static A51 instance;

    private A51() {
        this.lfsr1 = new LFSR(Constants.A5_1_FIRST_SEED,
                Constants.A5_1_FIRST_POLYNOMIAL,
                Constants.A5_1_FIRST_EXTRA_INDEX,
                true);
        this.lfsr2 = new LFSR(Constants.A5_1_SECOND_SEED,
                Constants.A5_1_SECOND_POLYNOMIAL,
                Constants.A5_1_SECOND_EXTRA_INDEX,
                true);
        this.lfsr3 = new LFSR(Constants.A5_1_THIRD_SEED,
                Constants.A5_1_THIRD_POLYNOMIAL,
                Constants.A5_1_THIRD_EXTRA_INDEX,
                true);
    }

    public static A51 getInstance() {
        if (instance == null) instance = new A51();
        return instance;
    }

    private void fStop(byte R1, byte R2, byte R3) {
        byte F = (byte) (R1 & R2 | R1 & R3 | R2 & R3);
        lfsr1.setState(lfsr1.getExtraValue() == F);
        lfsr2.setState(lfsr2.getExtraValue() == F);
        lfsr3.setState(lfsr3.getExtraValue() == F);
    }

    public byte[] getPseudoRandomSequence(int size) {
        byte[] result = new byte[size];

        for (int i = 0; i < size; i++) {
            result[i] = (byte) (lfsr1.getNextValue() ^ lfsr2.getNextValue() ^ lfsr3.getNextValue());
            fStop(lfsr1.getExtraValue(), lfsr2.getExtraValue(), lfsr3.getExtraValue());
        }

        return result;
    }

    public byte getPseudoRandomValue(){
        byte res = (byte) (lfsr1.getNextValue() ^ lfsr2.getNextValue() ^ lfsr3.getNextValue());
        fStop(lfsr1.getExtraValue(), lfsr2.getExtraValue(), lfsr3.getExtraValue());
        return res;
    }
}
