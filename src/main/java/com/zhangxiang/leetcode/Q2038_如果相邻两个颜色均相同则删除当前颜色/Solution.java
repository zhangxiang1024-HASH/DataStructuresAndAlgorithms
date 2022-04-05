package com.zhangxiang.leetcode.Q2038_如果相邻两个颜色均相同则删除当前颜色;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月22日 20:36:49
 * @desc:
 */
public class Solution {
    public static boolean winnerOfGame(String colors) {
        for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            String flag = i % 2 == 0 ? "BBB" : "AAA";
            if (colors.length() < 3) {
                return i % 2 == 0;
            } else {
                if (colors.contains(flag)) {
                    int index = colors.indexOf(flag);
                    colors = colors.substring(0, index) + colors.substring(index + 3);
                    break;
                }
                return !flag.equals("AAA");
            }
        }
        return false;
    }
}
