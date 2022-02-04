package com.zhangxiang.leetcode.链表.Q237_删除链表中的节点;

import com.zhangxiang.leetcode.链表.common.ListNode;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 15:37:27
 * @desc: https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class Solution {
    public void deleteNode(ListNode node) {
        //用下一个节点的值覆盖这个节点，然后删除下一个节点
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
