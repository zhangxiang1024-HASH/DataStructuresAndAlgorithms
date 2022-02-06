package com.zhangxiang.leetcode.Q141_环形链表;

import com.zhangxiang.leetcode.common.ListNode;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 19:03:50
 * @desc: https://leetcode-cn.com/problems/linked-list-cycle/
 *
 *
 */
public class Solution {
    //快慢指针 追击问题
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
