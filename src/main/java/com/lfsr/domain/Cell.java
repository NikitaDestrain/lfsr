package com.lfsr.domain;

public class Cell {

    private byte value;
    private boolean filter;

    public Cell(byte value, boolean filter) {
        this.value = value;
        this.filter = filter;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public boolean getFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }
}
