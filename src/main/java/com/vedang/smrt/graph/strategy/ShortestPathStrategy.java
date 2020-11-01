package com.vedang.smrt.graph.strategy;

import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.Vertex;

public interface ShortestPathStrategy<T extends Vertex> {

    void calculateShortestPath(Graph<T> graph, T sourceVertex);

}
