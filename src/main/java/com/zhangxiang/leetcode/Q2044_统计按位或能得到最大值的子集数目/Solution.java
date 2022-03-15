package com.zhangxiang.leetcode.Q2044_统计按位或能得到最大值的子集数目;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月15日 19:35:30
 * @desc: https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/
 */
public class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int max = 0, count = 0;
        //nums的非空子集以一共有2^n-1个 故i< 1<<num.length
        for (int i = 0; i < 1 << nums.length; i++) {
            int curVal = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    curVal |= nums[j];
                }
            }
            if (curVal > max) {
                max = curVal;
                count = 1;
            } else if (curVal == max) {
                count++;
            }
        }
        return count;
    }
}
