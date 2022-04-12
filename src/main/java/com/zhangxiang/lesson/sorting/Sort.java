package com.zhangxiang.lesson.sorting;


/**
 * @author: zhangxiang
 * @createTime: 2022年04月05日 18:29:25
 * @desc:
 */
public abstract class Sort<E extends Comparable<E>>{
    protected E[] array;
    private int cmpCount;
    private int swpCount;
    public void sort(E[] array) {
        if (null == array || array.length < 2) {
            return;
        }
        this.array = array;
        sort();
    }

    protected abstract void sort();

    protected int cmp(int l1, int l2) {
        cmpCount++;
        return array[l1].compareTo(array[l2]);
    }

    protected int cmp(E l1, E l2) {
        cmpCount++;
        return l1.compareTo(l2);
    }

    protected void swap(int l1, int l2) {
        swpCount++;
        E temp = array[l1];
        array[l1] = array[l2];
        array[l2] = temp;
    }
}
