package com.vedang.smrt.graph.builder;

import java.io.InputStreamReader;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.SmrtGraph;
import com.vedang.smrt.graph.entity.Station;

public class GraphBuilder {

    public Graph<Station> buildGraphFromFile(String fileName) {
        Graph<Station> graph = new SmrtGraph<>();
        CSVReader reader = null;
        try {
            System.out.println("Reading from file " + fileName);
            reader = new CSVReaderBuilder(new InputStreamReader(getClass().getResourceAsStream(fileName)))
                    .withSkipLines(1).build();

            String[] line;
            Station prevStation = null;
            while ((line = reader.readNext()) != null) {
                Station station = new Station(line[0], line[1], line[2]);
                graph.addVertex(station);
                if (prevStation != null && prevStation.getLine().equals(station.getLine())) {
                    graph.addEdge(prevStation, station);
                }
                prevStation = station;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Graph built of size " + graph.getAdjacenyMap().keySet().size());
        // System.out.println("Graph is as follows:" + graph);
        // System.out.println(graph);
        return graph;
    }
}
