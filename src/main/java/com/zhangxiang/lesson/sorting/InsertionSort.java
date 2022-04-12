package com.zhangxiang.lesson.sorting;

import java.util.Arrays;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月12日 19:45:03
 * @desc: 插入排序
 */
public class InsertionSort<E extends Comparable<E>> extends Sort<E>{
    @Override
    protected void sort() {
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
}
