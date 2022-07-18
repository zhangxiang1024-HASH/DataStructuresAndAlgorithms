package com.zhangxiang.leetcode.初级算法.链表;

import com.zhangxiang.leetcode.common.ListNode;

/**
 * @author: zhangxiang
 * @createTime: 2022年07月18日 19:17:15
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn2925/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 删除链表的倒数第N个节点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        int last = length(head) - n;
        if (last == 0) {
            return head.next;
        }
        for (int i = 0; i < last - 1; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    private int length(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
}
