package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 14:11:46
 * @desc: 快速合并基于RANK的优化 集合高度矮的嫁接到高度高的集合上 并且进行路径分裂优化
 */
public class QuickUnion_R_PS extends QuickUnion_R {

    public QuickUnion_R_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            int parent = parents[v];
            parents[v] = parents[parents[v]];
            v = parent;
        }
        return parents[v];
    }
}
