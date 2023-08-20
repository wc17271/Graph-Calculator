package nz.ac.auckland.se281.datastructures;

/**
 * A stack data structure.
 *
 * @param <T> The type of each stack.
 */
public class Stack<T> {

  private LinkedList<T> stack;

  public Stack() {
    stack = new LinkedList<>();
  }

  /**
   * adds the passed in vertex to the top of the stack.
   *
   * @param vertex the vertex to be added to the top of the stack.
   */
  public void push(T vertex) {
    stack.append(vertex);
  }

  /**
   * removes the top element from the stack and returns it.
   *
   * @return the top element of the stack.
   */
  public T pop() {
    return stack.removeAtEnd();
  }

  /**
   * checks if the stack is empty.
   *
   * @return a boolean indicating whether the stack is empty.
   */
  public boolean isEmpty() {
    return stack.isEmpty();
  }

  /**
   * returns the number of elements in the stack.
   *
   * @return the number of elements in the stack.
   */
  public int getSize() {
    return stack.getSize();
  }
}
