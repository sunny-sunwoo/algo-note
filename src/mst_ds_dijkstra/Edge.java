package mst_ds_dijkstra;

public class Edge {
	private final int a;
	private final int b;
	private final double weight;
	
	public Edge(int a, int b, double weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
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
