package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Type2_Traverse {

	/**
	 * q1. inorder traversal
	 */
	public static List<Integer> inorderTraverse(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		inorder(result, root);
		return result;
	}
	
	private static void inorder(List<Integer> result, TreeNode node) {
		if (node == null) return;
		
		inorder(result, node.left);
		result.add(node.val);
		inorder(result, node.right);
	}
	
	/**
	 * q2. find k numbers close to t
	 * 
	 * sol. inorder traversal
	 * logic when adding nums to the result list
	 * - check if list size > k
	 * - compare the longest(oldest) dist vs curr(newest) dist
	 *   -> if prevDist < newDist ? stop traversing
	 *   -> otherwise, remove the farthest(oldest) num
	 *   
	 * 
	 * remember.
	 * - use LL instead of AL to access both ends efficiently
	 * - LinkedList<I> result instead of List<I>
	 *    => to use getFirst(), getLast()
	 * 
	 */
	public static List<Integer> findCloseNumbers(TreeNode root, int t, int k) {
		LinkedList<Integer> result = new LinkedList<>();
		find(result, root, t, k);
		return result;
	}

	private static void find(LinkedList<Integer> result, TreeNode node, int t, int k) {
		if(node == null) return;
		find(result, node.left, t, k);
		
		result.add(node.val);
		if(result.size() > k) {
			int prevDist = Math.abs(t - result.getFirst()); 
			int newDist = Math.abs(t - result.getLast());
			
			if (prevDist < newDist) {
				result.removeLast(); 
				return; // pruning
			} else {
				result.removeFirst();
			}
		}
		
		find(result, node.right, t, k);
		
	}
	
	
}
