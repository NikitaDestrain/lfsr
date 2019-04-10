package com.lfsr.tester;

import static org.apache.commons.math3.special.Erf.erfc;
import static org.apache.commons.math3.util.FastMath.*;

public class UniversalMaurerTest {

    private static UniversalMaurerTest instance;
    private static double ALPHA = 0.01;

    private UniversalMaurerTest() {
    }

    public static UniversalMaurerTest getInstance() {
        if (instance == null) instance = new UniversalMaurerTest();
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
        return log(x) / log(2);
    }

    public boolean test(int n, byte[] E) {
        int L = 5;
        if (n >= 387840) L = 6;
        if (n >= 904960) L = 7;
        if (n >= 2068480) L = 8;
        if (n >= 4654080) L = 9;
        if (n >= 10342400) L = 10;
        if (n >= 22753280) L = 11;
        if (n >= 49643520) L = 12;
        if (n >= 107560960) L = 13;
        if (n >= 231669760) L = 14;
        if (n >= 496435200) L = 15;
        if (n >= 1059061760) L = 16;

        double expectedValue[] = {
                0.7326495,
                1.5374383,
                2.4016068,
                3.3112247,
                4.2534266,
                5.2177052,
                6.1962507,
                7.1836656,
                8.1764248,
                9.1723243,
                10.170032,
                11.168765,
                12.168070,
                13.167693,
                14.167488,
                15.167379
        };
        double variance[] = {
                0.69,
                1.338,
                1.901,
                2.358,
                2.705,
                2.954,
                3.125,
                3.238,
                3.311,
                3.356,
                3.384,
                3.401,
                3.410,
                3.416,
                3.419,
                3.421
        };

        int Q = (int) (10 * pow(2, L));
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
            T[0][decimal] = blockCounter;
        }

        // count sum
        double sum = 0.0;
        int tCounter = 0;
        int testLength = initLength + (L * K);
        byte[] cont = new byte[L];

        for (int i = initLength; i < testLength; i += L) {
            blockCounter++;
            System.arraycopy(E, i, cont, 0, L);
            int decimal = convertToDecimalInt(cont);
            int prevT = T[tCounter++][decimal];
            sum += binlog(blockCounter - prevT);
            // copy
            for (int j = 0; j < possibleCnt; j++) {
                T[tCounter][j] = T[tCounter - 1][j];
            }
            T[tCounter][decimal] = blockCounter;
        }

        double fn = sum / K;
        double c = 0.7 - (0.8 / (double) L) + (4 + (32 / (double) L)) * (pow(K, -3 / (double) L) / 15);
        double sigma = c * sqrt(variance[L - 1] / (double) K);
        double erfc = erfc(abs((fn - expectedValue[L - 1]) / (sqrt(2.0) * sigma)));
        boolean isRandom = erfc > ALPHA;

        System.out.println("\tsum: " + sum);
        System.out.println("\tfn: " + fn);
        System.out.println("\tc: " + c);
        System.out.println("\tsigma: " + sigma);
        System.out.println("\tp-value: " + erfc);
        System.out.println("\tresult: " + (isRandom ? "random" : "non-random"));
        return isRandom;
    }
}
