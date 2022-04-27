package com.zhangxiang.lesson.sorting;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月18日 20:48:31
 * @desc:
 */
public class QuickSort <E extends Comparable<E>> extends Sort<E>{
    @Override
    protected void sort() {
        quickSort(0, array.length);
    }

    /**
     * 对 [begin,end)范围的数据进行快速排序
     *
     * @param begin
     * @param end
     */
    private void quickSort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = pivotIndex(begin, end);
        quickSort(begin, mid);
        quickSort(mid + 1, end);
    }

    /**
     * 构造[begin,end)范围内的轴点元素
     *
     * @param begin
     * @param end
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
        //随机选择轴点 与begin交换
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        E pivotValue = array[begin];
        //end指向最后一个元素
        end--;
        while (begin < end) {
            while (begin < end) {
                if (cmp(pivotValue, array[end]) < 0) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                if (cmp(pivotValue, array[begin]) > 0) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        array[begin] = pivotValue;
        return begin;
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[300000];
        for (int i = 0; i < 300000; i++) {
            integers[i]=((int) (Math.random()*300001));
        }
        QuickSort<Integer> quickSort = new QuickSort<>();
        long start = System.currentTimeMillis();
        quickSort.sort(integers);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(integers));
        System.out.println("============="+(end-start));
    }
}
