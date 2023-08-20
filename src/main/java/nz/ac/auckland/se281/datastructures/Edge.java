package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {

  private T source;
  private T destination;

  /**
   * creates a new edge between two verticies.
   *
   * @param source the source vertex.
   * @param destination the destination vertex.
   */
  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  /**
   * gets the source vertex of the edge.
   *
   * @return the source vertex of an edge
   */
  public T getSource() {
    return source;
  }

  /**
   * gets the destination vertex of the edge.
   *
   * @return the destination vertex of an edge
   */
  public T getDestination() {
    return destination;
  }
}
