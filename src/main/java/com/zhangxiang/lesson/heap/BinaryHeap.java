package com.zhangxiang.lesson.heap;

import com.zhangxiang.lesson.tree.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月16日 20:22:15
 * @desc:
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
        siftUp(size - 1);
    }

    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) {
                break;
            }
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < size && compare(elements[rightChildIndex], child) > 0) {
                child = elements[childIndex = rightChildIndex];
            }
            if (compare(element, child) >= 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }


    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        E e = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        siftUp(0);
        return e;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if(size == 0){
            elements[0] = element;
            size++;
        }else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (null == element) {
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

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (int) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = (int) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
