package com.zhangxiang.leetcode.Q393_UTF_8编码验证;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月13日 23:28:32
 * @desc: https://leetcode-cn.com/problems/utf-8-validation/
 */
public class Solution {
    public static boolean validUtf8(int[] data) {
        //UTF_8一个字符最多4个字节 所以最多为11110
        //记录字节数
        int n = 0;
        for (int i = 0; i < data.length; i++) {
            if (n > 0) {//说明这个字符还没完 接续字节右移6位必须等于2
                if (data[i] >> 6 != 2) {
                    return false;
                }
                n--;
            } else if (data[i] >> 3 == 0b11110) {
                n = 3;
            } else if (data[i] >> 4 == 0b1110) {
                n = 2;
            } else if (data[i] >> 5 == 0b110) {
                n = 1;
            } else if (data[i] >> 7 == 0) {
                n = 0;
            } else {//255直接不符合、超出四个字节不符合
                return false;
            }
        }
        return n == 0;
    }

    public static void main(String[] args) {
        System.out.println(validUtf8(new int[]{250,145,145,145,145}));
    }
}
