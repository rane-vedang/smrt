package com.vedang.smrt.graph.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;

import org.junit.Test;

import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.Station;

public class GraphBuilderTest {

    @Test
    public void test() throws URISyntaxException {
	GraphBuilder graphBuilder = new GraphBuilder();
	Graph<Station> graph = graphBuilder.buildGraphFromFile("/StationMap.csv");
	assertThat(graph.getAdjacenyMap().keySet().size()).isEqualTo(122);
    }

}
