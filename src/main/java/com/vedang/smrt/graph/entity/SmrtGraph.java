package com.vedang.smrt.graph.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vedang.smrt.graph.strategy.DjikstrasStrategy;
import com.vedang.smrt.graph.strategy.ShortestPathStrategy;

public class SmrtGraph<T extends Station> implements Graph<T> {

    private Map<T, List<T>> adjacenyMap;

    private ShortestPathStrategy<T> shortestPathStrategy = new DjikstrasStrategy<>();

    Map<T, Integer> distanceMap = new HashMap<>();
    Map<T, T> parentMap = new HashMap<>();

    public SmrtGraph() {
	adjacenyMap = new HashMap<>();
    }

    @Override
    public void addVertex(T station) {
	if (station.isOpen()) {
	    if (adjacenyMap.containsKey(station)) {
		Station existingStation = adjacenyMap.keySet().stream()
			.filter(v -> v.getName().equals(station.getName())).findFirst().get();
		existingStation.getCodes().add(station.getCode());
	    } else {
		adjacenyMap.put(station, new LinkedList<T>());
	    }
	}
    }

    @Override
    public void addEdge(T source, T destination) {
	if (source.isOpen() && destination.isOpen()) {
	    adjacenyMap.get(source).add(destination);
	    adjacenyMap.get(destination).add(source);
	}
    }

    @Override
    public Map<T, List<T>> getAdjacenyMap() {
	return adjacenyMap;
    }

    public void setAdjacenyMap(Map<T, List<T>> adjacenyMap) {
	this.adjacenyMap = adjacenyMap;
    }

    @Override
    public void calculateShortestPath(T sourceVertex) {
	this.shortestPathStrategy.calculateShortestPath(this, sourceVertex);

    }

    @Override
    public void setStrategy(ShortestPathStrategy<T> s) {
	this.shortestPathStrategy = s;

    }

    @Override
    public Map<T, Integer> getDistanceMap() {
	return this.distanceMap;
    }

    @Override
    public Map<T, T> getParentMap() {
	return this.parentMap;
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();
	for (T v : getAdjacenyMap().keySet()) {
	    builder.append(v.toString() + ": ");
	    for (T w : getAdjacenyMap().get(v)) {
		builder.append(w.toString() + " ");
	    }
	    builder.append("\n");
	}

	return builder.toString();
    }

}
