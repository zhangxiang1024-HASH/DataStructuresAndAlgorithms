package com.zhangxiang.leetcode.common;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月11日 16:46:37
 * @desc:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
