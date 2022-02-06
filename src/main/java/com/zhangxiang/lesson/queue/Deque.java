package com.zhangxiang.lesson.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 16:01:01
 * @desc: 利用双链表实现双端队列
 */
public class Deque<E> {
    private List<E> list;

    public Deque() {
        list = new LinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueueRear(E element) {
        list.add(element);
    }

    public E deQueueFront() {
        return list.remove(0);
    }

    public void enQueueFront(E element) {
        list.add(0, element);
    }

    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    public E rear() {
        return list.get(list.size() - 1);
    }

    public E front() {
        return list.get(0);
    }
}
