package com.zhangxiang.lesson.dynamicarray;

import com.zhangxiang.lesson.common.AbstractList;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月03日 17:47:22
 * @desc: 顺序表
 */
public class DynamicArray<T> extends AbstractList<T> {
    private static final int DEFAULT_CAPACITY = 8;
    private T[] elements;

    public DynamicArray(int capacity) {
        capacity = capacity <= DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements = (T[]) new Object[capacity];
    }

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * 获取index位置的元素
     * 时间复杂度：O(1)
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素为element，并返回旧值
     * 时间复杂度：O(1)
     * @param index
     * @param element
     * @return
     */
    @Override
    public T set(int index, T element) {
        rangeCheck(index);
        T old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 获取元素element在数组中的索引
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return DEFAULT_ELEMENT_NOT_FOUND;
    }

    /**
     * 逻辑清空数组
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 增加元素
     * 时间复杂度：
     *  最好：O(1)
     *  最坏：O(n)
     *  平均：O(n)
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
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
        T[] newElements = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 删除index位置的元素，并返回其值
     * 时间复杂度：
     *  最好：O(1)
     *  最坏：O(n)
     *  平均：O(n)
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        rangeCheck(index);
        T removeValue = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return removeValue;
    }

    @Override
    public String toString() {
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(elements[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
