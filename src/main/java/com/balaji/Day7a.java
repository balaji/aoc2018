package com.balaji;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day7a {
  static class Node implements Comparable {
    private boolean visited;
    private Character name;
    private List<Node> parents = new ArrayList<>();
    private List<Node> children = new ArrayList<>();

    Node(char name) {
      this.name = name;
    }

    public void setVisited(boolean visited) {
      this.visited = visited;
    }

    public void addParent(Node parent) {
      this.parents.add(parent);
    }

    public void addChild(Node parent) {
      this.children.add(parent);
    }

    public boolean isVisited() {
      return visited;
    }

    public Optional<Node> nextChild() {
      return children.stream().sorted().findFirst();
    }

    public Optional<Node> nextParent() {
      return parents.stream().sorted().findFirst();
    }

    public boolean hasActiveParents() {
      return parents.stream().anyMatch(node -> !node.isVisited());
    }

    public boolean hasActiveChildren() {
      return children.stream().anyMatch(node -> !node.isVisited());
    }

    @Override
    public int compareTo(Object o) {
      return ((Node) o).name.compareTo(this.name);
    }
  }
}
