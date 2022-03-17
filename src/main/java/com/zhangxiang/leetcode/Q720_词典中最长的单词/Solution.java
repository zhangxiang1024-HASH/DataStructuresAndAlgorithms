package com.zhangxiang.leetcode.Q720_词典中最长的单词;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月17日 19:51:42
 * @desc: https://leetcode-cn.com/problems/longest-word-in-dictionary/
 */
public class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String word : words) {
            if(word.length() == 1 || set.contains(word.substring(0,word.length()-1))){
                res = word.length()>res.length()?word:res;
                set.add(word);
            }
        }
        return res;
    }
}
