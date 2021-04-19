package leetcode;

/**
 * LC 543
 * 
 * 3 options from curr node
 *  i) 	 left depth + right depth
 *  ii)  diameter of left subtree
 *  iii) diameter of right subtree
 *  
 * @author sunnypark
 *
 */
public class LC543_TreeDiameter {
	public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int currDiameter = getDepth(root.left) + getDepth(root.right);
        int childDiameter = Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right));

        return Math.max(currDiameter, childDiameter);
    }
    
    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    private static class TreeNode {
    	TreeNode left;
    	TreeNode right;
    }
}
