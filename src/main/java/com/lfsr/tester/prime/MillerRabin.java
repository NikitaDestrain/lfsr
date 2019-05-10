package com.lfsr.tester.prime;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

public class MillerRabin {
    // k - count of rounds
    public boolean test(BigInteger testValue, int k) {
        Random rnd = ThreadLocalRandom.current();

        // Find a and m such that m is odd and this == 1 + 2**s * t
        BigInteger thisMinusOne = testValue.subtract(ONE);
        BigInteger t = thisMinusOne;
        int s = t.getLowestSetBit();
        t = t.shiftRight(s);

        // Do the tests
        for (int i = 0; i < k; i++) {
            // Generate a uniform random on (1, this)
            BigInteger a;
            do {
                a = new BigInteger(testValue.bitLength(), rnd);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(testValue) >= 0);
            System.out.println("MR iteration " + i + " random value from [1, n-1]: " + a);

            int j = 0;
            BigInteger z = a.modPow(t, testValue);
            while (!((j == 0 && z.equals(ONE)) || z.equals(thisMinusOne))) {
                if (j > 0 && z.equals(ONE) || ++j == s) {
                    System.out.println("Miller Rabin test: failed");
                    return false;
                }
                z = z.modPow(TWO, testValue);
            }
        }
        System.out.println("Miller Rabin test: success");
        return true;
    }
}
