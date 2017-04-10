package Sorting;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Junyoung Jang 902860455
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (comparator == null || arr == null) {
            throw new IllegalArgumentException("null comparator or null array"
                    + " cannot be used for bubble sort.");
        }
        int l = arr.length;
        int i = 0;
        boolean swapped = true;
        while (i < l - 1 && swapped) {
            swapped = false;
            for (int j = 0; j < l - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            i++;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (comparator == null || arr == null) {
            throw new IllegalArgumentException("null comparator or null array"
                    + " cannot be used for insertion sort.");
        }
        int l = arr.length;
        for (int i = 1; i < l; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (comparator == null || arr == null || rand == null) {
            throw new IllegalArgumentException("null comparator, null array"
                    + " or null random cannot be used for quick sort.");
        }
        quickSort(arr, comparator, rand, 0, arr.length);
    }
    
    /**
     * Recursive helper function for quickSort that does quick sort given 
     * left and right boundary as subset. 
     * @param <T> data type to sort
     * @param arr  the array that must be sorted after the method runs
     * @param c  the Comparator used to compare the data in arr.
     * @param rand the Random object used to select pivots
     * @param l left boundary for sorting in this recursion.
     * @param r right boundary for sorting in this recursion.
     */
    private static <T> void quickSort(T[] arr, Comparator<T> c, 
            Random rand, int l, int r) {
        
        if (r - l <= 1) {
            return;
        }
        
        int pi = rand.nextInt(r - l) + l;
        T p = arr[pi];
        arr[pi] = arr[l];
        arr[l] = p;
        int li = l + 1;
        int ri = r - 1;
        
        while (li <= ri) {
            while (li <= ri && c.compare(arr[li], p) < 0) {
                li++;
            }
            while (li <= ri && c.compare(arr[ri], p) > 0) {
                ri--;
            }
            if (li <= ri) {
                T temp = arr[li];
                arr[li] = arr[ri];
                arr[ri] = temp;
                li++;
                ri--;
            }
        }
        T tempa = arr[ri];
        arr[ri] = arr[l];
        arr[l] = tempa;
        
        quickSort(arr, c, rand, l, ri);
        quickSort(arr, c, rand, ri + 1, r);
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (comparator == null || arr == null) {
            throw new IllegalArgumentException("null comparator or null array"
                    + " cannot be used for merge sort.");
        }
        int l = arr.length;
        if (l <= 1) {
            return;
        }
        int mi = l / 2;
        T[] la = (T[]) new Object[mi];
        T[] ra = (T[]) new Object[l - mi];
        for (int i = 0; i < mi; i++) {
            la[i] = arr[i];
        }
        for (int j = 0; j < l - mi; j++) {
            ra[j] = arr[mi + j];
        }
        mergeSort(la, comparator);
        mergeSort(ra, comparator);
        int li = 0;
        int ri = 0;
        int ci = 0;
        while (li + ri < l) {
            if (li < mi && (ri >= l - mi 
                    || comparator.compare(la[li], ra[ri]) <= 0)) {
                arr[ci++] = la[li++];
            } else {
                arr[ci++] = ra[ri++];
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("null array cannot be sorted.");
        }
        LinkedList<Integer>[] buck = new LinkedList[19];
        for (int q = 0; q < 19; q++) {
            buck[q] = new LinkedList<Integer>();
        }
        int l = arr.length;
        if (l == 0) {
            return arr;
        }
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < l; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int it = 0;
        while (max > 0 || min < 0) {
            max /= 10;
            min /= 10;
            it++;
        }
        for (int j = 0; j < it; j++) {
            int p = pow(10, j);
            for (int k = 0; k < l; k++) {
                int numb = arr[k];
                buck[((numb / p) % 10) + 9].add(numb);
            }
            int g = 0;
            int h = 0;
            while (g < 19) {
                LinkedList<Integer> each = buck[g];
                while (!each.isEmpty()) {
                    arr[h++] = each.removeFirst();
                }
                g++;
            }
        }
        return arr;
    }

    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("null array cannot be sorted.");
        }
        int l = arr.length;
        if (l == 0) {
            return arr;
        }
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < l; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int it = 0;
        while (max > 0 || min < 0) {
            max /= 10;
            min /= 10;
            it++;
        }
        return msdRadixSorthelp(arr, it);
    }
    
    /**
     * A recursive helper function that takes in each sub array of integer
     * and return ordered integer array by order of most significant digit 
     * to lowest.
     * @param arr array to be sorted over recursion.
     * @param it iteration number that decides which digit to organize in this
     * recursion.
     * @return array sorted by each digit specified with it.
     */
    private static int[] msdRadixSorthelp(int[] arr, int it) {
        LinkedList<Integer>[] buck = new LinkedList[19];
        for (int q = 0; q < 19; q++) {
            buck[q] = new LinkedList<Integer>();
        }
        int l = arr.length;
        if (l <= 1 || it == 0) {
            return arr;
        }
        int p = pow(10, it - 1);
        for (int k = 0; k < l; k++) {
            int numb = arr[k];
            buck[((numb / p) % 10) + 9].add(numb);
        }
        int g = 0;
        int h = 0;
        while (g < 19) {
            LinkedList<Integer> each = buck[g];
            int z = each.size();
            int[] ret = new int[z];
            for (int i = 0; i < z; i++) {
                ret[i] = each.removeFirst();
            }
            ret = msdRadixSorthelp(ret, it - 1);
            for (int j = 0; j < z; j++) {
                arr[h++] = ret[j];
            }
            g++;
        }
        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}
