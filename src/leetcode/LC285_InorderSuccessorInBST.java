package leetcode;

/**
 * LC285
 * 
	(approach1) inorder traverse O(N)
	base case1: null? rt null
	
	inorder(node.left)
	
	1. isFound and successor exists ? rt
	2. isFound? set successor, rt
	3. curr val is target? isFound true 
	
	inorder(node.right)
	
	(approach2) use bst O(LogN) => iterative
	if curr val > target val?
	   keep candidate
	   move to left
	else? 
	   move to right
	   
	 => successor will come at the bottom most node anyways.
 * @author sunnypark
 *
 */
public class LC285_InorderSuccessorInBST {
	public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
	
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}
}
