package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra
 * find cost from src to dest
 * 
 * 1. init
 * min heap: comparable vertex (sort by distance)
 * Map<V,D> cache: vertex -> distance
 * add src with 0.0 distance setting
 * 
 * 2. while minHeap is not empty, 
 *    => check u-v distance
 *   
 *  u = poll from min heap
 *  
 *  iter thru adj list  
 *   v = next vertex
 *   new distance(distance thru u to v) = u distance + curr edge cost
 *   
 *   if cache contains v && v's distance <= new distance
 *     => skip
 *   
 *   otherwise, relaxation if necessary (new distance < v's distance)
 *     update vertex w/ set prev, set dist
 *     update cache
 *     add to min heap
 *     
 * @author sunnypark
 *
 */
public class Dijkstra {
	public static void retrieveShortestPath(Vertex source) {
		PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
		Map<Vertex, Double> cache = new HashMap<>();
		source.setMinDistance(0.0);
		cache.put(source, source.getMinDistance());
		minHeap.offer(source);
		
		while (!minHeap.isEmpty()) {
			Vertex u = minHeap.poll();
			for (Edge adj : u.getAdjacencies()) {
				Vertex v = adj.getTarget();
				
				double newDistance = u.getMinDistance() + adj.getWeight();
				
				if (cache.containsKey(v) && v.getMinDistance() <= newDistance) {
					continue;
				}
				
				if (newDistance < v.getMinDistance()) {
					cache.put(v.setPrevious(u).setMinDistance(newDistance), newDistance);
					minHeap.offer(v);
				}
			}
		}
		
	}
}
