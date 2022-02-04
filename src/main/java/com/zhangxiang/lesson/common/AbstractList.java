package com.zhangxiang.lesson.common;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 13:09:39
 * @desc:
 */
public abstract class AbstractList<T> implements List<T> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T element) {
        add(size, element);
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != DEFAULT_ELEMENT_NOT_FOUND;
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
    }
}
