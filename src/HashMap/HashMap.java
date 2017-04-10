package HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Junyoung Jang 902860455
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Null data cannot "
                    + "be a key or a value in this HashMap");
        }
        V out = null;
        if (size + 1 > MAX_LOAD_FACTOR * table.length) {
            resizeBackingTable(table.length * 2 + 1);
        }
        int now = Math.abs((key.hashCode()));
        int k = 0;
        boolean check = true;
        int firstdel = 0;
        while (true) {
            if (k >= table.length 
                    || table[(now + k * k) % table.length] == null) {
                int here = (now + k * k) % table.length;
                if (!check) {
                    table[firstdel] = new MapEntry(key, value);
                    size++;
                    return null;
                } else if (table[here] == null) {
                    table[here] = new MapEntry(key, value);
                    size++;
                    return null;
                }
                resizeBackingTable(table.length * 2 + 1);
                k = 0;
            }
            if (table[(now + k * k) % table.length]
                    .getKey().equals(key)) {
                if (table[(now + k * k) % table.length].isRemoved()) {
                    table[(now + k * k) % table.length].setValue(value);
                    table[(now + k * k) % table.length].setRemoved(false);
                    size++;
                    return null;
                }
                out = table[(now + k * k) % table.length].getValue();
                table[(now + k * k) % table.length].setValue(value);
                return out;
            } else if (check && table[(now + k * k) 
                                      % table.length].isRemoved()) {
                check = false;
                firstdel = (now + k * k) % table.length;
            }
            k++;
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null data cannot "
                    + "be a key or a value in this HashMap");
        }
        V out = null;
        int now = Math.abs((key.hashCode())) % (table.length);
        int k = 0;
        while (table[(now + k * k) % table.length] != null
                && !table[(now + k * k) % table.length].getKey().equals(key)) {
            k++;
            if (k >= table.length) {
                throw new java.util.NoSuchElementException("This HashMap does "
                        + "not have the key in it.");
            }
        }
        int here = (now + k * k) % table.length;
        if (table[here] == null || table[here].isRemoved()) {
            throw new java.util.NoSuchElementException("This HashMap does "
                    + "not have the key in it.");
        } else {
            out = table[here].getValue();
            table[here].setRemoved(true);
            size--;
        }
        return out;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null data cannot "
                    + "be a key or a value in this HashMap");
        }
        V out = null;
        int now = Math.abs((key.hashCode())) % (table.length);
        int k = 0;
        while (table[(now + k * k) % table.length] != null
                && !table[(now + k * k) % table.length].getKey().equals(key)) {
            k++;
            if (k >= table.length) {
                throw new java.util.NoSuchElementException("This HashMap does "
                        + "not have the key in it.");
            }
        }
        int here = (now + k * k) % table.length;
        if (table[here] == null || table[here].isRemoved()) {
            throw new java.util.NoSuchElementException("This HashMap does "
                    + "not have the key in it.");
        } else {
            out = table[here].getValue();
        }
        return out;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null data cannot "
                    + "be a key or a value in this HashMap");
        }
        int now = Math.abs((key.hashCode())) % (table.length);
        int k = 0;
        while (table[(now + k * k) % table.length] != null
                && !table[(now + k * k) % table.length].getKey().equals(key)) {
            k++;
            if (k >= table.length) {
                return false;
            }
        }
        int here = (now + k * k) % table.length;
        return !(table[here] == null || table[here].isRemoved());
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> out = new HashSet<K>();
        for (MapEntry<K, V> now : table) {
            if (now != null && !now.isRemoved()) {
                out.add(now.getKey());
            }
        }
        return out;
    }

    @Override
    public List<V> values() {
        List<V> out = new ArrayList<V>();
        for (MapEntry<K, V> now : table) {
            if (now != null && !now.isRemoved()) {
                out.add(now.getValue());
            }
        }
        return out;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException("Too small to "
                    + "contain existing data in existing HashMap");
        }
        MapEntry<K, V>[] out = (MapEntry<K, V>[]) 
                new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                int now = Math.abs((table[i].getKey().hashCode())) % (length);
                int k = 0;
                while (out[(now + k * k) % length] != null) {
                    k++;
                }
                out[(now + k * k) % length] = table[i];
            } 
        }
        table = out;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
