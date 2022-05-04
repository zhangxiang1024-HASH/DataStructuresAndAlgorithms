package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 14:11:46
 * @desc: 快速合并基于RANK的优化 集合高度矮的嫁接到高度高的集合上
 */
public class QuickUnion_R extends QuickUnion {
    protected int[] ranks;

    public QuickUnion_R(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int g1 = find(v1);
        int g2 = find(v2);
        if (g1 == g2) {
            return;
        }
        if (ranks[g1] < ranks[g2]) {
            parents[g1] = g2;
        } else if (ranks[g1] > ranks[g2]){
            parents[g2] = g1;
        }else {
            parents[g1] = g2;
            ranks[g2]++;
        }
    }
}
