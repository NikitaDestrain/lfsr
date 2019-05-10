package com.lfsr.tester.pseudorandom;

public class InsufficientNumberOfCyclesException extends Exception {
    public InsufficientNumberOfCyclesException() {
        super();
    }

    public InsufficientNumberOfCyclesException(String message) {
        super(message);
    }
}
