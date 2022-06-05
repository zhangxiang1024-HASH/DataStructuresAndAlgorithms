package com.zhangxiang.leetcode.Q1143_最长公共子序列;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月05日 19:53:09
 * @desc: https://leetcode.cn/problems/longest-common-subsequence/
 */
public class Solution {

    /**
     * nums1[] nums2[]
     * 定义dp(i,j)是nums1前i个元素和nums2前j个元素的最长公共子序列的长度
     * 当 nums1[i-1] == nums2[j-1] 时 dp(i,j) = dp(i-1,j-1)+1;
     * 当 nums1[i-1] != nums2[j-1] 时 dp(i,j) = max{dp(i-1,j),dp(i,j-1)};
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }

    //空间优化
    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();
        int[][] dp = new int[2][chars2.length + 1];
        for (int i = 1; i <= chars1.length; i++) {
            int row = i & 1;
            int preRow = (i-1) & 1;
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[row][j] = dp[preRow][j - 1] + 1;
                } else {
                    dp[row][j] = Math.max(dp[preRow][j], dp[row][j - 1]);
                }
            }
        }
        return dp[chars1.length & 1][chars2.length];
    }

    //空间优化
    public int longestCommonSubsequence3(String text1, String text2) {
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] rowNums = text1.toCharArray(), colNums = text2.toCharArray();
        if(text1.length() < text2.length()){
            char[] temp = rowNums;
            rowNums = colNums;
            colNums = temp;
        }
        int[] dp = new int[colNums.length + 1];
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j-1], dp[j]);
                }
            }
        }
        return dp[colNums.length];
    }
}
