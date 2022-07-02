package com.zhangxiang.leetcode.初级算法.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月30日 21:16:25
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2y0c2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 两个数组的交集II {
    //采用双指针
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = 0,len2 = 0,r = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        while (len1 < nums1.length && len2 < nums2.length){
            if(nums1[len1] == nums2[len2]){
                result[r++] = nums1[len1];
                len1++;
                len2++;
            }else if(nums1[len1] < nums2[len2]){
                len1++;
            }else {
                len2++;
            }
        }
        return Arrays.copyOfRange(result,0,r);
    }
}
