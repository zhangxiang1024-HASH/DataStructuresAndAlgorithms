package com.zhangxiang.leetcode.Q51_N皇后;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月18日 21:30:04
 * @desc: https://leetcode.cn/problems/n-queens/
 */
public class Solution {
    //索引是行号，数组元素是列号
    int[] cols;
    List<List<String>> result;

    public List<List<String>> solveNQueens(int n) {
        cols = new int[n];
        result = new ArrayList<>();
        place(0);
        return result;
    }

    /**
     * 从row行开始摆放皇后
     *
     * @param row
     */
    private void place(int row) {
        int length = cols.length;
        if (row >= length) {
            List<String> oneResult = new ArrayList<>();
            StringBuilder resultStr;
            for (int r = 0; r < length; r++) {
                resultStr = new StringBuilder();
                for (int c = 0; c < length; c++) {
                    if (cols[r] == c) {//摆放皇后的位置
                        resultStr.append("Q");
                    } else {
                        resultStr.append(".");
                    }
                }
                oneResult.add(resultStr.toString());
            }
            result.add(oneResult);
            return;
        }
        for (int column = 0; column < length; column++) {
            if (isValid(row, column)) {
                cols[row] = column;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int column) {
        for (int i = 0; i < row; i++) {
            //同一列不能摆放
            if (cols[i] == column) {
                return false;
            }
            //同一斜线不能摆放
            if (row - i == Math.abs(column - cols[i])) {
                return false;
            }
        }
        return true;
    }
}
