package nz.ac.auckland.se281.datastructures;

/**
 * A linked list data structure.
 *
 * @param <T> The type of each linkedlist.
 */
public class LinkedList<T> {

  private Node<T> head; // the first node
  private Node<T> tail; // the last node
  private int size;

  /** creates a new linked-list. */
  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * returns the number of elements in the linked-list.
   *
   * @return the number of elements in the linked-list.
   */
  public int getSize() {
    return size;
  }

  /**
   * checks if the linked-list is empty.
   *
   * @return a boolean indicating whether the linked-list is empty.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * returns the data stored in the first node of the linked-list.
   *
   * @return the data stored in the first node of the linked-list.
   */
  public T getHead() {
    return head.getData();
  }

  /**
   * returns the next node of the passed in node in the linked-list.
   *
   * @param node the node whose next node is to be returned.
   * @return the next node of the passed in node.
   */
  public T getNext(Node<T> node) {
    if (node.getNext() == null) {
      return null;
    }
    return node.getNext().getData();
  }

  /**
   * adds a node to the end of the linked-list.
   *
   * @param data the data to be stored in the node.
   */
  public void append(T data) {

    // create the new node:
    Node<T> node = new Node<T>(data);

    // if no nodes exist, set head and tail to the new node:
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      // connect nodes
      tail.setNext(node);
      node.setPrevious(tail);
      tail = node;
    }
    size++;
  }

  /**
   * returns the data stored at the end of the linked-list and removes it.
   *
   * @return the data stored in the node at the end of the list.
   */
  public T removeAtEnd() {

    // check if linked-list is empty:
    if (isEmpty()) {
      return null;
    }

    Node<T> node = tail;

    // if only one node exists:
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      // set the new tail:
      tail = tail.getPrevious();
      tail.setNext(null);
    }
    size--;
    return node.getData();
  }

  /**
   * adds a node to the start of the linked-list.
   *
   * @param data the data to be stored in the node.
   */
  public void prepend(T data) {

    Node<T> node = new Node<T>(data);

    // if no nodes exist, set head and tail to the new node:
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      // connect nodes
      node.setNext(head);
      head.setPrevious(node);
      head = node;
    }
    size++;
  }

  /**
   * returns the data stored at the start of the linked-list and removes it.
   *
   * @return the data stored in the node at the start of the list.
   */
  public T removeAtStart() {

    // check if linked-list is empty:
    if (isEmpty()) {
      return null;
    }

    Node<T> node = head;

    // if only one node exists:
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      // set the new head:
      head = head.getNext();
      head.setPrevious(null);
    }
    size--;
    return node.getData();
  }
}
