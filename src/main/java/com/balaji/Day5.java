package com.balaji;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Day5 {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader stream = new BufferedReader(new FileReader("day5.txt"))) {
            builder.append(stream.readLine());

            ArrayList<Integer> r = new ArrayList<>();
            for (int i = 'A'; i <= 'Z'; i++) {
                String original = builder.toString();
                r.add(part1(original
                                .replaceAll(String.valueOf((char) i), "")
                                .replaceAll(String.valueOf((char) (i + 32)), "")
                        , true));

            }
            System.out.println(r.stream().min(Comparator.naturalOrder()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int part1(String input, boolean fix) {
        if (!fix) {
            return input.length();
        }
        boolean change = false;
        StringBuilder builder = new StringBuilder();
        builder.append(input.charAt(0));
        char[] charArray = input.toCharArray();
        int i = 1;
        while (i < charArray.length) {
            char a = charArray[i];

            if (builder.length() > 0 && Math.abs(a - builder.charAt(builder.length() - 1)) == 32) {
                builder.deleteCharAt(builder.length() - 1);
                change = true;
            } else {
                builder.append(a);
            }
            i++;
        }

        return part1(builder.toString(), change);
    }
}
