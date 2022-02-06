package com.zhangxiang.lesson.stack;

import com.zhangxiang.lesson.common.List;
import com.zhangxiang.lesson.dynamicarray.DynamicArray;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 13:44:50
 * @desc:
 */
public class Stack<T> {
    private List<T> list = new DynamicArray<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(T element) {
        list.add(element);
    }

    public T pop() {
        return list.remove(list.size() - 1);
    }

    public T peek() {
        return list.get(list.size() - 1);
    }
}
