package com.balaji;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day6 {

  public static void main(String[] args) throws IOException {
    try (BufferedReader stream = new BufferedReader(new FileReader("day6.txt"))) {
      String line;
      int LIMIT = 360;
      int[][] ids = new int[LIMIT][LIMIT];
      int[][] dis = new int[LIMIT][LIMIT];
      for (int i = 0; i < dis.length; i++) {
        Arrays.fill(dis[i], Integer.MAX_VALUE);
        Arrays.fill(ids[i], -1);
      }
      int count = 0;
      int x = 0, y = 0;
      final List<AbstractMap.SimpleEntry<Integer, Integer>> entries = new ArrayList<>();
      while ((line = stream.readLine()) != null) {
        String[] s = line.split(", ");
        final int a = Integer.parseInt(s[0]);
        final int b = Integer.parseInt(s[1]);
        dis[a][b] = 0;
        ids[a][b] = count++;
        if (a > x) {
          x = a;
        }
        if (b > y) {
          y = b;
        }
        entries.add(new AbstractMap.SimpleEntry<>(a, b));
      }

      part1(entries, ids, dis, x, y);
    }
  }

  private static void part1(List<AbstractMap.SimpleEntry<Integer, Integer>> entries, int[][] ids, int[][] dis, int x, int y) {
    int c = 0;
    for (int i = 0; i <= x + 1; i++) {
      for (int j = 0; j <= y + 1; j++) {
        int totalDis = 0;
        for (int z = 0; z < entries.size(); z++) {
          AbstractMap.SimpleEntry<Integer, Integer> entry = entries.get(z);
          int d = Math.abs(entry.getKey() - i) + Math.abs(entry.getValue() - j);
          totalDis += d;
          if (dis[i][j] > d) {
            dis[i][j] = d;
            ids[i][j] = z;
          } else if (dis[i][j] == d && ids[i][j] != z) {
            ids[i][j] = -1;
          }
        }
        if (totalDis < 10000) {
          c++;
        }
      }
    }
    System.out.println(c); //part 2

    final Map<Integer, Integer> nearest = new HashMap<>();
    final Set<Integer> infinites = new HashSet<>();
    for (int i = 0; i <= x + 1; i++) {
      for (int j = 0; j <= y + 1; j++) {
        nearest.put(ids[i][j], nearest.getOrDefault(ids[i][j], 0) + 1);
        if (j == 0 || i == 0 || j == y + 1 || i == x + 1) {
          infinites.add(ids[i][j]);
        }
      }
    }

    System.out.println(nearest.entrySet().stream()
        .filter(entry -> !infinites.contains(entry.getKey()))
        .filter(entry -> entry.getKey() != -1)
        .max(Comparator.comparing(Map.Entry::getValue))
        .map(Map.Entry::getValue));
  }
}
