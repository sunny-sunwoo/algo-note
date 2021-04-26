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
	// SOLUTION 1
	public int diameterOfBinaryTree_linear(TreeNode root) {
        return getDiameter(root).diameter;
    }
    
    private Pair getDiameter(TreeNode node) {
        if (node == null) {
            return new Pair(0,0);
        }
        
        Pair left = getDiameter(node.left);
        Pair right = getDiameter(node.right);
        
        int bestDiameter = Math.max(left.diameter, right.diameter);
        bestDiameter = Math.max(bestDiameter, left.height + right.height);
        
        int height = Math.max(left.height, right.height) + 1;
        return new Pair(bestDiameter, height);
    }
    
    private static class Pair {
        int diameter;
        int height;
        
        public Pair(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }
    
    // SOLUTION 2
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
