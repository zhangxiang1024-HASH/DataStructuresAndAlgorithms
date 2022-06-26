package com.zhangxiang.leetcode.Q2_两数相加;

import com.zhangxiang.leetcode.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月26日 09:07:45
 * @desc: https://leetcode.cn/problems/add-two-numbers/
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = null;
        ListNode curNode = null;
        boolean advancement = false;
        while (l1 != null || l2 != null) {
            int value1 = 0, value2 = 0;
            if (l1 != null) {
                value1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value2 = l2.val;
                l2 = l2.next;
            }
            int value = advancement ? value1 + value2 + 1 : value1 + value2;
            advancement = value >= 10;
            int remainder = advancement ? value % 10 : value;
            ListNode node = new ListNode(remainder);
            if (null == listNode) {
                listNode = node;
                curNode = listNode;
            } else {
                curNode.next = node;
                curNode = node;
            }
        }
        if (advancement) {
            curNode.next = new ListNode(1);
        }
        return listNode;
    }

    public static void main(String[] args) {
        int[] i1 = new int[]{9, 9, 9, 9,9,9,9};
        int[] i2 = new int[]{9, 9, 9, 9};
        ListNode node1= null;
        ListNode curNode = null;
        for (int i : i1) {
            if(node1 == null){
                node1 = new ListNode(i);
                curNode = node1;
            }else {
                curNode.next = new ListNode(i);
                curNode = curNode.next;
            }
        }

        ListNode node2= null;
        for (int i : i2) {
            if(node2 == null){
                node2 = new ListNode(i);
                curNode = node2;
            }else {
                curNode.next = new ListNode(i);
                curNode = curNode.next;
            }
        }

        ListNode node = addTwoNumbers(node1, node2);
    }
}
