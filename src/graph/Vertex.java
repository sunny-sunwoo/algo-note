package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
	int id;
	Vertex previous;
	List<Edge> adjacencies;
	double distanceFromSrc;
	
	public Vertex(int id) {
		this.id = id;
		this.adjacencies = new ArrayList<>();
		this.distanceFromSrc = Double.MAX_VALUE;
	}
	Vertex setMinDistance(double dist) {
		distanceFromSrc = dist;
		return this;
	}
	
	Vertex setPrevious(Vertex previous) {
		this.previous = previous;
		return this;
	}
	
	Vertex getPrevious() {
		return this.previous;
	}
	
	void addEdge(Edge newEdge) {
		this.adjacencies.add(newEdge);
	}
	
	List<Edge> getAdjacencies() {
		return this.adjacencies;
	}
	
	int getName() {
		return this.id;
	}
	
	double getMinDistance() {
		return this.distanceFromSrc;
	}
	
    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.distanceFromSrc, other.distanceFromSrc);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, Double.doubleToLongBits(distanceFromSrc));
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        
        if (!(other instanceof Vertex)) {
            return false;
        }
        
        Vertex o = (Vertex) other;
        return this.id == o.id;
    }
    
    @Override
    public String toString() {
        return "name: " + id + "\t" + adjacencies;
    }
}
