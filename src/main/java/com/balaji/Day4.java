package com.balaji;

import java.util.*;
import java.util.stream.IntStream;

public class Day4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        String[] input = new String[1200];
        int count = 0;
        while (!(line = scanner.nextLine()).equals("")) {
            input[count++] = line;
        }
        Arrays.sort(input, 0, count);

        int i = -1;
        Map<String, Integer[]> att2 = new HashMap<>();
        while (i < count - 1) {
            String id = null;
            if (input[++i].contains("Guard")) {
                if (input[i + 1].contains("Guard")) continue;
                id = input[i].split(" ")[3].substring(1);
                att2.putIfAbsent(id, newArray());
            }

            do {
                String sleep = input[++i];
                String wakes = input[++i];

                String finalId = id;
                IntStream.range(minutes(sleep.substring(1, sleep.indexOf(']'))),
                                minutes(wakes.substring(1, wakes.indexOf(']'))))
                        .forEach(t -> att2.get(finalId)[t]++);
            } while (i < count - 1 && !input[i + 1].contains("Guard"));
        }

        long max = 0;
        int maxIndex = 0;
        String id2 = null;
        for (Map.Entry<String, Integer[]> stringEntry : att2.entrySet()) {
            //part 1
            // long c = Arrays.stream(stringEntry.getValue()).filter(h -> h != 0).mapToInt(Integer::intValue).sum();
            // part 2
            long c = Arrays.stream(stringEntry.getValue()).max(Comparator.naturalOrder()).get();
            if (max < c) {
                max = c;
                id2 = stringEntry.getKey();
                List<Integer> list = Arrays.asList(stringEntry.getValue());
                maxIndex = list.stream().max(Comparator.naturalOrder())
                        .map(list::indexOf).orElse(-1);
            }
        }
        System.out.println(Integer.parseInt(id2) * maxIndex);
    }

    private static Integer[] newArray() {
        Integer[] integers = new Integer[60];
        Arrays.fill(integers, 0);
        return integers;
    }

    private static int minutes(String sleepTime) {
        return Integer.parseInt(sleepTime.split(" ")[1].split(":")[1]);
    }
}
