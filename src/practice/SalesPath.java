package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Q. return the cheapest cost path from root(company) to leaf(dealership)
 * 
 *    0
 *  5 3 6
 * 4    1 5
 * 
 * [approach] recursion
 * base case: 
 *  i) null node? rt 0
 *  ii) no child? rt curr val
 *  
 * logic: iter thru children
 *   recursive call w/ child node
 *   keep min
 *   
 * rt curr cost + min
 * 
 *  time: O(N)
 *  space: O(N) // stack trace
 *  
 * [edge case]
 *  what if, child node is null? => skip
 *  
 * [optimization]
 *  keep min val, exclude any path if the cost > min val
 *  
 * @author sunnypark
 *
 */
public class SalesPath {
	public static int getMinCost(TrieNode root) {
		// base case 1: null node
		if (root == null) {
			return 0;
		}
		
		// base case 2: leaf node
		if (root.children == null || root.children.isEmpty()) {
			return root.cost;
		}
		
		// logic
		int min = Integer.MAX_VALUE;
		for (TrieNode child : root.children) {
			if (child == null || child.cost > min) { // prunning: exclude unnecessary path
				continue;
			}
			min = Math.min(min, getMinCost(child));
		}
		
		// return
		return root.cost + min;
	}
	
	private static class TrieNode {
		int cost;
		List<TrieNode> children;
		
		TrieNode(int cost) {
			this.cost = cost;
			children = new ArrayList<>();
		}
	}
/*
 *      0
 *  5   20   6
 * 10       1 5 10
 *          2
 * */
	public static void main(String[] args) {
		TrieNode n1 = new TrieNode(0);
		TrieNode n2 = new TrieNode(5);
		TrieNode n3 = new TrieNode(20);
		TrieNode n4 = new TrieNode(6);
		TrieNode n5 = new TrieNode(10);
		TrieNode n6 = new TrieNode(1);
		TrieNode n7 = new TrieNode(5);
		TrieNode n8 = new TrieNode(10);
		TrieNode n9 = new TrieNode(2);
		
		n1.children = List.of(n2, n3, n4);
		n2.children = List.of(n5);
		n4.children = List.of(n6, n7, n8);
		n6.children = List.of(n9);
		
		System.out.println(getMinCost(n1));
	}
}
