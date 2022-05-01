package com.zhangxiang.lesson.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月18日 20:48:31
 * @desc: 希尔排序每完成一个步长的排序 其逆序列就会减少 故希尔排序底层使用的插入排序
 */
public class ShellSort<E extends Comparable<E>> extends Sort<E>{

    @Override
    protected void sort() {
        List<Integer> stepSequence = shellStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    /**
     * 按照step步长排序
     * @param step
     * col col+step col+2*step col+3*step
     * 1  2  3  4  5  6
     * 7  8  9 10 11 12
     * 13
     */
    private void sort(int step) {
        for (int col = 0; col < step; col++) {
            //插入排序
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur -= step;
                }
            }
        }
    }

    /**
     * 希尔步长序列 n/2^k
     * @return
     */
    private List<Integer> shellStepSequence(){
        List<Integer> shellStepSequence = new ArrayList<>();
        Integer step = array.length;
        while ((step = step >> 1) >=1){
            shellStepSequence.add(step);
        }
        return shellStepSequence;
    }
}
