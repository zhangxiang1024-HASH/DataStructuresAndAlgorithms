package com.zhangxiang.lesson.sequence;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月15日 19:23:46
 * @desc: 蛮力算法
 */
public class BruteForce {
    public static int indexOf(String text, String pattern) {
        if (null == text || null == pattern) {
            return -1;
        }
        int tl = text.length();
        int pl = pattern.length();
        if (tl == 0 || pl == 0 || pl > tl) {
            return -1;
        }
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int pi = 0, ti = 0;
        while (pi < pl && ti - pi <= tl - pl) {
            if (textChars[ti] == patternChars[pi]) {
                pi++;
                ti++;
            } else {
                ti -= pi - 1;
                pi = 0;
            }
        }
        return pi == pl ? ti - pi : -1;
    }
}
