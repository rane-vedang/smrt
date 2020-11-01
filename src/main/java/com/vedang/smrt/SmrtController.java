package com.vedang.smrt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vedang.smrt.exception.SmrtException;
import com.vedang.smrt.graph.builder.GraphBuilder;
import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.Station;
import com.vedang.smrt.result.entity.Result;
import com.vedang.smrt.result.entity.SmrtResult;
import com.vedang.smrt.service.SmrtService;

@RestController
public class SmrtController {

    @Autowired
    SmrtService smrtService;

    @RequestMapping("/shortestpath")
    @ResponseBody
    Result<Station> findShortestPath(@RequestParam(value = "source", required = true) String source,
	    @RequestParam(value = "destination", required = true) String destination) {
	try {

	    GraphBuilder graphBuilder = new GraphBuilder();
	    Graph<Station> smrtGraph = graphBuilder.buildGraphFromFile("/StationMap.csv");

	    Optional<Station> sourceStationOptional = smrtGraph.getAdjacenyMap().keySet().stream()
		    .filter(s -> s.getName().equals(source)).findFirst();
	    if (!sourceStationOptional.isPresent()) {
		throw new SmrtException("source station is missing or invalid or not opened yet");
	    }
	    Station sourceStation = sourceStationOptional.get();
	    Optional<Station> destinationStationOptional = smrtGraph.getAdjacenyMap().keySet().stream()
		    .filter(s -> s.getName().equals(destination)).findFirst();
	    if (!destinationStationOptional.isPresent()) {
		throw new SmrtException("destination station is missing or invalid or not opened yet");
	    }
	    Station destinationStation = destinationStationOptional.get();
	    smrtService.findShortestPath(smrtGraph, sourceStation);
	    return new SmrtResult<Station>(sourceStation, destinationStation, smrtGraph.getDistanceMap(),
		    smrtGraph.getParentMap(), false);
	} catch (Exception e) {
	    e.printStackTrace();
	    return new SmrtResult<Station>(new SmrtException(e.getMessage()));
	}

    }

    @RequestMapping("/shortestpathtime")
    @ResponseBody
    Result<Station> findShortestPathByTime(@RequestParam(value = "source", required = true) String source,
	    @RequestParam(value = "destination", required = true) String destination,
	    @RequestParam(value = "startTime", required = true) String startTimeStr) {
	try {
	    GraphBuilder graphBuilder = new GraphBuilder();
	    Graph<Station> smrtGraph = graphBuilder.buildGraphFromFile("/StationMap.csv");

	    Optional<Station> sourceStationOptional = smrtGraph.getAdjacenyMap().keySet().stream()
		    .filter(s -> s.getName().equals(source)).findFirst();
	    if (!sourceStationOptional.isPresent()) {
		throw new SmrtException("source station is missing or invalid or not opened yet");
	    }
	    Station sourceStation = sourceStationOptional.get();
	    Optional<Station> destinationStationOptional = smrtGraph.getAdjacenyMap().keySet().stream()
		    .filter(s -> s.getName().equals(destination)).findFirst();
	    if (!destinationStationOptional.isPresent()) {
		throw new SmrtException("destination station is missing or invalid or not opened yet");
	    }
	    Station destinationStation = destinationStationOptional.get();
	    smrtService.findShortestPathByTime(smrtGraph, sourceStation, startTimeStr);
	    return new SmrtResult<Station>(sourceStation, destinationStation, smrtGraph.getDistanceMap(),
		    smrtGraph.getParentMap(), true);
	} catch (Exception e) {
	    e.printStackTrace();
	    return new SmrtResult<Station>(new SmrtException(e.getMessage()));
	}

    }

}
