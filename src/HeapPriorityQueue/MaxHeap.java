package HeapPriorityQueue;
/**
 * Your implementation of a max heap.
 *
 * @author Junyoung Jang 902860455
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial length of {@code INITIAL_CAPACITY} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item "
                    + "cannot be an input to this Heap.");
        } 
        if (size > backingArray.length - 2) {
            T[] temp = (T[]) new Comparable[backingArray.length * 2 + 1];
            for (int i = 1; i <= size; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        size++;
        backingArray[size] = item;
        int j = size;
        while (j > 1 && backingArray[j].compareTo(backingArray[j / 2]) > 0) {
            backingArray = swap(backingArray, j, j / 2);
            j /= 2;
        }    
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("This Heap is empty.");
        }
        T out = backingArray[1];
        backingArray = swap(backingArray, 1, size);
        backingArray[size] = null;
        size--;
        int i = 1;
        while (2 * i <= size) {
            if (backingArray[2 * i + 1] == null) {
                if (backingArray[i].compareTo(backingArray[2 * i]) < 0) {
                    backingArray = swap(backingArray, i, 2 * i);
                    i *= 2;
                } else {
                    return out;
                }
            } else {
                if (backingArray[i].compareTo(backingArray[2 * i + 1]) > 0
                        && backingArray[i].compareTo(backingArray[2 * i]) > 0) {
                    return out;
                } else {
                    if (backingArray[2 * i].compareTo(
                            backingArray[2 * i + 1]) < 0) {
                        backingArray = swap(backingArray, i, 2 * i + 1);
                        i = 2 * i + 1;
                    } else {
                        backingArray = swap(backingArray, i, 2 * i);
                        i *= 2;
                    }
                }
            }
        }
        return out;
    }
    /**
     * Helper method for swapping two index in backingArray.
     * @param array the input backingArray.
     * @param a the one index to swap its value.
     * @param b the another index to swap its value.
     * @return backingArray that has been swapped in given two indexes.
     */
    private T[] swap(T[] array, int a, int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        return array;
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
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}