package com.vedang.smrt.result.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vedang.smrt.graph.entity.Station;

public class SmrtResult<T extends Station> extends Result<T> {

    @JsonIgnore
    T source;
    @JsonIgnore
    T destination;

    String cost;

    @JsonIgnore
    List<T> route;
    List<String> routeCodes;
    Map<T, Integer> distanceMap;
    Map<T, T> parentMap;
    List<String> routeDescriptions;

    public SmrtResult(T source, T destination, Map<T, Integer> distanceMap, Map<T, T> parentMap, boolean isDuration) {
	super(String.format("Travel route from %s to %s", source.getName(), destination.getName()));
	this.source = source;
	this.destination = destination;
	String unit = (isDuration == true) ? "minutes" : "stations";
	this.cost = distanceMap.get(destination) + " " + unit;
	if (distanceMap.get(destination) == Integer.MAX_VALUE) {
	    this.cost = null;
	    this.description = String.format("Sorry, We could not find a route from %s till %s at this time",
		    source.getName(), destination.getName());
	} else {
	    setRoute(parentMap, destination);
	    setRouteCodes();
	    setRouteDescription();
	}
    }

    public SmrtResult(Exception e) {
	super(e);
    }

    private void setRoute(Map<T, T> parentMap, T node) {
	this.route = new ArrayList<>();
	this.routeCodes = new ArrayList<>();
	while (node != null) {
	    this.route.add(node);
	    node = parentMap.get(node);
	}
	Collections.reverse(this.route);
    }

    private void setRouteCodes() {
	this.routeCodes = new ArrayList<>();
	for (int i = 0; i < this.route.size(); i++) {
	    Station currentStation = this.route.get(i);
	    this.routeCodes.add(String.join(",", currentStation.getCodes()));
	}
    }

    private void setRouteDescription() {
	this.routeDescriptions = new ArrayList<>();
	String previousLine = "";
	for (int i = 0; i < this.route.size() - 1; i++) {
	    Station currentStation = this.route.get(i);
	    Station nextStation = this.route.get(i + 1);
	    String currentLine = currentStation.getCommonLine(nextStation);
	    if (!previousLine.isEmpty() && !nextStation.getLines().contains(previousLine)) {
		routeDescriptions.add(String.format("Change from %s line to %s line", currentStation.getLine(),
			nextStation.getLine()));
		currentLine = nextStation.getLine();
	    }

	    routeDescriptions.add(String.format("Take %s line from %s to %s", currentLine, this.route.get(i).getName(),
		    this.route.get(i + 1).getName()));
	    previousLine = currentLine;
	}
    }

    public T getSource() {
	return source;
    }

    public void setSource(T source) {
	this.source = source;
    }

    public T getDestination() {
	return destination;
    }

    public void setDestination(T destination) {
	this.destination = destination;
    }

    public String getCost() {
	return cost;
    }

    public void setCost(String cost) {
	this.cost = cost;
    }

    public void setRouteCodes(List<String> routeCodes) {
	this.routeCodes = routeCodes;
    }

    public List<T> getRoute() {
	return route;
    }

    public void setRoute(List<T> route) {
	this.route = route;
    }

    public List<String> getRouteCodes() {
	return routeCodes;
    }

    public void setRouteCode(List<String> routeCodes) {
	this.routeCodes = routeCodes;
    }

    public void setRouteDescription(List<String> routeDescriptions) {
	this.routeDescriptions = routeDescriptions;
    }

    public Map<T, Integer> getDistanceMap() {
	return distanceMap;
    }

    public void setDistanceMap(Map<T, Integer> distanceMap) {
	this.distanceMap = distanceMap;
    }

    public Map<T, T> getParentMap() {
	return parentMap;
    }

    public void setParentMap(Map<T, T> parentMap) {
	this.parentMap = parentMap;
    }

    public List<String> getRouteDescriptions() {
	return routeDescriptions;
    }

    public void setRouteDescriptions(List<String> routeDescriptions) {
	this.routeDescriptions = routeDescriptions;
    }

}
