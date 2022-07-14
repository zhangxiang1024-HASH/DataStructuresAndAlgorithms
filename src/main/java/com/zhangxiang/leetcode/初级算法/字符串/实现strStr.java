package com.zhangxiang.leetcode.初级算法.字符串;

/**
 * @author: zhangxiang
 * @createTime: 2022年07月14日 19:31:07
 * 实现 strStr() 函数。
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 *
 * 说明：
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnr003/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 实现strStr {
    public int strStr(String haystack, String needle) {
        if (null == haystack || null == needle) {
            return -1;
        }
        int tl = haystack.length();
        int pl = needle.length();
        if (pl == 0) {
            return 0;
        }
        if (tl == 0 || pl > tl) {
            return -1;
        }
        int[] next = next(needle);
        char[] textChars = haystack.toCharArray();
        char[] patternChars = needle.toCharArray();
        int pi = 0, ti = 0;
        while (pi < pl && ti - pi <= tl - pl) {
            if (pi < 0 || textChars[ti] == patternChars[pi]) {
                pi++;
                ti++;
            } else {
                pi = next[pi];
            }
        }
        return pi == pl ? ti - pi : -1;
    }

    private static int[] next(String pattern) {
        char[] chars = pattern.toCharArray();
        int length = chars.length;
        int[] next = new int[length];
        int right = 0,max = length - 1,left = next[0] = -1;
        while (right < max){
            if(left < 0 || chars[left] == chars[right]){
                next[++right] = ++left;
            }else {
                left = next[left];
            }
        }
        return next;
    }
}
