package com.vedang.smrt.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.vedang.smrt.graph.builder.GraphBuilder;
import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.Station;

public class SmrtServiceTest {

    @Test
    public void testFindShortestPathSameLine() throws Exception {
	SmrtService smrtService = new SmrtService();
	GraphBuilder graphBuilder = new GraphBuilder();
	Graph<Station> smrtGraph = graphBuilder.buildGraphFromFile("/StationMap.csv");

	Station sourceStation = new Station("NS13", "Yishun", "20 December 1988");
	Station destinationStation = new Station("NS16", "Ang Mo Kio", "20 December 1988");

	smrtService.findShortestPath(smrtGraph, sourceStation);

	assertThat(smrtGraph.getDistanceMap().get(destinationStation)).isEqualTo(3);
    }

    @Test
    public void testFindShortestPathMultiLine() throws Exception {
	SmrtService smrtService = new SmrtService();
	GraphBuilder graphBuilder = new GraphBuilder();
	Graph<Station> smrtGraph = graphBuilder.buildGraphFromFile("/StationMap.csv");
	Station sourceStation = new Station("NS13", "Yishun", "20 December 1988");
	Station destinationStation = new Station("EW15", "Tanjong Pagar", "20 December 1988");

	smrtService.findShortestPath(smrtGraph, sourceStation);

	assertThat(smrtGraph.getDistanceMap().get(destinationStation)).isEqualTo(13);
    }

    @Test
    public void testFindShortestPathByTimeSameLine() throws Exception {
	SmrtService smrtService = new SmrtService();
	GraphBuilder graphBuilder = new GraphBuilder();
	Graph<Station> smrtGraph = graphBuilder.buildGraphFromFile("/StationMap.csv");
	Station sourceStation = new Station("NS13", "Yishun", "20 December 1988");
	Station destinationStation = new Station("NS16", "Ang Mo Kio", "20 December 1988");
	String nonPeakStartTime = "2020-10-29 13:30";
	smrtService.findShortestPathByTime(smrtGraph, sourceStation, nonPeakStartTime);

	assertThat(smrtGraph.getDistanceMap().get(destinationStation)).isEqualTo(30);
    }

    @Test
    public void testFindShortestPathByTimeMultiLine() throws Exception {
	SmrtService smrtService = new SmrtService();
	GraphBuilder graphBuilder = new GraphBuilder();
	Graph<Station> smrtGraph = graphBuilder.buildGraphFromFile("/StationMap.csv");
	Station sourceStation = new Station("NS13", "Yishun", "20 December 1988");
	Station destinationStation = new Station("EW15", "Tanjong Pagar", "20 December 1988");
	String nonPeakStartTime = "2020-10-29 13:30";
	smrtService.findShortestPathByTime(smrtGraph, sourceStation, nonPeakStartTime);

	assertThat(smrtGraph.getDistanceMap().get(destinationStation)).isEqualTo(160);
    }
}
