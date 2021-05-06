package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LC 1192 Critical Connections in a Network
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * 
 * [approach] tarjan algorithm
 *  - dfs using ranks array 
 *  - logic
 *  	1) proceed to next node with rank++
 *  	2) can't come back to previous node (proceed in 1 dir)
 *  	3) after dfs, 
 *  		=> curr rank == next rank if there is cycle.
 *  	    => curr rank < next rank (gap exists) if it's a critical connection
 *
 *
 * when exploring 0 -> 1 -> 2
 * 
	  (0,1)  (1,2) => after dfs, (1, 1)	
		0 ---  1 
		\     /
		   2
		 (2,3) => (2,1)
 *
 * e.g. 
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * @author sunnypark
 *
 */
public class LC1192_CriticalConnectionsInANetwork {
	public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		Map<Integer, Set<Integer>> graph = init(connections);
		List<List<Integer>> result = new ArrayList<>();
		int[] ranks = new int[n];
		dfs(result, graph, ranks, -1, 0, 1);
		return result;
	}
	
	private static void dfs(List<List<Integer>> result, Map<Integer, Set<Integer>> graph, int[] ranks, int prev, int curr, int rank) {
		ranks[curr] = rank;
		for (int next : graph.getOrDefault(curr, new HashSet<>())) {
			if (next == prev) {
				continue;
			}
			
			if (ranks[next] == 0) {
				dfs(result, graph, ranks, curr, next, rank + 1);
			}
			
			if (ranks[next] > rank) {
				result.add(List.of(curr, next));
			} else {
				ranks[curr] = ranks[next];
			}
		}
	}
	
	private static Map<Integer, Set<Integer>> init(List<List<Integer>> connections) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (List<Integer> connection : connections) {
			int from = connection.get(0);
			int to = connection.get(1);
			
			graph.computeIfAbsent(from, (unused) -> new HashSet<>()).add(to);
			graph.computeIfAbsent(to, (unused) -> new HashSet<>()).add(from);
		}
		return graph;
	}

	public static void main(String[] args) {
		List<List<Integer>> connections = List.of(List.of(0,1), List.of(1,2), List.of(1,3), List.of(2,0), List.of(3,4));
		System.out.println(criticalConnections(5, connections)); // expected: [[3, 4], [1, 3]]

	}
}
