package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * [1, 2, 3]
 * [2, 5, 7, 8]
 * [4, 5, 6, 7]
 * 
 * => Node of value and iterator 
 * => add next if remaining has next
 * 
 * @author sunnypark
 *
 */
public class Q2_KNumberOfSortedArraySorting {
	public static List<Integer> mergeSortedArray(List<List<Integer>> lists) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getValue));
		
		for (List<Integer> list : lists) {
			Iterator<Integer> iterator = list.iterator();
			if (!iterator.hasNext()) {
				continue;
			}
			pq.add(new Node(iterator.next(), iterator));
		}
		
		List<Integer> res = new ArrayList<>();
		while (!pq.isEmpty()) {
			Node top = pq.poll();
			if (top.remaining.hasNext()) {
				pq.offer(new Node(top.remaining.next(), top.remaining));
			}
			res.add(top.getValue());
		}
		
		return res;
	}
	
	private static class Node {
		int val;
		Iterator<Integer> remaining;
		
		public Node(int val, Iterator<Integer> remaining) {
			this.val = val;
			this.remaining = remaining;
		}
		public int getValue() {
			return this.val;
		}
	}
	
	public static void main(String[] args) {
		List<List<Integer>> input = List.of(List.of(1, 2, 3), List.of(2, 5, 7, 8), Arrays.asList(4, 5, 6, 7));
		System.out.println(mergeSortedArray(input));
	}
}
