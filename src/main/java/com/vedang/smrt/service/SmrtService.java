package com.vedang.smrt.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.vedang.smrt.exception.SmrtException;
import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.Station;
import com.vedang.smrt.graph.strategy.DjikstrasDurationStrategy;
import com.vedang.smrt.graph.strategy.DjikstrasStrategy;

@Component
public class SmrtService {

    public void findShortestPath(Graph<Station> smrtGraph, Station sourceStation) throws SmrtException {

	smrtGraph.setStrategy(new DjikstrasStrategy<>());
	smrtGraph.calculateShortestPath(sourceStation);
    }

    public void findShortestPathByTime(Graph<Station> smrtGraph, Station sourceStation, String startTimeStr)
	    throws Exception {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);

	System.out.println("Journey start time is " + startTime);
	smrtGraph.setStrategy(new DjikstrasDurationStrategy<>(startTime));
	smrtGraph.calculateShortestPath(sourceStation);
    }

}
