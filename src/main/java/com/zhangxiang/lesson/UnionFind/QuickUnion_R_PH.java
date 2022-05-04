package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 14:11:46
 * @desc: 快速合并基于RANK的优化 集合高度矮的嫁接到高度高的集合上 并且进行路径减半优化
 */
public class QuickUnion_R_PH extends QuickUnion_R {

    public QuickUnion_R_PH(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return parents[v];
    }
}
