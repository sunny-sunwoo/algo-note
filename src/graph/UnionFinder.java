package graph;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Disjoint Set: data structure that keeps track of a set of elements 
 * partitioned into a number of disjoint(non-overlapping) subsets.
 * 
 * Union-find algorithm: performs 2 operations, find and union.
 * 1) find: determine which subset a particular element is in. 
 * 2) union: join 2 subsets into a single subset.
 * 
 * [Disjoint Set]
 * Data structure to check points are connected efficiently.
 * 3 -> 2, 2 -> 1 equals to 3 - 2 - 1 (all connected)
 *     1          
 *    /                2
 *   2       vs       / \
 *  /                3   1  
 * 3            (more efficient)
 * 
 * Add graph in the form of tree
 * by tracking parent pointers & rank. 
 * 
 * Worst case: LogN (balanced binary tree form)
 * Reference: https://www.geeksforgeeks.org/disjoint-set-data-structures/ 
 *
 * @author sunnypark
 *
 */
public class UnionFinder {
	private final int[] parents;
	private final int[] ranks;
	
	public UnionFinder(int numOfNodes) {
		parents = new int[numOfNodes];
		ranks = new int[numOfNodes];
		
		for (int i = 0; i < numOfNodes; i++) {
			parents[i] = i;
			ranks[i] = 1;
		}
	}
	
	public int getUniqParentNum() {
		return IntStream.of(parents).boxed().collect(Collectors.toSet()).size();
	}
	
	public boolean isConnected(int u, int v) {
		return find(u) == find(v);
	}
	
	/**
	 * determines which subset(parent num) the given vertex is in.
	 * keeps updating internally!!!
	 * @param vertex vertex number
	 * @return parent vertex
	 */
	public int find(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}
		parents[vertex] = find(parents[vertex]);
		return parents[vertex];
	}
	
	public UnionFinder union(int u, int v) {
		int uParent = find(u);
		int vParent = find(v);
		
		if (uParent == vParent) {
			return this;
		}
		
		if (ranks[uParent] > ranks[vParent]) {
			parents[vParent] = uParent;
		} else {
			parents[uParent] = vParent;
		}
		
		// update rank
		if (ranks[uParent] == ranks[vParent]) {
			ranks[vParent]++;
		}
		
		return this;
	}

}
