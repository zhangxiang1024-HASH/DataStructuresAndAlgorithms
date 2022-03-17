package com.zhangxiang.lesson.heap;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月16日 20:22:15
 * @desc:
 */
public class BinaryHeap<E> implements Heap<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;
    private Comparator<E> comparator;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i <size ; i++) {
            elements[i]=null;
        }
        size=0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size+1);
        elements[size] = element;
        size++;
    }

    private void siftUp(int index){
        while (index>0){
            int parentIndex = index>>1;
        }
    }


    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
    }

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    private void emptyCheck(){
        if(size == 0){
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element){
        if(null == element){
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        capacity = oldCapacity + (oldCapacity >> 1); // 1.5倍
        E[] newElements = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }
}
