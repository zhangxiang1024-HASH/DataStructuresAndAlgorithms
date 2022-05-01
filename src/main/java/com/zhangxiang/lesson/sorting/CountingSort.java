package com.zhangxiang.lesson.sorting;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月01日 20:46:17
 * @desc:
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //找到最大最小值
        int min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        //开辟数组空间
        int[] counts = new int[max - min + 1];
        //重新开辟一个相同大小的数组用来放排好序的数据
        Integer[] sortedArr = new Integer[array.length];
        for (Integer integer : array) {
            counts[integer - min]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        //开始排序
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArr[--counts[array[i] - min]] = array[i];
        }
        array = sortedArr;
    }
}
