package com.zhangxiang.leetcode.Q110_平衡二叉树;

import com.zhangxiang.leetcode.common.TreeNode;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月27日 18:48:56
 * @desc:   https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }


    /**
     * 递归求高度
     *
     * @param node
     * @return
     */
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }
}
