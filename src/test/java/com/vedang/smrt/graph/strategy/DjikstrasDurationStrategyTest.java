package com.vedang.smrt.graph.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import com.vedang.smrt.graph.entity.SmrtGraph;
import com.vedang.smrt.graph.entity.Station;

public class DjikstrasDurationStrategyTest {

    @Test
    public void testShortestPath() throws Exception {
	SmrtGraph<Station> smrtGraph = new SmrtGraph<>();
	Station station1 = new Station("NS1", "Jurong East", "10 March 1990");
	Station station2 = new Station("NS2", "Bukit Batok", "10 March 1990");
	Station station3 = new Station("NS3", "Bukit Gombak", "10 March 1990");
	smrtGraph.addVertex(station1);
	smrtGraph.addVertex(station2);
	smrtGraph.addVertex(station3);
	smrtGraph.addEdge(station1, station2);
	smrtGraph.addEdge(station2, station3);
	LocalDateTime startTime = LocalDateTime.of(2020, 10, 29, 13, 15);
	DjikstrasDurationStrategy<Station> djikstrasDurationStrategy = new DjikstrasDurationStrategy<>(startTime);

	djikstrasDurationStrategy.calculateShortestPath(smrtGraph, station1);

	assertThat(smrtGraph.getDistanceMap().get(station3)).isEqualTo(20);

    }
}
