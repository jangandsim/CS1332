package LinkedList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Junyoung Jang 902860455
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (data == null) {
            throw new IllegalArgumentException("null data cannot be inserted.");
        }
        if (index == 0) {
            addToFront(data);
        } else {
            LinkedListNode nn = new LinkedListNode(data);
            LinkedListNode temp = head;
            while (--index > 0) {
                temp = temp.getNext();
            }
            if (temp.getNext() == null) {
                tail = nn;
            }
            nn.setNext(temp.getNext());
            temp.setNext(nn);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null data cannot be inserted.");
        }
        head = new LinkedListNode(data, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null data cannot be inserted.");
        }
        LinkedListNode nn = new LinkedListNode(data);
        if (size == 0) {
            head = nn;
            tail = nn;
        } else {
            tail.setNext(nn);
            tail = nn;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        T out;
        if (index == 0) {
            LinkedListNode temp = head;
            head = head.getNext(); 
            out = (T) temp.getData();
        } else {
            LinkedListNode temp = head;
            while (--index > 0) {
                temp = temp.getNext();
            }
            out = (T) temp.getNext().getData();
            temp.setNext(temp.getNext().getNext());
            if (temp.getNext() == null) {
                tail = temp;
            }
        }
        size--;
        if (size == 0) {
            tail = null;
        }
        return out;
    }

    @Override
    public T removeFromFront() {
        if (head == null) {
            return null;
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
    public T removeFromBack() {
        if (head == null) {
            return null;
        }
        LinkedListNode temp = head;
        if (temp.getNext() == null) {
            clear();
            return (T) temp.getData();
        }
        while (temp.getNext() != tail) {
            temp = temp.getNext();
        }
        T out = (T) temp.getNext().getData();
        temp.setNext(null);
        tail = temp;
        size--;
        return out;
    }

    @Override
    public T removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null "
                    + "data cannot be an argument.");
        }
        LinkedListNode temp = head;
        if (temp.getData().equals(data)) {
            head = head.getNext();
        } else {
            while (temp.getNext() != null 
                     && !temp.getNext().getData().equals(data)) {
                temp = temp.getNext();
            }
            if (temp.getNext() == null) {
                throw new NoSuchElementException("could "
                        + "not find the value.");
            }
            if (temp.getNext() == tail) {
                temp.setNext(null);
                tail = temp;
            } else {
                temp.setNext(temp.getNext().getNext());
            }
        }
        size--;
        if (size == 0) {
            tail = null;
        }
        return data;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        LinkedListNode temp = head;
        while (index-- > 0) {
            temp = temp.getNext();
        }
        return (T) temp.getData();
    }

    @Override
    public Object[] toArray() {
        T[] temparr = (T[]) new Object[size];
        LinkedListNode temp = head;
        for (int i = 0; i < size; i++) {
            temparr[i] = (T) temp.getData();
            temp = temp.getNext();
        }
        return temparr;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}