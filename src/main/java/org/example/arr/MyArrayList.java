package org.example.arr;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.IntStream;

public class MyArrayList<T> {

    private T[] list;
    private int end;


    public MyArrayList(int size) {
        this.list = (T[]) new Object[size];
        end = 0;
    }

    public MyArrayList() {
        this(1);
    }

    private boolean alloc() {
        try {
            T[] newList = (T[]) new Object[list.length * 2];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean dealloc() {
        int len = list.length;
        while ((end << 1) < len) len >>= 1;
        len <<= 1;
        if (len == list.length) return false;
        T[] newList = (T[]) new Object[len];
        System.arraycopy(list, 0, newList, 0, end);
        list = newList;
        return true;
    }

    public int size() {
        return end;
    }

    public boolean isEmpty() {
        return end == 0;
    }

    public boolean contains(T element) {
        return Arrays.stream(list).anyMatch(el -> Objects.equals(el, element));
    }

    public T[] toArray() {
        return list;
    }

    public boolean add(T element) {
        if (end >= list.length) alloc();
        list[end++] = element;
        return true;
    }

    public boolean remove(T element) {
        if (!contains(element)) return false;

        return true;
    }

    public boolean addAll(Collection<T> c) {
        for (T value : c) add(value);
        return true;
    }


    public boolean addAll(int index, Collection c) {
        return false;
    }


    public void clear() {
        this.list = (T[]) new Object[1];
        end = 0;
    }


    public T get(int index) {
        if (index >= end) return null;
        return list[index];
    }

    public T set(int index, T element) {
        if (index >= end) return null;
        return list[index] = element;
    }

    public void add(int index, T element) {
        if (list.length < end + 1) alloc();
        T[] tmpList = (T[]) new Object[end - index + 1];
        System.arraycopy(list, index, tmpList, 0, tmpList.length);
        list[index] = element;
        System.arraycopy(tmpList, 0, list, index + 1, tmpList.length);
    }


    public T remove(int index) {
        if (index >= end) return null;
        T element = list[index];
        T[] tmpList = Arrays.copyOfRange(list, index + 1, end);
        System.arraycopy(tmpList, 0, list, index, tmpList.length);
        end--;
        return element;
    }

    public int indexOf(T o) {
        return IntStream.rangeClosed(0, end).filter(i -> o.equals(list[i])).findFirst().orElse(-1);
    }


    public int lastIndexOf(T o) {
        return IntStream.rangeClosed(0, end).filter(i -> o.equals(list[end - i])).findFirst().orElse(-1);
    }


    public boolean retainAll(Collection<T> c) {
        list = (T[]) Arrays.stream(list).filter(el -> c.contains(el)).toArray();
        return true;
    }


    public boolean removeAll(Collection<T> c) {
        list = (T[]) Arrays.stream(list).filter(el -> !c.contains(el)).toArray();
        return true;
    }


    public boolean containsAll(Collection<T> c) {
        return c.stream().distinct().count() == Arrays.stream(list).filter(el -> !c.contains(el)).distinct().count();
    }

}