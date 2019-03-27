package com.lfsr;

public class CombinedLFSR {

    private LFSR lfsr1;
    private LFSR lfsr2;
    private LFSR lfsr3;

    // do not use, cuz the same like independentExpert
    private IndependentExpert fStopExpert;
    private IndependentExpert independentExpert;
    private static CombinedLFSR instance;

    private CombinedLFSR() {
        // can be switch to another
        this.fStopExpert = MajorityVoting.getInstance();
        this.independentExpert = MajorityVoting.getInstance();

        this.lfsr1 = new LFSR(Constants.FIRST_SEED,
                Constants.FIRST_POLYNOMIAL,
                Constants.FIRST_RESULT_INDEX,
                true);
        this.lfsr2 = new LFSR(Constants.SECOND_SEED,
                Constants.SECOND_POLYNOMIAL,
                Constants.SECOND_RESULT_INDEX,
                true);
        this.lfsr3 = new LFSR(Constants.THIRD_SEED,
                Constants.THIRD_POLYNOMIAL,
                Constants.THIRD_RESULT_INDEX,
                true);
    }

    public static CombinedLFSR getInstance() {
        if (instance == null) instance = new CombinedLFSR();
        return instance;
    }

    private void fStopMajority(byte value) {
        lfsr1.setState(lfsr1.getCurrentValue() == value);
        lfsr2.setState(lfsr2.getCurrentValue() == value);
        lfsr3.setState(lfsr3.getCurrentValue() == value);
    }

    public byte[] getPseudoRandomSequence(int size) {
        byte[] result = new byte[size];

        for (int i = 0; i < size; i++) {
            byte[] pseudoRandomValues = {lfsr1.getNextValue(),
                    lfsr2.getNextValue(),
                    lfsr3.getNextValue()};

            byte winner = ((MajorityVoting) independentExpert).getTheWinner(pseudoRandomValues);
            fStopMajority(winner);
            result[i] = winner;
        }

        return result;
    }
}
