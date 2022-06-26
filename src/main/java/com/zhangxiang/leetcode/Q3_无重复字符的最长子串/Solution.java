package com.zhangxiang.leetcode.Q3_无重复字符的最长子串;

import java.util.HashMap;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月26日 12:02:39
 * @desc:  https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class Solution {
    /**
     * 滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0, length = s.length();
        for (int end = 0; end < length; end++) {
            char cur = s.charAt(end);
            if (map.containsKey(cur)) {
                start = Math.max(map.get(cur) + 1, start);
            }
            max = Math.max(max, end - start + 1);
            map.put(cur, end);
        }
        return max;
    }
}
