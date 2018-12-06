package com.balaji;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day6 {
  public static void main(String[] args) {
    try (BufferedReader stream = new BufferedReader(new FileReader("day5.txt"))) {
      String line;
      List<AbstractMap.SimpleEntry<Integer, Integer>> inp = new ArrayList<>();
      while ((line = stream.readLine()) != null) {
        String[] s = line.split(",");
        inp.add(new AbstractMap.SimpleEntry<>(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
      }

      part1(inp);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void part1(List<AbstractMap.SimpleEntry<Integer, Integer>> inp) {
    int x = inp.stream().map(AbstractMap.SimpleEntry::getValue).max(Comparator.naturalOrder()).orElse(-1);
    int y = inp.stream().map(AbstractMap.SimpleEntry::getKey).max(Comparator.naturalOrder()).orElse(-1);

    int[][] output = new int[x][y];
    for (AbstractMap.SimpleEntry<Integer, Integer> entry : inp) {
      
    }
  }
}
