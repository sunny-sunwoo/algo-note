package graph;

import java.util.List;

public class Graph {
	List<Edge> edges;
	
	public Graph(List<Edge> edges) {
		this.edges = edges;
	}
	
	public int size() {
		return edges.size();
	}
	
	public List<Edge> getAllEdges() {
		return edges;
	}
}
