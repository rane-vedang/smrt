package com.vedang.smrt.graph.strategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.vedang.smrt.graph.entity.Graph;
import com.vedang.smrt.graph.entity.Station;
import com.vedang.smrt.graph.entity.Vertex;
import com.vedang.smrt.graph.util.DurationServiceUtil;

public class DjikstrasDurationStrategy<T extends Station> implements ShortestPathStrategy<T> {

    private LocalDateTime startTime;

    public DjikstrasDurationStrategy(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public void calculateShortestPath(Graph<T> graph, T sourceVertex) {
        System.out.println("Calculate shortest path for source " + sourceVertex + " and start time " + startTime);
        LocalDateTime currentTime = startTime;
        Map<T, Integer> distanceMap = graph.getDistanceMap();
        Map<T, LocalDateTime> currentTimeMap = new HashMap<>();
        Map<T, T> parentMap = graph.getParentMap();
        distanceMap.clear();
        parentMap.clear();
        Set<Vertex> unvisited = new HashSet<>();

        // Initialize distance map to 0 for source and infinity for rest of the vertices
        for (T v : graph.getAdjacenyMap().keySet()) {
            if (v.equals(sourceVertex)) {
                distanceMap.put(v, 0);
                currentTimeMap.put(v, currentTime);
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
            if ((unvisited.contains(minVertex) && !DurationServiceUtil.isNight(currentTimeMap.get(minVertex)))
                    || (unvisited.contains(minVertex) && DurationServiceUtil.isNight(currentTimeMap.get(minVertex))
                            && !DurationServiceUtil.NIGHT_NON_OPERATING_LINES.contains(minVertex.getLine()))) {
                unvisited.remove(minVertex);
                for (T neighbour : graph.getAdjacenyMap().get(minVertex)) {
                    if (unvisited.contains(neighbour)) {
                        Integer currDistance = distanceMap.get(neighbour);
                        Integer neighbourDistance = DurationServiceUtil.getDuration(currentTimeMap.get(minVertex),
                                minVertex, neighbour);
                        Integer newDistance = distanceMap.get(minVertex) + neighbourDistance;

                        LocalDateTime newTime = currentTimeMap.get(minVertex).plusMinutes(neighbourDistance);
                        if (newDistance < currDistance) {
                            distanceMap.put(neighbour, newDistance);
                            currentTimeMap.put(neighbour, newTime);
                            parentMap.put(neighbour, minVertex);
                            if ((unvisited.contains(neighbour)
                                    && !DurationServiceUtil.isNight(currentTimeMap.get(neighbour)))
                                    || (unvisited.contains(neighbour)
                                            && DurationServiceUtil.isNight(currentTimeMap.get(neighbour))
                                            && !DurationServiceUtil.NIGHT_NON_OPERATING_LINES
                                                    .contains(neighbour.getLine()))) {
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
