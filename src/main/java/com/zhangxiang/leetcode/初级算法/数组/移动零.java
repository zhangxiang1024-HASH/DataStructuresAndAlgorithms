package com.zhangxiang.leetcode.初级算法.数组;

/**
 * @author: zhangxiang
 * @createTime: 2022年07月02日 15:19:33
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2ba4i/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 移动零 {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length ; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        int left = 0,right = 0;
        while (right < nums.length){
            if(nums[right] != 0){
                int temp = nums[left];
                nums[left++] = nums[right];
                nums[right] = temp;
            }
            right++;
        }
    }
}
