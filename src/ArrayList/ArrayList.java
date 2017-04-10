package ArrayList;

/**
 * Your implementation of an ArrayList.
 *
 * @author Junyoung Jang
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("An element "
                    + "cannot be put out of range of List Size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be an input");
        }
        int k = backingArray.length;
        if (size == k) {
            k += INITIAL_CAPACITY;
        }
        T[] temp = (T[]) new Object[k];
        for (int i = 0; i < index; i++) {
            temp[i] = backingArray[i];
        }
        temp[index] = data;
        for (int j = index + 1; j < size + 1; j++) {
            temp[j] = backingArray[j - 1];
        }
        size++;
        backingArray = temp;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be an input");
        }
        int k = backingArray.length;
        if (size == k) {
            k += INITIAL_CAPACITY;
        }
        T[] temp = (T[]) new Object[k];
        temp[0] = data;
        for (int j = 1; j < size + 1; j++) {
            temp[j] = backingArray[j - 1];
        }
        size++;
        backingArray = temp;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be an input");
        }
        int k = backingArray.length;
        if (size == k) {
            k += INITIAL_CAPACITY;
        }
        T[] temp = (T[]) new Object[k];
        for (int j = 0; j < size; j++) {
            temp[j] = backingArray[j];
        }
        temp[size] = data;
        size++;
        backingArray = temp;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 1 || index >= size) {
            throw new IndexOutOfBoundsException("An element "
                    + "cannot be put out of range of List Size");
        }
        T temp = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T removeFromFront() {
        T temp = null;
        if (size > 0) {
            temp = backingArray[0];
            for (int i = 0; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[size - 1] = null;
            size--;
        }
        return temp;
    }

    @Override
    public T removeFromBack() {
        T temp = null;
        if (size > 0) {
            temp = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("An element "
                    + "cannot be put out of range of List Size");
        }
        return backingArray[index];
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
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
