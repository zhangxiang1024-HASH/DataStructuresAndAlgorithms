package com.zhangxiang.leetcode.初级算法.数组;

import java.util.Arrays;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月30日 21:38:01
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2cv1c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 加一 {
    public int[] plusOne(int[] digits) {
        //当最高位为9时可能会产生扩容
        for (int i = digits.length-1; i >= 0; i--) {
            if(digits[i] == 9){
                digits[i] = 0;
            }else {
                digits[i] +=1;
                return digits;
            }
        }
        int[] ints = new int[digits.length + 1];
        ints[0]=1;
        return ints;
    }
}
