package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Type3 Various Traversal
 *  - use various data structures e.g. stack, queue
 *  - use additional property in node e.g. rank
 * @author sunnypark
 *
 */
public class Type3_VariousTraversal {
	
	/**
	 * q1. zigzag traversal
	 * - 2 stacks (for curr nodes, for next nodes to pass down)
	 * - boolean flag to shift the dir
	 * 
	 * @param root
	 * @return
	 */
	public static List<List<String>> zigzag(TreeNode root) {
		List<List<String>> result = new ArrayList<>();
		Deque<TreeNode> curr = new ArrayDeque<>();
		Deque<TreeNode> next = new ArrayDeque<>();
		
		curr.push(root);
		zigzag(result, curr, next, true);
		return result;
	}
	
	private static void zigzag(List<List<String>>result, Deque<TreeNode> curr, Deque<TreeNode> next, boolean isLeft) {
		List<String> currLv = new ArrayList<>();
		while (!curr.isEmpty()) {
			TreeNode currNode = curr.pop();
			if (currNode == null) {
				continue;
			}
			currLv.add(currNode.getStringVal());
			
			next.push(isLeft ? currNode.left : currNode.right);
			next.push(isLeft ? currNode.right : currNode.left);
		}
		if (!currLv.isEmpty()) {
			result.add(currLv);
		}
		zigzag(result, next, curr, !isLeft);
	}
}
