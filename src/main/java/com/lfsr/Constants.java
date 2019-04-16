package com.lfsr;

public class Constants {

    public static final byte[] FIRST_SEED = {1, 0, 1, 1, 0};
    public static final byte[] SECOND_SEED = {1, 0, 0, 1, 1};
    public static final byte[] THIRD_SEED = {1, 0, 0, 1, 0};

    public static final byte[] FIRST_POLYNOMIAL = {1, 0, 0, 1, 0, 1};
    public static final byte[] SECOND_POLYNOMIAL = {1, 0, 0, 0, 0, 1};
    public static final byte[] THIRD_POLYNOMIAL = {1, 1, 0, 1, 0, 1};

    public static final byte[] TEST_SEED = {1, 0, 1, 1, 0, 1, 0, 1, 1, 0};
    public static final byte[] TEST_POLYNOMIAL = {1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0};

    public static final int FIRST_EXTRA_INDEX = 3;
    public static final int SECOND_EXTRA_INDEX = 0;
    public static final int THIRD_EXTRA_INDEX = 1;


    // lfsr A5/1
    public static final byte[] A5_1_FIRST_SEED = {
            1, 0, 1, 0,
            1, 0, 1, 0,
            1, 0, 1, 0,
            1, 0, 1, 0,
            1, 0, 1
    };
    public static final byte[] A5_1_SECOND_SEED = {
            0, 1, 0, 0,
            0, 1, 0, 1,
            0, 1, 1, 1,
            0, 1, 0, 1,
            0, 1, 0, 1,
            0, 1
    };
    public static final byte[] A5_1_THIRD_SEED = {
            1, 1, 1, 0,
            0, 1, 1, 0,
            0, 1, 1, 1,
            0, 1, 1, 1,
            0, 0, 1, 0,
            1, 1, 0
    };

    // x19 + x18 + x17 + x14 + 1
    public static final byte[] A5_1_FIRST_POLYNOMIAL = {
            1, 1, 1, 0,
            0, 1, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 1
    };
    // x22 + x21 + 1
    public static final byte[] A5_1_SECOND_POLYNOMIAL = {
            1, 1, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 1
    };
    // x23 + x22 + x21 + x8 + 1
    public static final byte[] A5_1_THIRD_POLYNOMIAL = {
            1, 1, 1, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 1,
            0, 0, 0, 0,
            0, 0, 0, 1
    };

    public static final int A5_1_FIRST_EXTRA_INDEX = 8;
    public static final int A5_1_SECOND_EXTRA_INDEX = 10;
    public static final int A5_1_THIRD_EXTRA_INDEX = 10;

    // for test
    public static final String RESOURCE_PATH = "resources/";
    public static final String E_FILE_NAME = "data.e";
    public static final String PI_FILE_NAME = "data.pi";
    public static final String LFSR_FILE_NAME = "data.lfsr";
}
