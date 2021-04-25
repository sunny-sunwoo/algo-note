package practice;

/**
 * Q. Longest Arithmetic Sequence in Binary Tree
 * 

   0 
  2 4 
 3   7 
  4   10
   5 => 4
    2 
     -1
     
 *  choice? depending on diff (prev, curr val)
 *   i) prev diff == next diff? 
 *      continue curr sequence w/ len + 1 (add curr node to length so far)
 *   
 *   ii) otherwise (prev diff != next diff)? 
 *       restart new sequence  w/ 1 (reset the length from curr node)
 *   
 *  from each node, 
 *   1. len update
 *   2. 2 recursive calls accordingly to left, right node 
 *     
 * @author sunnypark
 *
 */
public class LongestArithmeticSequenceInBT {
	// SOLUTION 1: using instance variable without return value
	private static int maxLen = 1;
	public static int getMaxArithmeticSequence_instVar(TreeNode root) {
		getMaxArithmeticSequence(root.left, root.val, 2);
		getMaxArithmeticSequence(root.right, root.val, 2);
		return maxLen;
	}
	
	private static void getMaxArithmeticSequence(TreeNode node, int prevVal, int lenSoFar) {
		if (node == null) {
			return;
		}
		
		maxLen = Math.max(maxLen, lenSoFar);
		int currVal = node.val;
		int diffSoFar = prevVal - currVal;
		
		if (node.left != null) {
			int leftDiff = currVal - node.left.val;
			int nextLen = (leftDiff == diffSoFar) ? lenSoFar + 1 : 2;
			maxLen = Math.max(maxLen, nextLen);
			getMaxArithmeticSequence(node.left, currVal, nextLen);
		}

		if (node.right != null) {
			int rightDiff = currVal - node.right.val;
			int nextLen = (rightDiff == diffSoFar) ? lenSoFar + 1 : 2;
			maxLen = Math.max(maxLen, nextLen);
			getMaxArithmeticSequence(node.right, currVal, nextLen);
		}
	}
	
	// SOLUTION 2: return value + parameter, without using instance variable
	public static int getMaxArithmeticSequence(TreeNode root) {
		return findMaxSequence(root, Integer.MAX_VALUE, 1);
	}
	
	private static int findMaxSequence(TreeNode node, int prevDiff, int lenSoFar) {
		if (node == null) {
			return lenSoFar;
		}

		int leftLen = findNextSequence(node, node.left, prevDiff, lenSoFar);
		int rightLen = findNextSequence(node, node.right, prevDiff, lenSoFar);

		return Math.max(leftLen, rightLen);
	}
	
	private static int findNextSequence(TreeNode currNode, TreeNode nextNode, int prevDiff, int lenSoFar) {
		int newDiff = nextNode == null ? Integer.MIN_VALUE : currNode.val - nextNode.val;

		if(prevDiff != newDiff) {
			return Math.max(lenSoFar + 1, findMaxSequence(nextNode, newDiff, 1));
		}

		return findMaxSequence(nextNode, newDiff, lenSoFar + 1);
	}
	
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int val) {
			this.val = val;
		}
		
		@Override
		public String toString() {
			return "TreeNode " + val;
		}
	}
	
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(2);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(7);
		TreeNode t6 = new TreeNode(9);
		
		TreeNode t7 = new TreeNode(4);
		TreeNode t8 = new TreeNode(5);
		TreeNode t9 = new TreeNode(6);
		TreeNode t10 = new TreeNode(2);
		TreeNode t11 = new TreeNode(1);
		
		t1.left = t2;
		t2.left = t3;
		
		t1.right = t4;
		t4.right = t5;
		t5.right = t6;
		
		t3.right = t7;
		t7.right = t8;
		t8.left = t9;
		t9.right = t10;
		t10.right = t11;
		
		System.out.println(getMaxArithmeticSequence_instVar(t1));
		System.out.println(getMaxArithmeticSequence(t1));
	}
}
