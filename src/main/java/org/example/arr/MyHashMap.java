package org.example.arr;


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class MyHashMap<K, V> {
    private Integer[] hashIndexes;
    private V[] values;

    public MyHashMap() {
        hashIndexes = new Integer[1];
        values = (V[]) new Object[1];
    }

    private int getHash(K key) {
        return Objects.hash(key);
    }

    private int getIndex(K key) {
        int hash = getHash(key);
        return IntStream.range(0, hashIndexes.length).filter(i -> hashIndexes[i] != null && hashIndexes[i] == hash).findFirst().orElse(-1);
    }

    private int setIndex(K key) {
        int hash = getHash(key);
        int idx = IntStream.range(0, hashIndexes.length).filter(i -> hashIndexes[i] == null).findFirst().orElse(-1);
        if (idx == -1) {
            V[] newValues = (V[]) new Object[values.length + 1];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;

            Integer[] newHashIndexes = new Integer[hashIndexes.length + 1];
            System.arraycopy(hashIndexes, 0, newHashIndexes, 0, hashIndexes.length);
            hashIndexes = newHashIndexes;

            idx = values.length - 1;
        }
        hashIndexes[idx] = hash;

        return idx;
    }

    private V add(int idx, V value) {
        if (values[idx] == null) values[idx] = value;
        return values[idx];
    }

    public V put(K key, V value) {
        int idx = getIndex(key);
        if (idx == -1) idx = setIndex(key);
        return add(idx, value);
    }


    public int size() {
        return (int) Arrays.stream(hashIndexes).filter(i -> i != null).count();
    }

    public V get(K key) {
        int idx = getIndex(key);
        if (idx == -1) return null;
        return values[idx];
    }

    public V remove(K key) {
        int idx = getIndex(key);
        if (idx == -1) return null;
        hashIndexes[idx] = null;
        return values[idx];
    }

    public boolean containsKey(K key) {
        int hash = getHash(key);
        return Arrays.stream(hashIndexes).anyMatch(h -> h == hash);
    }

    public boolean containsValue(V value) {
        return Arrays.asList(values).contains(value);
    }

    public void clear() {
        hashIndexes = new Integer[1];
        values = (V[]) new Object[1];
    }

    public boolean isEmpty() {
        return Arrays.stream(hashIndexes).filter(i -> i != null).count() == 0;
    }
}
