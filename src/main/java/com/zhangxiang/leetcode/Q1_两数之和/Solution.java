package com.zhangxiang.leetcode.Q1_两数之和;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月26日 08:59:10
 * @desc: https://leetcode.cn/problems/two-sum/
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }
}
