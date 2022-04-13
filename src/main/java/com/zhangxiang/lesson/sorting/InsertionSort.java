package com.zhangxiang.lesson.sorting;

import java.util.Arrays;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月12日 19:45:03
 * @desc: 插入排序
 */
public class InsertionSort<E extends Comparable<E>> extends Sort<E>{
    protected void sort2() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            E e = array[cur];
            while (cur > 0 && cmp(e, array[cur - 1]) < 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = e;
        }
    }

    /**
     * 用二分查找优化
     */
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            E e = array[begin];
            int swapIndex = binarySearch(begin);
            for (int i = begin -1; i >= swapIndex ; i--) {
                array[i+1] = array[i];
            }
            array[swapIndex] = e;
        }
    }

    private int binarySearch(int begin) {
        int binaryBegin = 0;
        int binaryEnd = begin;
        E e = array[begin];
        while (binaryBegin < binaryEnd) {
            int mid = (binaryBegin + binaryEnd) >> 1;
            if (cmp(e, array[mid]) < 0) {
                binaryEnd = mid;
            } else {
                binaryBegin = mid + 1;
            }
        }
        return binaryBegin;
    }
}
