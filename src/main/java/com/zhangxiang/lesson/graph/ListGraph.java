package com.zhangxiang.lesson.graph;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author: zhangxiang
 * @createTime: 2022年05月05日 19:41:20
 * @desc:
 */
public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> weightManager.compare(e1.weight, e2.weight);


    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {
            return;
        }
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (Objects.isNull(fromVertex)) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (Objects.isNull(toVertex)) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, weight);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (Objects.isNull(vertex)) {
            return;
        }
        Iterator<Edge<V, E>> outEdgeIterator = vertex.outEdges.iterator();
        while (outEdgeIterator.hasNext()) {
            Edge<V, E> edge = outEdgeIterator.next();
            edge.to.inEdges.remove(edge);
            edges.remove(edge);
            outEdgeIterator.remove();
        }
        Iterator<Edge<V, E>> inEdgeIterator = vertex.inEdges.iterator();
        while (inEdgeIterator.hasNext()) {
            Edge<V, E> edge = inEdgeIterator.next();
            edge.from.outEdges.remove(edge);
            edges.remove(edge);
            inEdgeIterator.remove();
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (Objects.isNull(fromVertex)) {
            return;
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (Objects.isNull(toVertex)) {
            return;
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin, Consumer<V> consumer) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (null == vertex) {
            return;
        }
        //已经遍历过的顶点
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> vertexQueue = new LinkedList<>();
        vertexQueue.offer(vertex);
        visitedVertices.add(vertex);
        while (!vertexQueue.isEmpty()) {
            Vertex<V, E> pollVertex = vertexQueue.poll();
            //遍历
            consumer.accept(pollVertex.value);
            for (Edge<V, E> outEdge : pollVertex.outEdges) {
                if (!visitedVertices.contains(outEdge.to)) {
                    vertexQueue.offer(outEdge.to);
                    visitedVertices.add(outEdge.to);
                }
            }
        }
    }

    @Override
    public void dfs(V begin, Consumer<V> consumer) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (null == vertex) {
            return;
        }
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> vertexStack = new Stack<>();
        //访问初始顶点
        vertexStack.push(vertex);
        visitedVertices.add(vertex);
        consumer.accept(vertex.value);
        while (!vertexStack.isEmpty()) {
            Vertex<V, E> pop = vertexStack.pop();
            for (Edge<V, E> edge : pop.outEdges) {
                //选择一条边 深入遍历
                if (visitedVertices.contains(edge.to)) {
                    continue;
                }
                vertexStack.push(pop);
                vertexStack.push(edge.to);
                visitedVertices.add(edge.to);
                consumer.accept(edge.to.value);
                break;
            }
        }
    }

    @Override
    public List<V> topologicalSort() {
        List<V> topologicalSortList = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> ins = new HashMap<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            if (vertex.inEdges.size() == 0) {
                queue.offer(vertex);
            } else {
                ins.put(vertex, vertex.inEdges.size());
            }
        });
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            topologicalSortList.add(vertex.value);
            Integer in = ins.get(vertex) - 1;
            if (in == 0) {
                queue.offer(vertex);
            } else {
                ins.put(vertex, in);
            }
        }
        return topologicalSortList;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        //return prim();
        return kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        if (!iterator.hasNext()) {
            return new HashSet<>();
        }
        Set<Vertex<V, E>> addedVertex = new HashSet<>();
        HashSet<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Vertex<V, E> vertex = iterator.next();
        addedVertex.add(vertex);
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(vertex.outEdges, edgeComparator);
        int verticesSize = vertices.size();
        while (!minHeap.isEmpty() && addedVertex.size() < verticesSize) {
            Edge<V, E> edge = minHeap.remove();
            if (addedVertex.contains(edge.to)) {
                continue;
            }
            edgeInfos.add(edge.edgeInfo());
            addedVertex.add(edge.to);
            minHeap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize <= 1) {
            return new HashSet<>();
        }
        //初始化并查集
        UnionFind<Vertex<V, E>> unionFind = new UnionFind<>();
        vertices.forEach((v, vertex) -> unionFind.makeSet(vertex));
        HashSet<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(edges, edgeComparator);
        while (!minHeap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = minHeap.remove();
            if (unionFind.isSame(edge.from, edge.to)) {
                continue;
            }
            edgeInfos.add(edge.edgeInfo());
            unionFind.union(edge.from, edge.to);
        }
        return edgeInfos;
    }

    /**
     * 递归DFS
     *
     * @param begin
     * @param consumer
     */
    public void dfsWithRecurse(V begin, Consumer<V> consumer) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (null == vertex) {
            return;
        }
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        visitedVertices.add(vertex);
        dfs(vertex, consumer, visitedVertices);
    }

    private void dfs(Vertex<V, E> vertex, Consumer<V> consumer, Set<Vertex<V, E>> visitedVertices) {
        consumer.accept(vertex.value);
        for (Edge<V, E> edge : vertex.outEdges) {
            if (visitedVertices.contains(edge.to)) {
                continue;
            }
            visitedVertices.add(edge.to);
            dfs(edge.to, consumer, visitedVertices);
        }
    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> outEdges = new HashSet<>();
        Set<Edge<V, E>> inEdges = new HashSet<>();

        Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this(from, to, null);
        }

        public EdgeInfo<V, E> edgeInfo() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    public static void main(String[] args) {
        ListGraph<Integer, Integer> listGraph = new ListGraph<>(new WeightManager<Integer>() {
            @Override
            public int compare(Integer w1, Integer w2) {
                return w1.compareTo(w2);
            }

            @Override
            public Integer add(Integer w1, Integer w2) {
                return w1 + w2;
            }
        });
        String[] arr = {"0,4,7", "0,2,2", "2,4,4", "2,1,3", "2,6,6", "2,5,3", "1,5,1", "5,6,4", "5,7,5", "3,7,9"};
        for (String s : arr) {
            String[] strings = s.split(",");
            listGraph.addEdge(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            listGraph.addEdge(Integer.parseInt(strings[1]), Integer.parseInt(strings[0]), Integer.parseInt(strings[2]));
        }
        //listGraph.dfs(1, System.out::println);
        System.out.println(listGraph.mst());
    }
}
