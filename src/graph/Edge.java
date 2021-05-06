package graph;

public class Edge implements Comparable<Edge>{
	private final int a;
	private final int b;
	private final double weight;
	private Vertex target;
	
	public Edge(int a, int b, double weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	
	// Edge for Dijkstra
	public Edge(Vertex target, double weight) {
		a = -1;
		b = target.id;
		this.target = target;
		this.weight = weight;
	}
	
	public Vertex getTarget() {
		return target;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public int either() {
		return a;
	}
	
	public int other(int vertex) {
		if (vertex == a) {
			return b;
		}
		if (vertex == b) {
			return a;
		}
		throw new IllegalArgumentException("illegal vertex");
	}
	
	@Override
	public int compareTo(Edge other) {
		return Double.compare(this.weight, other.weight);
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("vertex1: ")
				.append(a)
				.append("vertex2: ")
				.append(b)
				.append("weight: ")
				.append(weight)
				.toString();
	}
}
