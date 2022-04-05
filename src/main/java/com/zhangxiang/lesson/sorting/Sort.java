package com.zhangxiang.lesson.sorting;

/**
 * @author: zhangxiang
 * @createTime: 2022年04月05日 18:29:25
 * @desc:
 */
public abstract class Sort {
    protected Integer[] array;
    private int cmpCount;
    private int swpCount;
    public void sort(Integer[] array) {
        if (null == array || array.length < 2) {
            return;
        }
        this.array = array;
        sort();
    }

    protected abstract void sort();

    protected int cmp(int l1, int l2) {
        cmpCount++;
        return array[l1] - array[l2];
    }

    protected int cmpValue(int l1, int l2) {
        cmpCount++;
        return l1 - l2;
    }

    protected void swap(int l1, int l2) {
        swpCount++;
        Integer temp = array[l1];
        array[l1] = array[l2];
        array[l2] = temp;
    }
}
