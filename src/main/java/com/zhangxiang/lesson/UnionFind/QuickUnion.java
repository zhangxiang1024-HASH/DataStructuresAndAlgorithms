package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 14:11:46
 * @desc: 快速合并实现
 */
public class QuickUnion extends UnionFind {
    public QuickUnion(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            v = parents[v];
        }
        return parents[v];
    }

    @Override
    public void union(int v1, int v2) {
        int g1 = find(v1);
        int g2 = find(v2);
        if (g1 == g2) {
            return;
        }
        parents[g1] = g2;
    }
}
