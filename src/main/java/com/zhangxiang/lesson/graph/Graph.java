package com.zhangxiang.lesson.graph;

import java.util.function.Consumer;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月05日 19:39:47
 * @desc:
 */
public abstract class Graph<V,E> {
    public abstract int edgesSize();
    public abstract int verticesSize();

    public abstract void addVertex(V v);
    public abstract void addEdge(V from, V to);
    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeVertex(V v);
    public abstract void removeEdge(V from, V to);

    /**
     * 广度优先搜索
     * @param begin
     * @param consumer
     */
    public abstract void bfs(V begin, Consumer<V> consumer);

    /**
     * 深度优先搜索
     * @param begin
     * @param consumer
     */
    public abstract void dfs(V begin, Consumer<V> consumer);
}
