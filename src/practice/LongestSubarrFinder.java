package practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Problem 2: Given an array find the length of the longest subarray whose first element is greater than the last element
 * 
 * e.g.
 *   
 *   |
 * [-10,5,3,-7,-6]
 *   ^         ^ 
 *  <---------->
 *  
 *  queue => (0,4)
 *        => (0,3) (1,4)
 *        => (0,2),(1,3),(1,3),(2,4) // Dedup using visited
 *  
 *  time complexity: O(N^2)
 *  
 * @author sunnypark
 *
 */
public class LongestSubarrFinder {
	public static int findLongestSubarr(int[] nums) {
		Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
		Set<Pair<Integer, Integer>> visited = new HashSet<>();
		
		int lo = 0, hi = nums.length - 1;
		Pair<Integer, Integer> currPair = Pair.of(lo, hi); 
		queue.offer(currPair);
		visited.add(currPair);
		
		// visited
		while(!queue.isEmpty()) {
			Pair<Integer, Integer> top = queue.poll();
			lo = top.left;
			hi = top.right;

			if (nums[lo] > nums[hi]) {
				return top.getLength();
			}

			if (lo + 1 <= hi && lo + 1 < nums.length) {
				Pair<Integer, Integer> leftPair = Pair.of(lo + 1, hi);
				if (visited.contains(leftPair)) {
					continue;
				}
				queue.offer(leftPair);
			}
			
			if (lo <= hi - 1 && hi - 1 > 0) {
				Pair<Integer, Integer> rightPair = Pair.of(lo, hi - 1);
				if (visited.contains(rightPair)) {
					continue;
				}
				queue.offer(Pair.of(lo, hi - 1));
			}
		}
		return -1;
	}
	
	private static class Pair<K,V> {
		int left;
		int right;
		private Pair(int left, int right) {
			this.left = left;
			this.right = right;
		}
		
		public static Pair<Integer,Integer> of(int left ,int right) {
			return new Pair<Integer, Integer>(left, right);
		}
		
		public int getLength() {
			return right - left + 1;
		}
		
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			
			Pair other = (Pair) o;
			return this.left == other.left && this.right == other.right;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(left, right);
		}
		
	}
	
	public static void main(String[] args) {
		int[] nums = {-2,-8,3,-7,-1}; // 4
		System.out.println(findLongestSubarr(nums));
	}
}
