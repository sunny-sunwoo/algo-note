package graph;

import java.util.PriorityQueue;

/**
 * LC 1168
 * 
	1. build edges
	  [1,2,1], [2,3,1], [0,1,1], [0,2,2], [0,3,2]
	  
	pq = minHeap of edges (sort by edge[2])
	2. while pq is not empty
	 poll the top
	 if connected? ctn
	 
	 connect
	 increment the total cost by curr edge cost
	 
	 if all connected? rt 
	   
	3. rt total
	
	union finder => how to check connection efficiently
	int[] uf => [0, 1, 2, 3]
	 1) find
	   if uf[idx] = idx ? 
	      => rt idx
	      
	    otherwise? recursive call 
	       => rt find(uf[idx])
	 
	 2) union x,y
	  // prunning: check if x,y merged already
	    uf[x] = y
	    
[time complexity]
   Total time complexity: O(ElogE+E+V) 
    - sort:O(ElogE) 
	- find operations:O(1)*2E = O(E) b/c find operation for weighted union-find with path compression should be average O(1)
	- initialize union-find:O(V)
   
   Total space complexity:O(V+E)
    - O(E):List<int[]> edges    O(V):UnionFind
 * @author sunnypark
 *
 */
public class LC1168_WaterDistributionInVillage {
	int[] uf;
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        init(minHeap, n, wells, pipes);
        
        uf = new int[n + 1];
        initUnionFinder();
        int totalCost = 0;
        
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            if (isConnected(top[0], top[1])) {
                continue;
            }
            union(top[0], top[1]);
            totalCost += top[2];
        }

        return totalCost;
    }
    
    private boolean isConnected(int u, int v) {
        return find(u) == find(v);
    }
    
    private int find(int v) {
        if (uf[v] == v) {
            return v;
        }
        return uf[v] = find(uf[v]);
    }
    
    private void union(int u, int v) {
        if (isConnected(u, v)) {
            return;
        }
        int uParent = uf[u];
        int vParent = uf[v];
        uf[uParent] = vParent;
    }
    
    private void initUnionFinder() {
        for (int i = 0; i < uf.length; i++) {
            uf[i] = i;
        }
    }
    
    private void init(PriorityQueue<int[]> minHeap, int n, int[] wells, int[][] pipes) {
        for (int[] pipe : pipes) {
            minHeap.offer(pipe);
        }
        for (int i = 0; i < wells.length; i++) {
            int[] newEdge = new int[]{0, i + 1, wells[i]};
            minHeap.offer(newEdge);
        }
    }
}
