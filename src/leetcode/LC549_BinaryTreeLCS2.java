package leetcode;

public class LC549_BinaryTreeLCS2 {
	int maxLen = 0;
    public int longestConsecutive(TreeNode root) {
        getMaxLen(root);
        return maxLen;
    }
    
    private Pair getMaxLen(TreeNode node) {
        if (node == null) {
            return Pair.of(0,0);
        }
        
        int dec = 0, inc = 0;
        int currVal = node.val;
        
        TreeNode leftNode = node.left;
            Pair leftPair = getMaxLen(leftNode);
            if (leftNode == null || currVal - 1 == leftNode.val) {
                dec = leftPair.dec;
            } else if (leftNode == null || currVal + 1 == leftNode.val) {
                inc = leftPair.inc;
            }

        
        TreeNode rightNode = node.right;
            Pair rightPair = getMaxLen(rightNode);
            if (rightNode == null || currVal - 1 == rightNode.val) {
                dec = Math.max(dec, rightPair.dec);
            } else if (rightNode == null || currVal + 1 == rightNode.val) {
                inc = Math.max(inc, rightPair.inc);
            }

        
        maxLen = Math.max(maxLen, dec + inc + 1);
        return Pair.of(dec + 1, inc + 1);
    }
    
    private static class Pair {
        int dec;
        int inc;
        
        private Pair(int dec, int inc) {
            this.dec = dec;
            this.inc = inc;
        }
        
        public static Pair of(int dec, int inc) {
            return new Pair(dec, inc);
        }
    }
    
    private static class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    }
}
