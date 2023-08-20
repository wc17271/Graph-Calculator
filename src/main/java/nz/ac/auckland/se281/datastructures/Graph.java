package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  private final Set<T> verticies;
  private HashMap<T, List<T>> adjacencyList;

  /**
   * Constructs a graph from a set of un ordered verticies and edges.
   *
   * @param verticies a set of graph verticies that are unordered.
   * @param edges a set of graph edges that are unordered.
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {

    this.verticies = verticies;

    // stores the DESTINATIONS of the vertex as a value
    adjacencyList = new HashMap<T, List<T>>();

    // collecting all destinations that have the same source vertex:
    for (T vertex : verticies) {

      List<Integer> temp = new ArrayList<>();

      for (Edge<T> edge : edges) {
        if (vertex.equals(edge.getSource())) {
          temp.add(Integer.parseInt(edge.getDestination().toString()));
        }
      }

      // sorting in order of destination and casting back to generic:
      List<T> roots = new ArrayList<T>();
      Collections.sort(temp);

      for (Integer i : temp) {
        roots.add((T) i.toString());
      }

      // store the vertex (key) and its destinations into the hashmap:
      adjacencyList.put(vertex, roots);
    }
  }

  /**
   * Determines the roots of a graph based on the passed in verticies and edges.
   *
   * @return the roots of the graph.
   */
  public Set<T> getRoots() {
    List<Integer> tempRoots = new ArrayList<>();

    // checking for roots:
    outerloop:
    for (T vertex : verticies) {

      // if the vertex has an equivalence class, then add the smallest (numerically) vertex:
      if (getEquivalenceClass(vertex).size() > 0) {
        ArrayList<T> equivalenceClass = new ArrayList<T>(getEquivalenceClass(vertex));
        tempRoots.add(Integer.parseInt(equivalenceClass.get(0).toString()));

        continue;
      }

      // We consider two types of nodes as roots: (1) the in-degree of the node is 0 and also has
      // out degree > 0

      // if the outdegree of a vertex, then it is NOT a root.
      if (adjacencyList.get(vertex).size() == 0) {
        continue;
      }

      // if in-degree of the vertex is 0, then it is a root:
      for (T destination : verticies) {
        if (adjacencyList.get(destination).contains(vertex)) {
          continue outerloop;
        }
      }

      tempRoots.add(Integer.valueOf((String) vertex));
    }

    // sort the roots and cast back to generic:
    Set<T> roots = new LinkedHashSet<T>();
    Collections.sort(tempRoots);

    for (Integer i : tempRoots) {
      roots.add((T) i.toString());
    }

    return roots;
  }

  /**
   * Determines whether the graph displays reflexive properties.
   *
   * @return a boolean value indicating whether the graph is reflexive or not.
   */
  public boolean isReflexive() {

    for (T vertex : verticies) {
      // check if vertex is its own source:
      if (!adjacencyList.get(vertex).contains(vertex)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Determines whether the graph displays symmetric properties.
   *
   * @return a boolean value indicating whether the graph is symmetric or not.
   */
  public boolean isSymmetric() {

    for (T vertex : verticies) {

      // if vertex has no neighbour, then vacously true:
      if (adjacencyList.get(vertex).size() == 0) {
        continue;
      }

      // check if the destination vertex has an edge to the current vertex:
      for (T destination : adjacencyList.get(vertex)) {
        if (!adjacencyList.get(destination).contains(vertex)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Determines whether the graph displays transitive properties.
   *
   * @return a boolean value indicating whether the graph is transitive or not.
   */
  public boolean isTransitive() {

    for (T vertex : verticies) {

      // if vertex has no neighbour, then vacously true:
      if (adjacencyList.get(vertex).size() == 0) {
        continue;
      }

      // check if the destination vertex has any edges:
      for (T intermediate : adjacencyList.get(vertex)) {

        // if intermediate edge does not exist, then vacously true:
        if (adjacencyList.get(intermediate).size() == 0) {
          continue;
        }

        // check if the vertex has an edge to the intermediate vertex:
        for (T destination : adjacencyList.get(intermediate)) {
          if (!adjacencyList.get(vertex).contains(destination)) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /**
   * Determines whether the graph displays antisymmetric properties.
   *
   * @return a boolean value indicating whether the graph is anti symmetric or not.
   */
  public boolean isAntiSymmetric() {
    // if (a,b) where a != b, then (b,a) must not exist

    for (T vertex : verticies) {

      // if the vertex has no neighbours, it is vacuously true:
      if (adjacencyList.get(vertex).size() == 0) {
        continue;
      }

      // check if the destination vertex has an edge to the current vertex:
      for (T destination : adjacencyList.get(vertex)) {

        // ignore all cases where a = b
        if (vertex.equals(destination)) {
          continue;
        }

        // if (b,a) exists, then return false:
        if (adjacencyList.get(destination).contains(vertex)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Determines whether the graph is an equivalence relation - reflexive, symmetric and transitive.
   *
   * @return a boolean value indicating whether the graph is an equivalence relation or not.
   */
  public boolean isEquivalence() {
    // graph is an equivalence relation if it is reflexive, symmetric and transitive:
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    }

    return false;
  }

  /**
   * Returns to us the equivalence class of a given vertex. The equivalence class of a vertex are
   * all the neighbouring verticies that the vertex has an edge to.
   *
   * @param vertex the vertex to get the equivalence class of.
   * @return a set of all the verticies that are in the equivalence class of the inputted vertex.
   */
  public Set<T> getEquivalenceClass(T vertex) {

    // initialize new set
    Set<T> set = new LinkedHashSet<T>();

    // if inputted vertex is not in the graph, then return empty set:
    if (!verticies.contains(vertex)) {
      return set;
    }

    // if not an equivalence relation, then return empty set:
    if (!isEquivalence()) {
      return set;
    }

    // get the vertex's neighbours and add to set:
    List<T> neighbours = adjacencyList.get(vertex);

    for (T neighbour : neighbours) {
      set.add(neighbour);
    }

    return set;
  }

  /**
   * Performs an iterative breadth first search of the graph and returns a list of the visited
   * verticies. A breadth first search visits all the neighbours of a vertex on the same level
   * before exploring the next level.
   *
   * @return an ordered list of the vertices that were visited during the breadth first search.
   */
  public List<T> iterativeBreadthFirstSearch() {

    Queue<T> queue = new Queue<>();
    List<T> visited = new ArrayList<T>();

    // getting the roots of the graph:
    Set<T> roots = getRoots();

    // if there are no roots, then return empty list:
    if (roots.isEmpty()) {
      return visited;
    }

    // do breadth first search:
    for (T root : roots) {
      queue.enqueue(root);

      while (!queue.isEmpty()) {
        executeBreadthFirstSearch(visited, queue);
      }
    }

    return visited;
  }

  /**
   * Helper method for responsible for executing the logic of the breadth first search, i.e., adding
   * the current vertex to the list of visited vertices and adding all of its neighbours to the
   * queue.
   *
   * @param visited the list of visited vertices.
   * @param queue the queue of vertices to visit.
   */
  public void executeBreadthFirstSearch(List<T> visited, Queue<T> queue) {
    // recursive case:
    T current = queue.dequeue();

    // add vertex to the list of already visited vertices:
    if (!visited.contains(current)) {
      visited.add(current);
    }

    // add all of the vertex's neighbours to the queue:
    for (T neighbour : adjacencyList.get(current)) {
      if (!visited.contains(neighbour)) {
        queue.enqueue(neighbour);
      }
    }
  }

  /**
   * Performs an iterative depth first search of the graph and returns a list of the visited
   * verticies. A depth first search visits a single branch of a graph until it reaches a dead end
   * before exploring the next branch.
   *
   * @return an ordered list of the vertices that were visited during the depth first search.
   */
  public List<T> iterativeDepthFirstSearch() {

    Stack<T> stack = new Stack<>();
    List<T> visited = new ArrayList<T>();

    // getting the roots of the graph:
    Set<T> roots = getRoots();

    // if there are no roots, then return empty list:
    if (roots.isEmpty()) {
      return visited;
    }

    // depth first search:
    for (T root : roots) {
      stack.push(root);

      while (!stack.isEmpty()) {
        executeDepthFirstSearch(visited, stack);
      }
    }

    return visited;
  }

  /**
   * Helper method responsible for executing the logic of the depth first search, i.e., adding the
   * current vertex to the list of visited vertices and adding all of its neighbours to the stack.
   *
   * @param visited the list of visited vertices.
   * @param stack the stack of vertices to visit.
   */
  public void executeDepthFirstSearch(List<T> visited, Stack<T> stack) {
    T current = stack.pop();

    // add vertex to the list of already visited vertices:
    if (!visited.contains(current)) {
      visited.add(current);
    }

    // add all of the vertex's neighbours to the stack in REVERSE order
    List<T> neighbours = adjacencyList.get(current);

    for (int i = neighbours.size() - 1; i >= 0; i--) {
      T neighbour = neighbours.get(i);

      if (!visited.contains(neighbour)) {
        stack.push(neighbour);
      }
    }
  }

  /**
   * Performs a recursive breadth first search of the graph and returns a list of the visited
   * verticies. A breadth first search visits all the neighbours of a vertex on the same level
   * before exploring the next level.
   *
   * @return an ordered list of the vertices that were visited during the breadth first search.
   */
  public List<T> recursiveBreadthFirstSearch() {

    // initializing variables:
    List<T> visited = new ArrayList<T>();
    Queue<T> queue = new Queue<>();
    Set<T> roots = getRoots();

    // if there are no roots, then return empty list:
    if (roots.isEmpty()) {
      return visited;
    }

    // do recursive breadth first search:
    for (T root : roots) {
      queue.enqueue(root);
      executeBreadthFirstSearchRecursive(root, visited, queue);
    }

    return visited;
  }

  /**
   * Helper method for responsible for executing the logic of the breadth first search, i.e., adding
   * the current vertex to the list of visited vertices and adding all of its neighbours to the
   * queue.
   *
   * @param vertex current vertex.
   * @param visited list of visited vertices.
   * @param queue queue of vertices to visit.
   */
  private void executeBreadthFirstSearchRecursive(T vertex, List<T> visited, Queue<T> queue) {

    // base case:
    if (queue.isEmpty()) {
      return;
    }

    // recursive case:
    // adds the current vertex to queue and visited then get neighbours
    executeBreadthFirstSearch(visited, queue);
    executeBreadthFirstSearchRecursive(vertex, visited, queue);
  }

  /**
   * Performs a recursive depth first search of the graph and returns a list of the visited
   * verticies. A depth first search visits a single branch of a graph until it reaches a dead end
   * before exploring the next branch.
   *
   * @return an ordered list of the vertices that were visited during the depth first search.
   */
  public List<T> recursiveDepthFirstSearch() {

    // initializing variables:
    List<T> visited = new ArrayList<T>();
    Stack<T> stack = new Stack<>();
    Set<T> roots = getRoots();

    // if there are no roots, then return empty list:
    if (roots.isEmpty()) {
      return visited;
    }

    // do recursive depth first search:
    for (T root : roots) {
      stack.push(root);
      executeDepthFirstSearchRecursive(root, visited, stack);
    }

    return visited;
  }

  /**
   * Helper method responsible for executing the logic of the depth first search, i.e., adding the
   * current vertex to the list of visited vertices and adding all of its neighbours to the stack.
   *
   * @param vertex current vertex.
   * @param visited list of visited vertices.
   * @param stack stack of vertices to visit.
   */
  private void executeDepthFirstSearchRecursive(T vertex, List<T> visited, Stack<T> stack) {

    // base case:
    if (stack.isEmpty()) {
      return;
    }

    // recursive case:
    // adds the current vertex to stack and visited then get neighbours
    executeDepthFirstSearch(visited, stack);
    executeDepthFirstSearchRecursive(vertex, visited, stack);
  }
}
