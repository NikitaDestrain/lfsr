package com.lfsr.tester;

import org.apache.commons.math3.special.Erf;

public class MaurerTest {

    private static MaurerTest instance;

    private MaurerTest() {
    }

    public static MaurerTest getInstance() {
        if (instance == null) instance = new MaurerTest();
        return instance;
    }

    private int convertToDecimalInt(byte[] value) {
        int res = 0;
        int size = value.length - 1;
        for (int i = size; i >= 0; i--) {
            int acc = 1;
            for (int j = 0; j < size - i; j++) {
                acc *= 2;
            }
            res += value[i] * acc;
        }
        return res;
    }

    private int countPossibleCnt(int L) {
        int res = 1;
        for (int i = 0; i < L; i++) {
            res *= 2;
        }
        return res;
    }

    private double binlog(int x) {
        return Math.log(x) / Math.log(2);
    }

    // L - seed
    public boolean test(int L, int Q, int n, byte[] E) {
        int K = n / L - Q;

        int possibleCnt = countPossibleCnt(L);

        int[][] T = new int[K + 1][possibleCnt];

        int initLength = L * Q;
        int blockCounter = 0;
        // init T
        for (int i = 0; i < initLength; i += L) {
            blockCounter++;
            byte[] cont = new byte[L];
            System.arraycopy(E, i, cont, 0, L);
            int decimal = convertToDecimalInt(cont);
            T[0][decimal] += decimal;
        }

        // count sum
        double sum = 0.0;
        int tCounter = 0;

        int kq = K + Q;
        for (int i = initLength; i < n; i += L) {
            blockCounter++;
            byte[] cont = new byte[L];
            System.arraycopy(E, i, cont, 0, L);
            for (byte c : cont) {
                System.out.print(c);
            }

            int decimal = convertToDecimalInt(cont);

            int prevT = T[tCounter++][decimal];
            System.out.print(" log(" + blockCounter + "-" + prevT + ") = ");
            sum += binlog(blockCounter - prevT);
            // copy
            for (int j = 0; j < possibleCnt; j++) {
                T[tCounter][j] = T[tCounter - 1][j];
            }
            T[tCounter][decimal] = blockCounter;
            System.out.println(sum);
            if (blockCounter == kq) {
                break;
            }
        }

        double fn = sum / K;


        System.out.println();
        System.out.println(fn);
        // research
        double erf = Erf.erf(Math.abs((6.194107 - 6.196251) / (Math.sqrt(2.0) * Math.sqrt(3.125))));
        System.out.println(erf);
        return false;
    }
}
