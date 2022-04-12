package com.zhangxiang.lesson.sorting;


/**
 * @author: zhangxiang
 * @createTime: 2022年04月05日 18:18:57
 * @desc:
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E>{
    private int heapSize;
    @Override
    protected void sort() {
        //原地建堆
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
        while (heapSize > 1){
            swap(0,--heapSize);
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        E element = array[index];
        int half = heapSize >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < heapSize && cmp(array[rightChildIndex], child) > 0) {
                child = array[childIndex = rightChildIndex];
            }
            if (cmp(element, child) >= 0) {
                break;
            }
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
