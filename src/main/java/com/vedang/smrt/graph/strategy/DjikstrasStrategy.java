package com.vedang.smrt.graph.strategy;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.Vertex;

public class DjikstrasStrategy<T extends Vertex> implements ShortestPathStrategy<T> {

    @Override
    public void calculateShortestPath(Graph<T> graph, T sourceVertex) {
	System.out.println("Calculate shortest path for source " + sourceVertex);
	Map<T, Integer> distanceMap = graph.getDistanceMap();
	Map<T, T> parentMap = graph.getParentMap();
	distanceMap.clear();
	parentMap.clear();
	Set<Vertex> unvisited = new HashSet<>();

	// initialise distance map to 0 for source and infinity for rest of the vertices
	for (T v : graph.getAdjacenyMap().keySet()) {
	    if (v.equals(sourceVertex)) {
		distanceMap.put(v, 0);
	    } else {
		distanceMap.put(v, Integer.MAX_VALUE);
	    }
	    unvisited.add(v);
	}
	System.out.println("Distance Map Initialised" + graph.getDistanceMap());
	System.out.println("Unvisited Size=" + unvisited.size());
	PriorityQueue<T> queue = new PriorityQueue<>((v1, v2) -> distanceMap.get(v1) - distanceMap.get(v2));
	queue.add(sourceVertex);
	while (!queue.isEmpty()) {
	    T minVertex = queue.poll();
	    if (unvisited.contains(minVertex)) {
		unvisited.remove(minVertex);
		for (T neighbour : graph.getAdjacenyMap().get(minVertex)) {
		    if (unvisited.contains(neighbour)) {
			Integer currDistance = distanceMap.get(neighbour);
			Integer newDistance = distanceMap.get(minVertex) + 1;
			if (newDistance < currDistance) {
			    distanceMap.put(neighbour, newDistance);
			    parentMap.put(neighbour, minVertex);
			    if (unvisited.contains(neighbour)) {
				queue.offer(neighbour);
			    }
			}
		    }
		}
	    }
	}
	System.out.println("Distance Map size=" + distanceMap.size());
	System.out.println("Parent Map size=" + parentMap.size());
	System.out.println("Distance Map " + distanceMap);
	System.out.println("Parent Map " + parentMap);

    }

}
