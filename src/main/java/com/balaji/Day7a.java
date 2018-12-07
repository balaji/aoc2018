package com.balaji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class Day7a {
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
      final HashSet<Node> freeNodes = new HashSet<>();
      for (Node value : x.values()) {
        if (!value.nextParent().isPresent()) {
          freeNodes.add(value);
        }
      }
      final Node node = freeNodes.stream().sorted().findFirst().orElse(null);
      final StringBuilder builder = new StringBuilder();

      freeNodes.remove(node);
      traverse(node, builder, freeNodes);
      System.out.println(builder.toString());
    }

    void traverse(Node n, StringBuilder builder, Set<Node> freeNodes) {
      if (n.isVisited()) {
        return;
      }
      n.setVisited();
      builder.append(n.name);
      freeNodes.addAll(n.availableFree());
      freeNodes.stream().sorted().filter(q -> !q.isVisited()).findFirst().ifPresent(d -> {
        freeNodes.remove(d);
        traverse(d, builder, freeNodes);
      });
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

    void setVisited() {
      this.visited = true;
    }

    void addChild(Node child) {
      this.children.add(child);
      child.parents.add(this);
    }

    boolean isVisited() {
      return visited;
    }

    Optional<Node> nextParent() {
      return parents.stream().filter(n -> !n.isVisited()).sorted().findFirst();
    }

    Set<Node> availableFree() {
      return children.stream().filter(n -> !n.nextParent().isPresent()).collect(Collectors.toSet());
    }

    @Override
    public int compareTo(Object o) {
      return this.name.compareTo(((Node) o).name);
    }
  }
}
