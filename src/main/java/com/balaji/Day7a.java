package com.balaji;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    void part1() {
      final Node node = openNodes().stream().sorted().findFirst().orElse(null);
      final StringBuilder builder = new StringBuilder();

      part1(node, builder);
      System.out.println(builder.toString());
    }

    void part1(Node n, StringBuilder builder) {
      if (n.isVisited()) {
        return;
      }
      n.setVisited();
      builder.append(n.name);
      openNodes().stream().sorted().filter(q -> !q.isVisited()).findFirst().ifPresent(d -> part1(d, builder));
    }

    void part2() {
      System.out.println(part2(lowestN()));
    }

    private HashSet<Node> openNodes() {
      final HashSet<Node> freeNodes = new HashSet<>();
      for (Node value : x.values()) {
        if (!value.nextParent().isPresent()) {
          freeNodes.add(value);
        }
      }
      return freeNodes;
    }

    private List<Node> lowestN() {
      return openNodes().stream().sorted(Comparator.comparingInt(Node::getEffort)).limit(workers).collect(Collectors.toList());
    }

    List<Node> outstanding = new ArrayList<>();
    int workers = 5;
    int total = 0;

    int part2(List<Node> parallelJobs) {
      outstanding.removeAll(openNodes());
      outstanding.addAll(openNodes());

      outstanding.stream().filter(nq -> !nq.isVisited())
          .sorted(Comparator.comparingInt(Node::getEffort))
          .forEach(e -> {
            if (!parallelJobs.contains(e) && parallelJobs.size() < workers) parallelJobs.add(e);
          });

      parallelJobs.stream().min(Comparator.comparingInt(Node::getEffort)).map(Node::getEffort)
          .ifPresent(low -> {
            total += low;
            parallelJobs.stream().filter(na -> na.getEffort() != 0).forEach(na -> {
              na.setEffort(na.getEffort() - low);
              if (na.getEffort() == 0) {
                na.setVisited();
              }
            });
          });

      openNodes().stream()
          .sorted()
          .filter(q -> !q.isVisited())
          .findFirst()
          .ifPresent(d -> part2(parallelJobs.stream().filter(r-> r.getEffort() != 0).collect(Collectors.toList())));

      return total;
    }
  }

  static class Node implements Comparable {

    @Override
    public String toString() {
      return "Node{" +
             "name=" + name + ",effort=" + effort +
             '}';
    }

    private boolean visited;
    private Character name;
    private int effort;
    private List<Node> parents = new ArrayList<>();
    private List<Node> children = new ArrayList<>();

    Node(char name) {
      this.name = name;
      effort = (name - 'A') + 61;
      this.visited = false;
    }

    private int getEffort() {
      return effort;
    }

    private void setEffort(int effort) {
      this.effort = effort;
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
      return parents.stream().filter(n -> !n.isVisited()).findFirst();
    }

    @Override
    public int compareTo(Object o) {
      return this.name.compareTo(((Node) o).name);
    }
  }
}
