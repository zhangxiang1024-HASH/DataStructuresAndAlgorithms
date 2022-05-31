package com.zhangxiang.leetcode.Q53_最大子数组和;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月31日 20:05:25
 * @desc: https://leetcode.cn/problems/maximum-subarray/
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length);
    }

    private int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) {
            return nums[begin];
        }
        int mid = (begin + end) >> 1;
        int leftMax = nums[mid - 1];
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftSum, leftMax);
        }
        int rightMax = nums[mid];
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightSum, rightMax);
        }
        //继续递归调用 每个问题的最大子数组等于 左边最大子数组、右边最大子数组、两边数组一起合成的最大子数组 中的最大值
        return Math.max(leftMax + rightMax, Math.max(maxSubArray(nums, begin, mid), maxSubArray(nums, mid, end)));
    }
}
