package com.balaji;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputs = new int[10000];
        int count = 0;
        while (scanner.hasNextInt()) {
            inputs[count++] = scanner.nextInt();
        }

        part1(inputs);
        part2(inputs, count);
    }

    private static void part1(int[] inputs) {
        System.out.println(Arrays.stream(inputs).sum());
    }

    private static void part2(int[] inputs, int count) {
        int i = 0;
        int total = 0;
        Set<Integer> inter = new HashSet<>();
        inter.add(0);
        while (true) {
            total += inputs[i++];
            if (inter.contains(total)) {
                break;
            }
            inter.add(total);
            if (count == i) {
                i = 0;
            }
        }

        System.out.println(total);
    }
}
