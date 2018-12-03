package com.balaji;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day3 {
    private static String[][] fabric = new String[1000][1000];
    private static int count = 0;
    private static int[] clashes = new int[1294];
    private static Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

    public static void main(String[] args) {
        for (String[] strings : fabric) {
            Arrays.fill(strings, "0");
        }
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("")) {
            Matcher m = pattern.matcher(line);
            while (m.find()) {
                part1(m.group(1),
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)),
                        Integer.parseInt(m.group(4)),
                        Integer.parseInt(m.group(5))
                );
            }
        }
        int clash = IntStream.range(1, clashes.length)
                .filter(i -> clashes[i] == 0)
                .findFirst().orElse(-1);

        System.out.println(clash);
        System.out.println(count);
    }

    private static void part1(String id, int x, int y, int l, int b) {
        for (int i = x; i < x + l; i++) {
            for (int j = y; j < y + b; j++) {
                if (fabric[i][j].equals("0")) {
                    fabric[i][j] = id;
                } else {
                    if (!fabric[i][j].contains(",")) {
                        count++;
                    }
                    fabric[i][j] = fabric[i][j] + "," + id;
                    Arrays.stream(fabric[i][j].split(","))
                            .map(Integer::parseInt)
                            .forEach(k -> clashes[k] = 1);
                }
            }
        }
    }
}
