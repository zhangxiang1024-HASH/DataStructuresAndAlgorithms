package com.zhangxiang.leetcode.初级算法;

/**
 * @author: zhangxiang
 * @createTime: 2022年06月28日 19:34:04
 * @desc: 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2gy9m/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 删除排序数组中的重复项 {
    public static int removeDuplicates(int[] nums) {
        int curIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[curIndex]) {
                nums[++curIndex] = nums[i];
            }
        }
        return ++curIndex;
    }
}
