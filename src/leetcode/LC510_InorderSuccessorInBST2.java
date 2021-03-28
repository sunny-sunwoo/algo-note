package leetcode;

/**
 * LC 510
 * 
 node from child vs node from parent
 1) find leftmost from right child
 2) => approach1_ find parent with greater value
    => approach2_ go up to leftmost and then one more to right parent
 * @author sunnypark
 *
 */
public class LC510_InorderSuccessorInBST2 {
	public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        
        // 1) candidate from children
        Node leftmostNode = node.right;
        while (leftmostNode != null && leftmostNode.left != null) {
            leftmostNode = leftmostNode.left;
        }
        if (leftmostNode != null) {
            return leftmostNode;
        }

        // 2) candidate from parents
        Node parentNode = node.parent;
        Node possibleParentNode = null;
        while (parentNode != null) {
            if (parentNode.val > node.val) {
                possibleParentNode = parentNode;
                break;
            }
            parentNode = parentNode.parent;
        }
        
        // without using BST property. 
        // instead, go up to left most parent and one more up to right parent
//        Node currNode = node;
//        while (currNode.parent != null && currNode.parent.val < currNode.val) {
//            currNode = currNode.parent;
//        }
//        if (currNode.parent != null) {
//            possibleParentNode = currNode.parent;
//        }

        return possibleParentNode;
    }
	
	private class Node {
		int val;
		Node parent;
		Node left;
		Node right;
	}
}
