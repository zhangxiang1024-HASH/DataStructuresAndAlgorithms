package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 14:11:46
 * @desc: 快速合并基于SIZE优化 元素数量小的嫁接到元素数量大的集合上
 */
public class QuickUnion_S extends QuickUnion {
    private int[] sizes;

    public QuickUnion_S(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int g1 = find(v1);
        int g2 = find(v2);
        if (g1 == g2) {
            return;
        }
        if (sizes[g1] < sizes[g2]) {
            parents[g1] = g2;
            sizes[g2] += sizes[g1];
        } else {
            parents[g2] = g1;
            sizes[g1] += sizes[g2];
        }
    }
}
