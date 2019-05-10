package com.lfsr.tester.pseudorandom;

import static org.apache.commons.math3.special.Gamma.regularizedGammaP;
import static org.apache.commons.math3.util.FastMath.abs;
import static org.apache.commons.math3.util.FastMath.pow;

public class RandomExcursionsTest {
    private static RandomExcursionsTest instance;
    private static double ALPHA = 0.01;

    private RandomExcursionsTest() {
    }

    public static RandomExcursionsTest getInstance() {
        if (instance == null) instance = new RandomExcursionsTest();
        return instance;
    }

    public void test(int n, byte[] e) throws ExceedingTheMaxNumberOfCyclesExpectedException, InsufficientNumberOfCyclesException {
        int b, i, j, k, J, x;
        int cycleStart, cycleStop;
        int cycle[] = new int[n];
        int S_k[] = new int[n];
        int stateX[] = {-4, -3, -2, -1, 1, 2, 3, 4};
        int counter[] = {0, 0, 0, 0, 0, 0, 0, 0};
        double p_value = 0.0, sum, constraint;
        double nu[][] = new double[6][8];
        double pi[][] = {
                {0.0000000000, 0.00000000000, 0.00000000000, 0.00000000000, 0.00000000000, 0.0000000000},
                {0.5000000000, 0.25000000000, 0.12500000000, 0.06250000000, 0.03125000000, 0.0312500000},
                {0.7500000000, 0.06250000000, 0.04687500000, 0.03515625000, 0.02636718750, 0.0791015625},
                {0.8333333333, 0.02777777778, 0.02314814815, 0.01929012346, 0.01607510288, 0.0803755143},
                {0.8750000000, 0.01562500000, 0.01367187500, 0.01196289063, 0.01046752930, 0.0732727051}
        };

        J = 0;
        S_k[0] = 2 * (int) e[0] - 1;
        for (i = 1; i < n; i++) {
            S_k[i] = S_k[i - 1] + 2 * e[i] - 1;
            if (S_k[i] == 0) {
                J++;
                if (J > Math.max(1000, n / 100)) {
                    throw new ExceedingTheMaxNumberOfCyclesExpectedException("Exceeded the max number of cycles expected" +
                            "\n\t" + J + "grater than " + Math.max(1000, n / 100));
                }
                cycle[J] = i;
            }
        }
        if (S_k[n - 1] != 0)
            J++;
        cycle[J] = n;

        constraint = Math.max(0.005 * pow(n, 0.5), 500);
        if (J < constraint) {
            throw new InsufficientNumberOfCyclesException("Insufficient Number Of Cycles" +
                    "\n\tcycle count: " + J +
                    "\n\tminimal needed count: " + constraint);
        } else {
            cycleStart = 0;
            cycleStop = cycle[1];
            for (k = 0; k < 6; k++)
                for (i = 0; i < 8; i++)
                    nu[k][i] = 0.;
            for (j = 1; j <= J; j++) {
                for (i = 0; i < 8; i++)
                    counter[i] = 0;
                for (i = cycleStart; i < cycleStop; i++) {
                    if ((S_k[i] >= 1 && S_k[i] <= 4) || (S_k[i] >= -4 && S_k[i] <= -1)) {
                        if (S_k[i] < 0)
                            b = 4;
                        else
                            b = 3;
                        counter[S_k[i] + b]++;
                    }
                }
                cycleStart = cycle[j] + 1;
                if (j < J)
                    cycleStop = cycle[j + 1];

                for (i = 0; i < 8; i++) {
                    if ((counter[i] >= 0) && (counter[i] <= 4))
                        nu[counter[i]][i]++;
                    else if (counter[i] >= 5)
                        nu[5][i]++;
                }
            }

            for (i = 0; i < 8; i++) {
                x = stateX[i];
                sum = 0.;
                for (k = 0; k < 6; k++)
                    sum += pow(nu[k][i] - J * pi[(int) abs(x)][k], 2) / (J * pi[(int) abs(x)][k]);
                p_value = 1 - regularizedGammaP(2.5, sum / 2.0);

                boolean isRandom = p_value > ALPHA;
                System.out.println("\tx: " + x);
                System.out.println("\t\tchi2: " + sum);
                System.out.println("\t\tp-value: " + p_value);
                System.out.println("\t\tresult: " + (isRandom ? "random" : "non-random"));
            }
        }
    }
}
