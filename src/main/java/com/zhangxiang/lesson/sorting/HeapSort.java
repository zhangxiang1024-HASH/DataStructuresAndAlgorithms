package com.zhangxiang.lesson.sorting;


/**
 * @author: zhangxiang
 * @createTime: 2022年04月05日 18:18:57
 * @desc:
 */
public class HeapSort extends Sort{
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
        Integer element = array[index];
        int half = heapSize >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            Integer child = array[childIndex];
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < heapSize && cmpValue(array[rightChildIndex], child) > 0) {
                child = array[childIndex = rightChildIndex];
            }
            if (cmpValue(element, child) >= 0) {
                break;
            }
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
