package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 14:11:46
 * @desc: 快速合并基于RANK的优化 集合高度矮的嫁接到高度高的集合上 并且进行路径压缩优化
 */
public class QuickUnion_R_PC extends QuickUnion_R {

    public QuickUnion_R_PC(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
