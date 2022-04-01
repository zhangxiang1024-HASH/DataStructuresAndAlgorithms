package com.zhangxiang.lesson.sorting.BubbleSort;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月01日 21:01:01
 * @desc:
 */
public class BubbleSort {
    public static void sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean sorted = true;
            for (int j = 1; j <= i; j++) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
