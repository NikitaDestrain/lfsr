package com.lfsr.runner.prime;

import com.lfsr.combined.A51;
import com.lfsr.tester.prime.MillerRabin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MillerRabinPrimeNumberRunner {

    private static A51 a51;
    private static Random random;

    public static void main(String[] args) {
        a51 = A51.getInstance();
        random = new Random();

        Scanner scanner = new Scanner(System.in);
        SimpleTests simpleTests = SimpleTests.getInstance();
        MillerRabin millerRabin = new MillerRabin();

        System.out.println("Prime number generator");
        System.out.println("Test: Miller Rabin");
        //System.out.println("Pseudo random generator: LFSR A5/1");
        System.out.println();
        System.out.println("Please, provide next required parameters:");
        System.out.print("p (count of bits): ");
        int p = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Use hard 2000 prime test? (y/n): ");
        String yN = scanner.nextLine();
        boolean is2000 = yN.equalsIgnoreCase("y");
        System.out.print("k (count of rounds for Miller Rabin): ");
        int k = scanner.nextInt();
        System.out.print("count of prime numbers: ");
        int c = scanner.nextInt();

        System.out.println();

        List<BigInteger> list = new ArrayList<>(c);
        int counter = 0;

        // time
        long start = System.currentTimeMillis();

        // generate c values
        while (counter < c) {
            boolean isSimpleTestFine = false;
            boolean isMillerRabinFine = false;
            BigInteger pseudo = null;

            // test time
            while (!isMillerRabinFine) {
                while (!isSimpleTestFine) {
                    pseudo = generateValue(p);
                    if (simpleTests.defaultTest(pseudo, is2000)) {
                        isSimpleTestFine = true;
                    }
                }
                if (millerRabin.test(pseudo, k)) {
                    isMillerRabinFine = true;
                } else {
                    isSimpleTestFine = false;
                }
            }
            boolean contCheck = false;
            for (BigInteger bi : list) {
                if (bi.equals(pseudo)) {
                    contCheck = true;
                    break;
                }
            }
            if (!contCheck) {
                list.add(pseudo);
                counter++;
            }

            System.out.println();
        }


        System.out.println("Result: ");
        for (BigInteger bi : list) {
            System.out.println(bi);
        }

        System.out.println();
        long finish = System.currentTimeMillis();
        System.out.println("time: " + (finish - start) + " ms");
    }

    private static BigInteger generateValue(int p) {
        System.out.print("Generating next pseudo random value of p bits...");
        BigInteger res = new BigInteger(p, random);
        while (res.equals(BigInteger.ZERO) || res.equals(BigInteger.ONE) || res.equals(BigInteger.TWO)) {
            res = new BigInteger(p, random);
        }
        System.out.println(" success");
        return res;
    }
}
