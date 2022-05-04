package com.zhangxiang.lesson.UnionFind;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月02日 13:46:43
 * @desc: 并查集
 */
public abstract class UnionFind {
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be >= 1");
        }
        //初始化根节点
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找V所属集合(根节点)
     *
     * @param v
     * @return
     */
    public abstract int find(int v);

    /**
     * 合并集合
     * @param v1
     * @param v2
     */
    public abstract void union(int v1, int v2);

    /**
     * 判断v1、v2是否属于同一个集合
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (parents.length >= v) {
            throw new IndexOutOfBoundsException("Index must be < " + parents.length);
        }
    }
}
