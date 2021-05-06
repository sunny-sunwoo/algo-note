package graph;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Union Finder without rank arr (simple ver.)
 *  => keeps track of partitioned sets status
 * 
 * 1. find(int u) => rt parent w/recursion
 *     
 * 2. union(int u, int v) => connect u,v
 * 
 * 3. helper: isConnected, getUniqueNum
 * 
 * @author sunnypark
 *
 */
public class UnionFinder_NoRank {
	int[] tracker;
	
	public UnionFinder_NoRank(int n) {
		tracker = new int[n];
		for (int i = 0; i < n; i++) {
			tracker[i] = i;
		}
	}
	
	public int find(int u) {
		if (tracker[u] == u) {
			return u;
		}
		
		return tracker[u] = find(tracker[u]);
	}
	
	public void union(int u, int v) {
		if (isConnected(u,v)) {
			return;
		}
		
		int uParent = tracker[u];
		int vParent = tracker[v];
		
		tracker[uParent] = vParent;
	}
	
	private boolean isConnected(int u, int v) {
		int uParent = find(u);
		int vParent = find(v);
		return uParent == vParent;
	}
	
	public int getUniqueNumber() {
		return IntStream.of(tracker).boxed().collect(Collectors.toSet()).size();
	}
	
}
