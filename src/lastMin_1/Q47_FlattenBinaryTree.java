package lastMin_1;

/**
 * 
 * 1) ITERATIVE - find leftMostRight - clean up the left side - move forward to
 * the right side
 * 
 * 2) RECURSIVE - post-order - clean up from the right bottom and come back up
 * 
 * @author sunnypark
 *
 */
public class Q47_FlattenBinaryTree {
	public void flatten_iterative(TreeNode root) {
		TreeNode node = root;
		while (node != null) {
			if (node.left != null) {
				TreeNode leftMostRight = findLeftMostRight(node.left);
				leftMostRight.right = node.right;
				node.right = node.left;
				node.left = null;
			}
			node = node.right;
		}
	}

	private TreeNode findLeftMostRight(TreeNode node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	public void flatten_recursive(TreeNode root) {
		TreeNode[] prev = new TreeNode[1];
		flatten(prev, root);
	}

	private void flatten(TreeNode[] prev, TreeNode node) {
		if (node == null) {
			return;
		}
		flatten(prev, node.right);
		flatten(prev, node.left);
		node.right = prev[0];
		node.left = null;
		prev[0] = node;
	}

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

}
