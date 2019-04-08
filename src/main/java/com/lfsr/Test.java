package com.lfsr;

import com.lfsr.tester.MaurerTest;
import com.lfsr.util.FileReaderUtils;

public class Test {

    public static void main(String[] args) {
        MaurerTest mt = MaurerTest.getInstance();
        FileReaderUtils fru = FileReaderUtils.getInstance();

        byte[] e = fru.readFileToByteArray(Constants.RESOURCE_PATH, Constants.E_FILE_NAME);
        byte[] pi = fru.readFileToByteArray(Constants.RESOURCE_PATH, Constants.PI_FILE_NAME);

        byte[] test = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1};
        mt.test(2, 4, test.length, test);
    }
}

