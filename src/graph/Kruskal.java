package graph;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
/**
 * Kruskal Algorithm
 * finds a minimum spanning forest of an undirected edge-weighted graph.
 * if the graph is connected, it finds a minimum spanning tree.
 * 
 * - greedy algorithm: paradigm that builds up a solution piece by piece,
 * always choosing the next piece that offers the most obvious and immediate benefit.
 * 
 * An optimization problem can be solved using Greedy 
 * if the problem has the following property: At every step, 
 * we can make a choice that looks best at the moment, 
 * and we get the optimal solution of the complete problem.
 * 
 * 
 * minimum spanning tree = subset of edges that forms a tree that includes every vertex, 
 * where the sum of weights of all the edges in the tree is minimized.
 * 
 * [implementation]
 * => needs unionFinder, minHeap, and mst
 * 1. offer all edges to minHeap
 * 2. populate mst while minHeap is not empty
 *     - keep polling the head edge.
 *     - if both ends connected? ctn
 *     - otherwise, (1)connect (2)add to mst
 *     
 * 3. rt mst 
 * 
 * => time complexity: O(ELogE)
 * 
 * @author sunnypark
 *
 */
public class Kruskal {
	public Deque<Edge> findMinimumSpanningTree(Graph g) {
		Deque<Edge> mst = new ArrayDeque<>();
		UnionFinder uf = new UnionFinder(g.size() + 1);
		PriorityQueue<Edge> minHeap = populate(g);
		
		while (!minHeap.isEmpty()) {
			Edge currEdge = minHeap.poll();
			
			int u = currEdge.either();
			int v = currEdge.other(u);
			
			if (uf.isConnected(u, v)) {
				continue;
			}
			
			uf.union(u, v);
			mst.addLast(currEdge);
		}
		return mst;
	}
	
	private PriorityQueue<Edge> populate(Graph g) {
		PriorityQueue<Edge> mh = new PriorityQueue<>();
		for (Edge e : g.getAllEdges()) {
			mh.offer(e);
		}
		return mh;
	}
}
