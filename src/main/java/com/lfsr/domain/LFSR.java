package com.lfsr.domain;

public class LFSR {

    private Cell[] sequence;
    private int size;
    private int lastIndex;
    private int extraValueIndex;
    private byte currentValue;
    private boolean state;

    public LFSR(byte[] seed, byte[] polynomialCoefficients, int extraIndex, boolean initState) {
        this.size = seed.length;
        this.lastIndex = size - 1;
        this.extraValueIndex = extraIndex;
        this.sequence = new Cell[size];
        for (int i = 0; i < size; i++) {
            sequence[i] = new Cell(seed[i], polynomialCoefficients[i + 1] == 1);
        }
        this.currentValue = sequence[lastIndex].getValue();
        this.state = initState;
    }

    public boolean isStopped() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    // for debug
    private void printState() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(sequence[i].getValue());
            sb.append(" ");
        }
        System.out.println(state + "\n" + sb.toString());
    }

    private void singleStep() {
        this.currentValue = sequence[lastIndex].getValue();

        byte accum = 0;
        byte graybeard = 0;
        for (int i = 0; i < size; i++) {
            Cell curCell = sequence[i];
            byte cellValue = curCell.getValue();

            if (curCell.getFilter()) {
                accum ^= cellValue;
            }
            if (i != 0) {
                curCell.setValue(graybeard);
            }

            graybeard = cellValue;
        }
        sequence[0].setValue(accum);
    }

    public byte getNextValue() {
        //printState();
        if (state) {
            singleStep();
        }
        //printState();
        return currentValue;
    }

    public byte getCurrentValue() {
        return currentValue;
    }

    public byte getExtraValue() {
        return sequence[extraValueIndex].getValue();
    }
}
