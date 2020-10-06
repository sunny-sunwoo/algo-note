package tree;

public class Type1_GetDepth {
	/**
	 * q1. get depth - tree
	 */
	public static int getDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
	}
	
	/**
	 * q2. get depth - trie
	 */
	public static int getDepthTrie(TrieNode root) {
		if (root == null) {
			return 0;
		}
		
		int maxDepth = 0;
		for (TrieNode child : root.children) {
			maxDepth = Math.max(maxDepth, getDepthTrie(child));
		}
		
		return maxDepth + 1;
	}
	
	/**
	 * q3. get total
	 */
	public static int getTotal(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		return getTotal(node.left) + node.val + getTotal(node.right);
	}
	
	/**
	 * q4. get total - max path
	 */
	public static int getMaxPathSum(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		return Math.max(getMaxPathSum(node.left), getMaxPathSum(node.right)) + node.val;
	}
	
	/**
	 * q5. validate bst
	 */
	public static boolean validateBST(TreeNode root) {
		return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static boolean validate(TreeNode node, int min, int max) {
		if (node == null) {
			return true;
		}
		
		// take care of the current node only!
		if (node.val < min || node.val > max) {
			return false;
		}
		
		return validate(node.left, min, node.val) && validate(node.right, node.val, max);
	}
	
	public static void main(String[] args) {
		System.out.println(getDepth(TreeNode.generate()));
	}
}
