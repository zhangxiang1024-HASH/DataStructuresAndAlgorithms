package com.zhangxiang.leetcode.Q653_两数之和IV输入BST;

import com.zhangxiang.leetcode.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月21日 19:44:14
 * @desc: https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 */
public class Solution {
    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (null == root) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        //深度优先搜索
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
