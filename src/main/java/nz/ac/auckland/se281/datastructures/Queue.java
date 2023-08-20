package nz.ac.auckland.se281.datastructures;

/**
 * A queue data structure.
 *
 * @param <T> The type of each queue.
 */
public class Queue<T> {

  private LinkedList<T> queue;

  public Queue() {
    queue = new LinkedList<>();
  }

  /**
   * adds the passed in data to the end of the queue.
   *
   * @param data the data to be added to the queue.
   */
  public void enqueue(T data) {
    queue.append(data);
  }

  /**
   * removes the first element from the queue and returns it.
   *
   * @return the first element of the queue.
   */
  public T dequeue() {
    return queue.removeAtStart();
  }

  /**
   * checks if the queue is empty.
   *
   * @return a boolean indicating whether the queue is empty.
   */
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  /**
   * returns the number of elements in the queue.
   *
   * @return the number of elements in the queue.
   */
  public int getSize() {
    return queue.getSize();
  }
}
