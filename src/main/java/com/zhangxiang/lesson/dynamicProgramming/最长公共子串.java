package com.zhangxiang.lesson.dynamicProgramming;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月06日 20:16:00
 * @desc: ABCBA 和 BABCA 的最长公共子串是 ABC 长度为3
 */
public class 最长公共子串 {
    /**
     * 定义 dp(i,j)是以str1[i-1]、str2[j-1]结尾的最长公共子串的长度
     * 当 str1[i-1] = str2[j-1] dp(i,j) = dp(i-1,j-1)+1;
     * 当 str1[i-1] != str2[j-1] dp(i,j) = 0;
     *
     * @param str1
     * @param str2
     * @return
     */
    static int lcs(String str1, String str2) {
        if (null == str1 || str1.length() == 0) {
            return 0;
        }
        if (null == str2 || str2.length() == 0) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = dp[0][0];
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i-1] == chars2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                max = Math.max(dp[i][j],max);
            }
        }
        return max;
    }


    static int lcs2(String str1, String str2) {
        if (null == str1 || str1.length() == 0) {
            return 0;
        }
        if (null == str2 || str2.length() == 0) {
            return 0;
        }
        char[] rowChars = str1.toCharArray();
        char[] colChars = str2.toCharArray();
        if(rowChars.length < colChars.length){
            char[] temp = rowChars;
            rowChars = colChars;
            colChars = temp;
        }
        int[] dp = new int[colChars.length+1];
        int max = 0;
        for (int i = 1; i < rowChars.length; i++) {
            int cur=0;
            for (int j = 1; j < colChars.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowChars[i-1] == colChars[j-1]) {
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = 0;
                }
                max = Math.max(dp[j],max);
            }
        }
        return max;
    }
}
