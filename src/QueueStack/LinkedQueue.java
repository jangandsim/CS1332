package QueueStack;
/**
 * Your implementation of a linked queue.
 *
 * @author Junyoung Jang 902860455
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (head == null) {
            throw new java.util.NoSuchElementException("This is empty queue.");
        }
        T out = head.getData();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return out;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null "
                    + "data cannot be an argument.");
        }
        if (size == 0) {
            LinkedNode temp = new LinkedNode(data);
            head = temp;
            tail = temp;
        } else {
            tail.setNext(new LinkedNode(data));
            tail = tail.getNext();
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}