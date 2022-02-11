package com.zhangxiang.leetcode.Q226_翻转二叉树;

import com.zhangxiang.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月11日 16:54:05
 * @desc: https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            //翻转左右节点
            TreeNode tempNode = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = tempNode;

            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }
        return root;
    }
}
