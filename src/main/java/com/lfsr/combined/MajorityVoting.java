package com.lfsr.combined;

public class MajorityVoting implements IndependentExpert {

    private static MajorityVoting instance;

    private MajorityVoting() {
    }

    public static MajorityVoting getInstance() {
        if (instance == null) instance = new MajorityVoting();
        return instance;
    }

    public byte getTheWinner(byte[] participants) {
        byte zeroCnt = 0;
        byte oneCnt = 0;
        for (byte participant : participants) {
            if (participant == (byte) 0) {
                zeroCnt++;
            } else {
                oneCnt++;
            }
        }
        return zeroCnt > oneCnt ? (byte) 0 : (byte) 1;
    }
}
