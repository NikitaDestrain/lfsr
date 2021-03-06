package com.lfsr.tester.pseudorandom;

import static org.apache.commons.math3.special.Gamma.regularizedGammaP;
import static org.apache.commons.math3.util.FastMath.pow;

public class LinearComplexityTest {
    private static LinearComplexityTest instance;
    private static double ALPHA = 0.01;

    private LinearComplexityTest() {
    }

    public static LinearComplexityTest getInstance() {
        if (instance == null) instance = new LinearComplexityTest();
        return instance;
    }

    public boolean test(int M, int n, byte[] e) {
        int i, ii, j, d, N, L, m, N_, parity, sign, K = 6;
        double p_value, T_, mean, nu[] = new double[7], chi2;
        double pi[] = {0.01047, 0.03125, 0.12500, 0.50000, 0.25000, 0.06250, 0.020833};
        int T[] = new int[M];
        int P[] = new int[M];
        int B_[] = new int[M];
        int C[] = new int[M];

        N = (int) (n / M);
        for (i = 0; i < K + 1; i++) {
            nu[i] = 0.00;
        }
        for (ii = 0; ii < N; ii++) {
            for (i = 0; i < M; i++) {
                B_[i] = 0;
                C[i] = 0;
                T[i] = 0;
                P[i] = 0;
            }
            L = 0;
            m = -1;
            d = 0;
            C[0] = 1;
            B_[0] = 1;

            N_ = 0;
            while (N_ < M) {
                d = (int) e[ii * M + N_];
                for (i = 1; i <= L; i++)
                    d += C[i] * e[ii * M + N_ - i];
                d = d % 2;
                if (d == 1) {
                    for (i = 0; i < M; i++) {
                        T[i] = C[i];
                        P[i] = 0;
                    }
                    for (j = 0; j < M; j++)
                        if (B_[j] == 1)
                            P[j + N_ - m] = 1;
                    for (i = 0; i < M; i++)
                        C[i] = (C[i] + P[i]) % 2;
                    if (L <= N_ / 2) {
                        L = N_ + 1 - L;
                        m = N_;
                        for (i = 0; i < M; i++)
                            B_[i] = T[i];
                    }
                }
                N_++;
            }
            if ((parity = (M + 1) % 2) == 0)
                sign = -1;
            else
                sign = 1;
            mean = M / 2.0 + (9.0 + sign) / 36.0 - 1.0 / pow(2, M) * (M / 3.0 + 2.0 / 9.0);
            if ((parity = M % 2) == 0) {
                sign = 1;
            } else {
                sign = -1;
            }
            T_ = sign * (L - mean) + 2.0 / 9.0;

            if (T_ <= -2.5)
                nu[0]++;
            else if (T_ > -2.5 && T_ <= -1.5)
                nu[1]++;
            else if (T_ > -1.5 && T_ <= -0.5)
                nu[2]++;
            else if (T_ > -0.5 && T_ <= 0.5)
                nu[3]++;
            else if (T_ > 0.5 && T_ <= 1.5)
                nu[4]++;
            else if (T_ > 1.5 && T_ <= 2.5)
                nu[5]++;
            else
                nu[6]++;
        }
        chi2 = 0.00;
        for (i = 0; i < K + 1; i++) {
            System.out.println("\tnu" + i + ": " + nu[i]);
        }
        for (i = 0; i < K + 1; i++) {
            chi2 += pow(nu[i] - N * pi[i], 2) / (N * pi[i]);
        }

        p_value = 1 - regularizedGammaP(K / 2.0, chi2 / 2.0);

        boolean isRandom = p_value > ALPHA;
        System.out.println("\tchi2: " + chi2);
        System.out.println("\tp-value: " + p_value);
        System.out.println("\tresult: " + (isRandom ? "random" : "non-random"));
        return isRandom;
    }
}
