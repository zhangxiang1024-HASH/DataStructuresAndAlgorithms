package com.zhangxiang.lesson.sorting;

import java.util.Arrays;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月18日 19:22:10
 * @desc:
 */
public class MergeSort<E extends Comparable<E>> extends Sort<E> {
    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对[begin,end)范围的数据进行归并排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将[begin,mid)与[mid,end) 范围的数据合成一个有序序列
     *
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;
        //备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = array[li++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[20000];
        for (int i = 0; i < 20000; i++) {
            integers[i] = (int) (Math.random() * 20001);
        }
        MergeSort<Integer> integerMergeSort = new MergeSort<>();
        long s = System.currentTimeMillis();
        integerMergeSort.sort(integers);
        long e = System.currentTimeMillis();
        System.out.println(e - s);
    }
}
