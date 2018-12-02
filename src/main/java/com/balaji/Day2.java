package com.balaji;

import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        String[] input = new String[1000];
        int count = 0;
        while (!(line = scanner.nextLine()).equals("")) {
            input[count++] = line;
        }
        part1(input, count);
        part2(input, count);
    }

    private static void part1(String[] input, int items) {
        int twoTimes = 0, threeTimes = 0;
        for (int i = 0; i < items; i++) {
            String str = input[i];
            int[] chars = new int[26];
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                chars[c - 'a']++;
            }

            boolean two = false, three = false;
            for (int count : chars) {
                if (count == 2) {
                    two = true;
                }

                if (count == 3) {
                    three = true;
                }
            }
            if (two) twoTimes++;
            if (three) threeTimes++;
        }
        System.out.println(twoTimes * threeTimes);
    }

    private static void part2(String[] input, int items) {
        for (int i = 0; i < items - 1; i++) {
            String rhs = input[i];
            for (int j = i + 1; j < items; j++) {
                String lhs = input[j];
                int total = 0;
                for (int a = 0; a < rhs.length(); a++) {
                    if (rhs.charAt(a) != lhs.charAt(a)) {
                        total++;
                    }
                }
                if (total == 1) {
                    for (int a = 0; a < rhs.length(); a++) {
                        if (rhs.charAt(a) == lhs.charAt(a)) {
                            System.out.print(rhs.charAt(a));
                        }
                    }
                    return;
                }
            }
        }
    }
}
