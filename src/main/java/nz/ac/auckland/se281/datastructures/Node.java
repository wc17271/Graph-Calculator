package nz.ac.auckland.se281.datastructures;

/**
 * A node in a linked list.
 *
 * @param <T> The type of each node.
 */
public class Node<T> {

  private T data;
  private Node<T> next;
  private Node<T> previous;

  /**
   * creates a new node that stores the passed in data.
   *
   * @param data the data to be stored in the node.
   */
  public Node(T data) {
    this.data = data;
    this.next = null;
    this.previous = null;
  }

  /**
   * returns the data stored in the node on which it is invoked.
   *
   * @return the data stored in the node on which it is invoked.
   */
  public T getData() {
    return data;
  }

  /**
   * gets the next node of the node on which it is invoked.
   *
   * @return the next node of the node on which it is invoked.
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * gets the previous node of the node on which it is invoked.
   *
   * @return the previous node of the node on which it is invoked.
   */
  public Node<T> getPrevious() {
    return previous;
  }

  /**
   * sets the passed in node as the next node of the node on which it is invoked.
   *
   * @param next the node to be set as the next node.
   */
  public void setNext(Node<T> next) {
    this.next = next;
  }

  /**
   * sets the passed in node as the previous node of the node on which it is invoked.
   *
   * @param previous the node to be set as the previous node.
   */
  public void setPrevious(Node<T> previous) {
    this.previous = previous;
  }
}
