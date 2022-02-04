package com.zhangxiang.lesson.common;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 12:38:29
 * @desc:
 */
public interface List<T> {
    static final int DEFAULT_ELEMENT_NOT_FOUND = -1;
    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 设置index位置的元素为element，并返回旧值
     *
     * @param index
     * @param element
     * @return
     */
    T set(int index, T element);

    /**
     * 获取元素element在数组中的索引
     *
     * @param element
     * @return
     */
    int indexOf(T element);

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    boolean contains(T element);

    /**
     * 逻辑清空数组
     */
    void clear();

    int size();

    boolean isEmpty();


    /**
     * 添加
     *
     * @param element
     */
    void add(T element);

    void add(int index, T element);

    /**
     * 删除index位置的元素，并返回其值
     *
     * @param index
     * @return
     */
    T remove(int index);
}
