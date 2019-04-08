package com.lfsr.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {

    private static FileReaderUtils instance;

    private FileReaderUtils() {
    }

    public static FileReaderUtils getInstance() {
        if (instance == null) instance = new FileReaderUtils();
        return instance;
    }

    public byte[] readFileToByteArray(String path, String name) {
        List<Character> chars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path + name)))) {
            int c;
            while ((c = reader.read()) != -1) {
                chars.add((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int size = 0;
        for (char c : chars) {
            if (c == '1' || c == '0') {
                size++;
            }
        }
        byte[] res = new byte[size];
        int counter = 0;
        for (Character c : chars) {
            if (c == '1' || c == '0') {
                res[counter++] = Byte.parseByte(c.toString());
            }
        }
        return res;
    }
}
