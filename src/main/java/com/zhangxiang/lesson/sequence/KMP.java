package com.zhangxiang.lesson.sequence;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月15日 20:27:58
 * @desc:
 */
public class KMP {
    public static int indexOf(String text, String pattern) {
        if (null == text || null == pattern) {
            return -1;
        }
        int tl = text.length();
        int pl = pattern.length();
        if (tl == 0 || pl == 0 || pl > tl) {
            return -1;
        }
        int[] next = next(pattern);
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
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
        int[] next = new int[pattern.length()];
        int i = 0;
        int n = next[i] = -1;
        int imax = next.length - 1;
        while (i < imax) {
            if (n < 0 || pattern.charAt(i) == pattern.charAt(n)) {
                ++i;
                ++n;
                if (pattern.charAt(i) == pattern.charAt(n)) {
                    next[i] = next[n];
                } else {
                    next[i] = n;
                }
            } else {
                n = next[n];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(KMP.indexOf("ADBC", "CA"));
    }
}
