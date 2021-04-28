package leetcode;

import leetcode.LC543_TreeDiameter.TreeNode;

/**
 * LC 298
 * 
choice: depending on curr val
 i) prev val + 1 ? continue sequence 
     => helper(curVal, lenSoFar + 1)
     
 ii) otherwise ? restart sequence  VS length so far 
     => max of lenSoFar VS helper(curVal, 1)

 base case: null
     => return lenSoFar
 * @author sunnypark
 *
 */
public class LC298_BinaryTreeLCS {
	public static int longestConsecutive(TreeNode root) {
        return getMaxLCS(root, Integer.MAX_VALUE, 1);
    }
    
    private static int getMaxLCS(TreeNode node, int prevVal, int lenSoFar) {
        if (node == null) {
            return lenSoFar;
        }
        
        if (node.val != prevVal + 1) {
            return Math.max(lenSoFar, Math.max(getMaxLCS(node.left, node.val, 1), getMaxLCS(node.right, node.val, 1)));
        }
        
        return Math.max(getMaxLCS(node.left, node.val, lenSoFar + 1), getMaxLCS(node.right, node.val, lenSoFar + 1));
    }
    
    private static class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    }
}
