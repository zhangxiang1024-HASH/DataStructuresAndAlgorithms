package com.zhangxiang.lesson.dynamicProgramming;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月09日 19:11:58
 * @desc:
 */
public class 背包问题0_1 {
    /**
     * 定义dp(i,j) 是容量为j时，可选前i件物品时的价值最大值
     * 那么 当 j< weights[i-1] 时 dp(i,j) = dp(i-1,j);
     * 当 j >= weights[i-1] 时 dp(i,j) = max{dp(i-1,j),dp(i-1,j-weights[i-1])+values[i-1]}
     */
    static int maxValue(int[] values, int[] weights, int capacity) {
        if ((null == values || values.length == 0) || (null == weights || weights.length == 0)) {
            return 0;
        }
        if (values.length != weights.length || capacity <= 0) {
            return 0;
        }
        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[weights.length][capacity];
    }

    //空间优化
    static int maxValue2(int[] values, int[] weights, int capacity) {
        if (null == values || values.length == 0 || null == weights || weights.length == 0) {
            return 0;
        }
        if (values.length != weights.length || capacity <= 0) {
            return 0;
        }
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= 1; j--) {
                if(j < weights[i-1]){
                    continue;
                }
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[capacity];
    }
}
