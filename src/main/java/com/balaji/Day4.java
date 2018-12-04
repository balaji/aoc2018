package com.balaji;

import java.util.Arrays;
import java.util.Scanner;
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

    final String[][] data = new String[count][62];
    for (String[] datum : data) {
      Arrays.fill(datum, "");
    }

    int i = -1, idx = -1;
    String id;
    while (i < count - 1) {
      if (input[++i].contains("Guard")) {
        id = input[i].split(" ")[3];
        data[++idx][0] = id;
      }

      do {
        String sleep = input[++i];
        String wakes = input[++i];

        String sleepTime = sleep.substring(1, sleep.indexOf(']'));
        String wakeUpTime = wakes.substring(1, wakes.indexOf(']'));

        data[idx][1] = String.format("%s", sleepTime.split(" ")[0].substring(5));

        final int v = idx;
        IntStream.range(minutes(sleepTime), minutes(wakeUpTime))
            .forEach(t -> data[v][t] = "#");
      } while (i < count - 1 && !input[i + 1].contains("Guard"));
    }

    for (String[] datum : data) {
      System.out.println(Arrays.toString(datum));
    }
  }

  private static int minutes(String sleepTime) {
    return Integer.parseInt(sleepTime.split(" ")[1].split(":")[1]);
  }
}
