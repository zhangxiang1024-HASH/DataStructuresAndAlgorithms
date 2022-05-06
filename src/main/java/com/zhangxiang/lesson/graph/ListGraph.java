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
        ListGraph<Integer, Integer> listGraph = new ListGraph<>();
        String[] arr = {"3,7", "3,1", "1,0", "1,5", "1,2", "1,6", "2,4"};
        for (String s : arr) {
            String[] strings = s.split(",");
            listGraph.addEdge(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
            listGraph.addEdge(Integer.parseInt(strings[1]), Integer.parseInt(strings[0]));
        }
        listGraph.dfs(1, System.out::println);
    }
}
