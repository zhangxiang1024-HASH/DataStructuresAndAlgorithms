package com.zhangxiang.leetcode.Q225_用队列实现栈;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 19:13:13
 * @desc: https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class MyStack {
    private List<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        return queue.remove(queue.size() - 1);
    }

    public int top() {
        return queue.get(queue.size() - 1);
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
