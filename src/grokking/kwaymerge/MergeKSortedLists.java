package grokking.kwaymerge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists {
	public static List<Integer> mergeKSortedLists(List<List<Integer>> input) {
		List<Integer> res = new ArrayList<>();
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> (a.getValue() - b.getValue()));
		
		for (List<Integer> each : input) {
			Iterator<Integer> itr = each.iterator();
			pq.offer(new Node(itr.next(), itr));
		}
		
		while(!pq.isEmpty()) {
			Node top = pq.poll();
			res.add(top.value);
			if (top.hasNext()) {
				pq.offer(new Node(top.itr.next(), top.itr));
			}
		}
		return res;
	}
	
	private static class Node {
		int value;
		Iterator<Integer> itr;
		
		Node(int val, Iterator<Integer> itr) {
			this.value = val;
			this.itr = itr;
		}
		int getValue() {
			return value;
		}
		
		boolean hasNext() {
			return itr.hasNext();
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list1 = List.of(2,5,7);
		List<Integer> list2 = List.of(1,3,8);
		List<Integer> list3 = List.of(0,2,10);
		
		System.out.println(mergeKSortedLists(List.of(list1, list2, list3)));
	}
}
