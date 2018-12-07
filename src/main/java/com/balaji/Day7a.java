package com.balaji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Day7a {
  public static void main(String[] args) {
    Search search = new Search();
    search.insert('F','P');
    search.insert('R','J');
    search.insert('X','H');
    search.insert('L','N');
    search.insert('U','Z');
    search.insert('B','C');
    search.insert('S','C');
    search.insert('N','Y');
    search.insert('I','J');
    search.insert('H','K');
    search.insert('G','Z');
    search.insert('Q','V');
    search.insert('E','P');
    search.insert('P','W');
    search.insert('J','D');
    search.insert('V','W');
    search.insert('T','D');
    search.insert('Z','A');
    search.insert('K','A');
    search.insert('Y','O');
    search.insert('O','W');
    search.insert('C','M');
    search.insert('D','A');
    search.insert('W','M');
    search.insert('M','A');
    search.insert('C','A');
    search.insert('F','Z');
    search.insert('I','A');
    search.insert('W','A');
    search.insert('T','C');
    search.insert('S','K');
    search.insert('B','J');
    search.insert('O','A');
    search.insert('Q','P');
    search.insert('G','M');
    search.insert('R','T');
    search.insert('B','G');
    search.insert('J','O');
    search.insert('X','E');
    search.insert('X','C');
    search.insert('H','Y');
    search.insert('Y','A');
    search.insert('X','W');
    search.insert('H','A');
    search.insert('X','A');
    search.insert('I','M');
    search.insert('G','J');
    search.insert('N','G');
    search.insert('D','M');
    search.insert('L','D');
    search.insert('V','T');
    search.insert('I','Y');
    search.insert('S','J');
    search.insert('K','Y');
    search.insert('F','R');
    search.insert('U','T');
    search.insert('Z','M');
    search.insert('T','Z');
    search.insert('B','I');
    search.insert('E','K');
    search.insert('N','J');
    search.insert('X','Q');
    search.insert('F','Y');
    search.insert('H','P');
    search.insert('Z','D');
    search.insert('V','O');
    search.insert('E','C');
    search.insert('V','C');
    search.insert('P','A');
    search.insert('B','N');
    search.insert('S','W');
    search.insert('P','D');
    search.insert('L','W');
    search.insert('D','W');
    search.insert('K','C');
    search.insert('L','M');
    search.insert('R','O');
    search.insert('F','L');
    search.insert('R','H');
    search.insert('K','O');
    search.insert('T','W');
    search.insert('R','K');
    search.insert('C','W');
    search.insert('N','T');
    search.insert('R','P');
    search.insert('E','M');
    search.insert('G','T');
    search.insert('U','K');
    search.insert('Q','D');
    search.insert('U','S');
    search.insert('J','V');
    search.insert('P','Y');
    search.insert('X','Z');
    search.insert('U','H');
    search.insert('H','M');
    search.insert('I','C');
    search.insert('V','M');
    search.insert('N','I');
    search.insert('B','K');
    search.insert('R','Q');
    search.insert('O','C');

    search.traverse();
  }

  static class Search {
    Map<Character, Node> x = new HashMap<>();

    void insert(char a, char b) {
      Node na = x.getOrDefault(a, new Node(a));
      Node nb = x.getOrDefault(b, new Node(b));
      na.addChild(nb);

      x.putIfAbsent(a, na);
      x.putIfAbsent(b, nb);
    }

    void traverse() {
      for (Node value : x.values()) {
        traverse(value);
        reset();
        System.out.println();
      }
    }

    void reset() {
      for (Node value : x.values()) {
        value.setVisited(false);
      }
    }

    void traverse(Node n) {
      if (n.isVisited()) {
        return;
      }

      while (n.nextParent().isPresent()) {
        n = n.nextParent().get();
      }
      n.setVisited(true);
      System.out.print(n.name);

      if (n.nextChild().isPresent()) {
        traverse(n.nextChild().get());
      }
    }
  }

  static class Node implements Comparable {
    private boolean visited;
    private Character name;
    private List<Node> parents = new ArrayList<>();
    private List<Node> children = new ArrayList<>();

    Node(char name) {
      this.name = name;
      this.visited = false;
    }

    void setVisited(boolean v) {
      this.visited = v;
    }

    void addChild(Node child) {
      this.children.add(child);
      child.parents.add(this);
    }

    boolean isVisited() {
      return visited;
    }

    Optional<Node> nextChild() {
      return children.stream().filter(n -> !n.isVisited()).sorted().findFirst();
    }

    Optional<Node> nextParent() {
      return parents.stream().filter(n -> !n.isVisited()).sorted().findFirst();
    }

    @Override
    public int compareTo(Object o) {
      return this.name.compareTo(((Node) o).name);
    }
  }
}
