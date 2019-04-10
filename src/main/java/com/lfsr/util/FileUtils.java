package com.lfsr.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static FileUtils instance;

    private FileUtils() {
    }

    public static FileUtils getInstance() {
        if (instance == null) instance = new FileUtils();
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

    public void writeBytesToFile(String path, String name, byte[] bytes) {
        try (FileWriter fw = new FileWriter(new File(path + name))) {
            for (byte b : bytes) {
                // WA cast to String
                fw.write(Byte.toString(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
