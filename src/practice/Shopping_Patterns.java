package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * shopping patterns
 * 
 * 1 = 2,3
 * 2 = 1,3,4
 * 3 = 1,2,4
 * 4 = 2,3,5
 * 5 = 4
 * 
 * => (1,2,3) +2
 * => (2,3,4) +3
 * 
 *     1 - 2
 *     | /  \
 *     3 --- 4 --- 5
 * 
 * [logic] public level
 * - find all trios
 *     sol1) using dfs
 *     sol2) brute force: checking all edges
 *     
 * - for each trio:
 *     => for nbrs from each node: 
 *          cnt++ if nbr is not in the trio
 *	   => keep min
 * 
 * [logic] buildTrioList with dfs
 * 1. build path from each node
 * 2. fill trioList(result) through dfs
 *     - base case: when path size reaches 3, add path if curr node contains origin
 *     - logic: for all neighbors, build path
 * 
 * @author sunnypark
 *
 */
public class Shopping_Patterns {
	public static Map<Integer, Set<Integer>> graph;
	public static int getMinScore(int nodesNum, int edgesNum, int[] from, int[] to) {
		init(nodesNum, edgesNum, from, to);
		Set<Set<Integer>> trioList = buildTrioList();
		System.out.println(trioList); // expected: (2,3,4), (3,4,5)

		if (trioList.size() == 0) {
			return -1;
		}
		
		int minScore = Integer.MAX_VALUE;
		
		for (Set<Integer> trio : trioList) {
			int cnt = 0;
			for (int currNode : trio) {
				Set<Integer> nbrs = graph.get(currNode);
				for (int nbr : nbrs) {
					if (trio.contains(nbr)) {
						continue;
					}
					cnt++;
				}
			}
			minScore = Math.min(minScore, cnt);
		}
		return minScore;
	}
	
	private static void init(int nodesNum, int edgesNum, int[] from, int[] to) {
		graph = new HashMap<>();
		
		for (int i = 1; i <= nodesNum; i++) {
			graph.put(i, new HashSet<Integer>());
		}
		
		for (int i = 1; i <= edgesNum; i++) {
			graph.get(from[i - 1]).add(to[i - 1]);
			graph.get(to[i - 1]).add(from[i - 1]);
		}
		
		System.out.println(graph); // expected: {1=[2, 3], 2=[1, 3, 4], 3=[1, 2, 4], 4=[2, 3, 5], 5=[4]}
	}
	
	private static Set<Set<Integer>> buildTrioList() {
		Set<Set<Integer>> trioList = new HashSet<>(); // trio list using set for dedup purpose
		for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
			int origin = entry.getKey();
			Set<Integer> nbrs = entry.getValue();
			
			for (int nbr: nbrs) {
				Set<Integer> path = new HashSet<>();
				path.add(origin);
				dfs(trioList, path, origin, nbr);
			}
			
		}
		return trioList;
	}
	
	private static void dfs(Set<Set<Integer>> trioList, Set<Integer> path, int origin, int curr) {
		if (path.size() == 2) {
			if (graph.get(curr).contains(origin)) { // trio using set for fast search
				path.add(curr);
				trioList.add(new HashSet<>(path)); // NOTE: DONT FORGET 'NEW'
			}
			return;
		}
		
		for (int next : graph.get(curr)) {
			if (next < curr) {
				continue;
			}
			path.add(curr);
			dfs(trioList, path, origin, next);
			path.remove(curr);
		}
	}
	
	// brute force approach
	// by checking all edges
	private static Set<Set<Integer>> buildTrioList_bruteforce(Map<Integer, Set<Integer>> adjList) {
		Set<Set<Integer>> trioList = new HashSet<>();
		
		for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet()) {
			if (entry.getValue().size() < 2) {
				continue;
			}
			List<Pair<Integer, Integer>> edgeList = buildEdges(entry.getValue());
			for (Pair<Integer, Integer> edge : edgeList) {
				int first = entry.getKey();
				int second = edge.first;
				int third = edge.second;
				
				if (isConnected(second, third, adjList)) {
					trioList.add(new HashSet<Integer>() {{ add(first); add(second); add(third); }});
				}
			}
		}
		return trioList;
	}
	
	private static List<Pair<Integer, Integer>> buildEdges(Set<Integer> nbrs) {
		List<Pair<Integer, Integer>> edgeList = new ArrayList<>();
		List<Integer> nbrList = nbrs.stream().collect(Collectors.toList());
		for (int i = 0; i < nbrList.size(); i++) {
			for (int j = i + 1; j < nbrList.size(); j++) {
				edgeList.add(new Pair<Integer, Integer>(nbrList.get(i), nbrList.get(j)));
			}
		}
		return edgeList;
	}
	
	private static boolean isConnected(int second, int third, Map<Integer, Set<Integer>> adjList) {
		return adjList.get(second).contains(third);
	}
	
	public static class Pair<K,V> {
		K first;
		V second;
		
		Pair(K key, V value) {
			this.first = key;
			this.second = value;
		}
	}

	/**
	 * main method.
	 * 
	 * input for testing: 
5 6
1 2
1 3
2 3
2 4
3 4
4 5
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
        String[] firstLine = bufferedReader.readLine().split(" ");
        int products_nodes = Integer.parseInt(firstLine[0]);
        int products_edges = Integer.parseInt(firstLine[1]);

        int[] products_from = new int[products_edges];
        int[] products_to = new int[products_edges];
        
        int cnt = 0;
        while (cnt < products_edges) {
        	String[] currLine = bufferedReader.readLine().split(" ");
        	int from = Integer.parseInt(currLine[0]);
        	int to = Integer.parseInt(currLine[1]);
        	products_from[cnt] = from;
        	products_to[cnt] = to;
        	cnt++;
        }

        bufferedReader.close();
        System.out.println(getMinScore(products_nodes, products_edges, products_from, products_to)); // expected: 2
    
//		Map<Integer, Set<Integer>> adjList = new HashMap<>();
//		adjList.put(1, new HashSet<Integer>() {{ add(2); add(3); }});
//		adjList.put(2, new HashSet<Integer>() {{ add(1); add(3); add(4); }});
//		adjList.put(3, new HashSet<Integer>() {{ add(1); add(2); add(4); }});
//		adjList.put(4, new HashSet<Integer>() {{ add(2); add(3); add(5); }});
//		adjList.put(5, new HashSet<Integer>() {{ add(4);}});
	}
}
