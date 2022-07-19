package com.zhangxiang.leetcode.初级算法.链表;

import com.zhangxiang.leetcode.common.ListNode;

import java.util.Stack;

/**
 * @author: zhangxiang
 * @createTime: 2022年07月19日 18:53:43
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnnhm6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 反转链表 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode first = null, temp;
        while (head != null) {
            temp = head.next;
            head.next = first;
            first = head;
            head = temp;
        }
        return first;
    }

    //stack
    public ListNode reverseList3(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        ListNode node = stack.pop();
        ListNode first = node;
        while (!stack.isEmpty()) {
            ListNode temp = stack.pop();
            node.next = temp;
            node = temp;
        }
        node.next = null;
        return first;
    }
}
