package com.zhangxiang.leetcode.Q599_两个列表的最小索引总和;

import java.util.*;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月14日 20:41:18
 * @desc:   https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/
 */
public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> list = new ArrayList<>();
        int sum = Integer.MAX_VALUE;
        for (int j = 0; j < list2.length; j++) {
            if (map.containsKey(list2[j])) {
                Integer i = map.get(list2[j]);
                if (i + j < sum) {
                    list.clear();
                    list.add(list2[j]);
                    sum = i + j;
                } else if (i + j == sum) {
                    list.add(list2[j]);
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
