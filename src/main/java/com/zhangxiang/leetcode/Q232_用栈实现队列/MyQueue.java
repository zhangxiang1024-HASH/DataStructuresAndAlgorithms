package com.zhangxiang.leetcode.Q232_用栈实现队列;

import java.util.Stack;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 15:26:47
 * @desc: https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * 用栈实现队列
 */
public class MyQueue {
    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        exchange();
        return outStack.pop();
    }

    private void exchange() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public int peek() {
        exchange();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
