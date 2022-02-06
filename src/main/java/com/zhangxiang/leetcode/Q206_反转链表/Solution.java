package com.zhangxiang.leetcode.Q206_反转链表;

import com.zhangxiang.leetcode.common.ListNode;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 16:21:36
 * @desc: https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Solution {
    //递归解法
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newListNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newListNode;
    }

    //非递归
    public ListNode reverseList2(ListNode head) {
        ListNode tempNode;
        ListNode first = null;
        while (head != null) {
            tempNode = head.next;
            head.next = first;
            first = head;
            head = tempNode;
        }
        return first;
    }
}
