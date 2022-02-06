package com.zhangxiang.lesson.queue;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 16:01:01
 * @desc: 循环双端队列
 */
public class CircleDeque<E> {
    private int size;
    private int front;
    private E[] elements;
    private final static int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[(front + size) % elements.length] = element;
        size++;
    }

    public E deQueueFront() {
        ensureCapacity(size + 1);
        E frontElement = elements[this.front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return frontElement;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = (front - 1 + elements.length) % elements.length;
        elements[front] = element;
        size++;
    }

    public E deQueueRear() {
        ensureCapacity(size + 1);
        E rear = elements[(front + size - 1) % elements.length];
        elements[(front + size - 1) % elements.length] = null;
        size--;
        return rear;
    }

    public E rear() {
        return elements[(front + size - 1) % elements.length];
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
