package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * LC 1197
 * 
 * Approach: A*
	8 dirs => (-2,-1), (-2,1), (2,-1), (2,1), (-1,-2), (1,-2), (-1,2), (1,2)
	
	Map<Pair, Integer> cache: pos -> num of moves
	PQ<DistRes> minHeap: based on dist (dist to destination)
	
	add (0,0) -> cache and minHeap
	
	while minHeap is not empty
	  top
	  if (x,y) found? => rt value
	  
	  for 8 directions
	    nextY, nextX
	    
	    distToNext > distToCurr + 1 // relaxation
	      => update w/ distToCurr
	      => push to minHeap and cache
	
	helper class:
	 1. Pair - equals, hashCode for HM use
	 2. DistRest - impls Comparable for PQ use
 * @author sunnypark
 *
 */
public class LC1197_MinKnightMoves {
	private final static List<Pair> DIRS = List.of(
	        Pair.of(-2,-1), Pair.of(-2,1), Pair.of(2,-1), Pair.of(2,1),
	        Pair.of(-1,-2), Pair.of(1,-2), Pair.of(-1,2), Pair.of(1,2)
	    );
	    
	    public int minKnightMoves(int x, int y) {
	        Map<Pair, Integer> cache = new HashMap<>();
	        PriorityQueue<DistRes> minHeap = new PriorityQueue<>();
	        
	        Pair src = Pair.of(0,0);
	        Pair dest = Pair.of(x,y);
	        DistRes first = new DistRes(src, getDistToEnd(src,dest));
	        cache.put(src, 0);
	        minHeap.offer(first);
	        
	        while (!minHeap.isEmpty()) {
	            DistRes top = minHeap.poll();
	            Pair currPair = top.pair;
	            if(currPair.first == x && currPair.second == y) {
	                return cache.get(currPair);   
	            }
	            
	            for (Pair dir : DIRS) {
	                Pair nextPair = currPair.toNext(dir);
	                
	                int stepsToNext = cache.getOrDefault(nextPair, Integer.MAX_VALUE);
	                int stepsToCurr = cache.getOrDefault(currPair, Integer.MAX_VALUE);
	                
	                if (stepsToNext > stepsToCurr + 1) {
	                    cache.put(nextPair, stepsToCurr + 1);
	                    minHeap.offer(new DistRes(nextPair, stepsToCurr + 1 + getDistToEnd(nextPair, dest)));
	                }
	            }
	        }
	        throw new IllegalArgumentException("invalid input");
	    }
	    
	    private double getDistToEnd(Pair from, Pair to) {
	        return Math.hypot(from.first - to.first, from.second - to.second);
	    }
	    
	    //TODO: private class Pair, DistRes
	    private static class DistRes implements Comparable<DistRes>{
	        private final double distToEnd;
	        private final Pair pair;
	        
	        private DistRes(Pair pair, double distToEnd) {
	            this.pair = pair;
	            this.distToEnd = distToEnd;
	        }
	        
	        @Override
	        public int compareTo(DistRes other) {
	            return Double.compare(this.distToEnd, other.distToEnd);
	        }
	    }
	    
	    private static class Pair {
	        int first;
	        int second;
	        
	        private Pair(int first, int second) {
	            this.first = first;
	            this.second = second;
	        }

	        private static Pair of(int first, int second) {
	            return new Pair(first, second);
	        }
	        
	        private Pair toNext(Pair delta) {
	            return Pair.of(first + delta.first, second + delta.second);
	        }
	        
	        // overrides equals, hashcode
	        @Override
	        public boolean equals(Object o) {
	            if (!(o instanceof Pair)) {
	                return false;
	            }
	            
	            Pair other = (Pair) o;
	            return this.first == other.first && this.second == other.second;
	        }
	        
	        @Override
	        public int hashCode() {
	            return Objects.hash(first, second);
	        }
	    }
}
