package com.vedang.smrt.graph.entity;

import java.util.List;
import java.util.Map;

import com.vedang.smrt.graph.strategy.ShortestPathStrategy;

public interface Graph<T extends Vertex> {

    void calculateShortestPath(T sourceVertex);

    void setStrategy(ShortestPathStrategy<T> s);

    Map<T, Integer> getDistanceMap();

    Map<T, T> getParentMap();

    Map<T, List<T>> getAdjacenyMap();

    void addVertex(T vertex);

    void addEdge(T src, T target);
}
