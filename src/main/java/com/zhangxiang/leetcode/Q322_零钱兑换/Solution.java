package com.zhangxiang.leetcode.Q322_零钱兑换;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月01日 20:53:27
 * @desc: https://leetcode.cn/problems/coin-change/
 */
public class Solution {
    /**
     * 动态规划 dp[n] = min{dp[n-25],dp[n-20],dp[n-10],.....}+1
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            //硬币数量不会超过amount
            int min = amount;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    min = Math.min(min, dp[i - coins[j]]);
                }
            }
            dp[i] = min + 1;
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
