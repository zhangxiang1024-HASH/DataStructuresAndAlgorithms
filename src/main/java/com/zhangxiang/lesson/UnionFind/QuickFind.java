package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 14:08:02
 * @desc: 快速查找实现
 */
public class QuickFind extends UnionFind{

    public QuickFind(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }
}
