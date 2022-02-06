package com.zhangxiang.lesson.queue;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 16:36:54
 * @desc: 循环队列
 */
public class CircleQueue<E> {
    private int size;
    private int front;
    private E[] elements;
    private final static int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[(front + size) % elements.length] = element;
        size++;
    }

    public E deQueue() {
        E front = elements[this.front];
        elements[this.front] = null;
        this.front = (this.front + 1) % elements.length;
        size--;
        return front;
    }

    public E front() {
        return elements[front];
    }

    /**
     * 扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (capacity <= oldCapacity) {
            return;
        }
        //扩大1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        elements = newElements;
        front = 0;
    }
}
