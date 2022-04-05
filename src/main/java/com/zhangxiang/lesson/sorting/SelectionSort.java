package com.zhangxiang.lesson.sorting;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月05日 18:06:27
 * @desc:
 */
public class SelectionSort extends Sort{

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = array[0];
            for (int begin = 1; begin <= end ; begin++) {
                if(cmp(maxIndex,begin) <= 0){
                    //每轮选择最大的值和尾部交换
                    maxIndex = begin;
                }
            }
            swap(maxIndex,end);
        }
    }
}
