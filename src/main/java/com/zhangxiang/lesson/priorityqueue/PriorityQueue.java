package com.zhangxiang.lesson.priorityqueue;

import com.zhangxiang.lesson.heap.BinaryHeap;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月20日 21:22:32
 * @desc: 优先级队列
 */
public class PriorityQueue<E> {
    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator){
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue(){
        this(null);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public void clear(){
        heap.clear();
    }

    public void enQueue(E element){
        heap.add(element);
    }

    public E deQueue(){
        return heap.remove();
    }

    public E front(){
        return heap.get();
    }

}
