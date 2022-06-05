package com.zhangxiang.leetcode.Q300_最长递增子序列;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月05日 17:24:47
 * @desc: https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class Solution {
    /**
     * 定义dp[i]是以nums[i]结尾的最长递增子序列的长度
     * <p>
     * dp[i] = Math.max(dp[j] + 1, dp[i]);(j< i);
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
