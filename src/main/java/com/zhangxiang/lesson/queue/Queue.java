package com.zhangxiang.lesson.queue;

import com.zhangxiang.lesson.common.List;
import com.zhangxiang.lesson.linkedlist.LinkedList;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 15:10:10
 * @desc: 用双链表实现队列
 */
public class Queue<E> {
    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public void clear(){
        list.clear();
    }
}
