package com.zhangxiang.lesson.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月05日 19:39:47
 * @desc:
 */
public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;

    public abstract int edgesSize();

    public abstract int verticesSize();

    public abstract void addVertex(V v);

    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeVertex(V v);

    public abstract void removeEdge(V from, V to);

    public Graph(WeightManager<E> weightManager){
        this.weightManager = weightManager;
    }

    /**
     * 广度优先搜索
     *
     * @param begin
     * @param consumer
     */
    public abstract void bfs(V begin, Consumer<V> consumer);

    /**
     * 深度优先搜索
     *
     * @param begin
     * @param consumer
     */
    public abstract void dfs(V begin, Consumer<V> consumer);

    /**
     * 拓扑排序
     *
     * @return
     */
    public abstract List<V> topologicalSort();

    /**
     * 最小生成树
     *
     * @return
     */
    public abstract Set<EdgeInfo<V, E>> mst();

    public abstract Map<V,PathInfo<V,E>> shortestPath(V begin);

    public interface WeightManager<E> {
        int compare(E w1, E w2);

        E add(E w1, E w2);
    }

    public static class EdgeInfo<V, E> {
        V from;
        V to;
        E weight;

        public EdgeInfo() {
        }

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static class PathInfo<V,E>{
        E weight;
        List<EdgeInfo<V,E>> paths = new LinkedList<>();


    }
}
