package com.zhangxiang.lesson.heap;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月16日 20:14:28
 * @desc:
 */
public interface Heap<E> {
    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    E get();

    E remove();

    E replace(E element);
}
