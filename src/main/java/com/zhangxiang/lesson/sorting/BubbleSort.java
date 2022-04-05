package com.zhangxiang.lesson.sorting;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月01日 21:01:01
 * @desc:
 */
public class BubbleSort extends Sort{

    @Override
    protected void sort() {
        for (int i = array.length - 1; i > 0; i--) {
            int sortedIndex = 1;
            for (int j = 1; j <= i; j++) {
                if (cmp(j,j - 1) < 0) {
                    swap(j,j-1);
                    sortedIndex = j;
                }
            }
            i = sortedIndex;
        }
    }
}
