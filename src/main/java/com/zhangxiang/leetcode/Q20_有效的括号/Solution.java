package com.zhangxiang.leetcode.Q20_有效的括号;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月06日 14:24:38
 * @desc: https://leetcode-cn.com/problems/valid-parentheses/
 */
public class Solution {
    private static Map<Character, Character> map = new HashMap<>();

    static {
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {//左括号
                stack.push(s.charAt(i));
            } else {//右括号
                if (stack.isEmpty()) {
                    return false;
                }
                if (!map.get(stack.pop()).equals(s.charAt(i))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
