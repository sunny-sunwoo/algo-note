package practice;

public class LargestSmallerBSTKey {
	static class BinarySearchTree {
		Node root;
//		int candidate;
//
//		public int findLargestSmallerKey(int num) {
//			candidate = -1;
//			findLargestSmallerKey(root, num);
//			return candidate;
//		}
//
//		private void findLargestSmallerKey(Node node, int num) {
//			if (node == null) {
//				return;
//			}
//
//			if (node.key >= num) { // no candidate condition
//				findLargestSmallerKey(node.left, num);
//			} else { // keep candidate only when curr val < num
//				candidate = node.key;
//				findLargestSmallerKey(node.right, num);
//			}
//		}
		
		public int findLargestSmallerKey(int num) {
			return findLargestSmallerKey(root, num, Integer.MIN_VALUE);
		}
		
		private int findLargestSmallerKey(Node node, int target, int maxVal) {
			if (node == null) {
				return maxVal == Integer.MIN_VALUE ? -1 : maxVal;
			}
			
			int ret = -1;
			if (node.key >= target) { // move to left
				ret = findLargestSmallerKey(node.left, target, maxVal);
			} else { // keep candidate, and move down to right
				maxVal = Math.max(maxVal, node.key);
				ret = findLargestSmallerKey(node.right, target, maxVal);
			}

			return ret;
		}

		void insert(int key) {
			// 1) If the tree is empty, create the root
			if(this.root == null) {
				this.root = new Node(key);
				return;
			}

			// 2) Otherwise, create a node with the key
			//    and traverse down the tree to find where to
			//    to insert the new node
			Node currentNode = this.root;
			Node newNode = new Node(key); 

			while(currentNode != null) {
				if(key < currentNode.key) {
					if(currentNode.left == null) {
						currentNode.left = newNode;
						newNode.parent = currentNode;
						break;
					} else {
						currentNode = currentNode.left;
					}
				} else {
					if(currentNode.right == null) {
						currentNode.right = newNode;
						newNode.parent = currentNode;
						break;
					} else {
						currentNode = currentNode.right;
					}
				}
			}
		}
	}
	static class Node {
		int key;
		Node left;
		Node right; 
		Node parent;

		Node(int key) {
			this.key = key;
			left = null;
			right = null;
			parent = null;
		}
	}


	public static void main(String[] args) {

		// Create a Binary Search Tree
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(20);
		bst.insert(9);
		bst.insert(25);
		bst.insert(5);
		bst.insert(12);
		bst.insert(11);
		bst.insert(14);

		int result1 = bst.findLargestSmallerKey(26);
		System.out.println("Largest smaller number is " + result1); // 25

		int result2 = bst.findLargestSmallerKey(20);
		System.out.println("Largest smaller number is " + result2); // 14

		int result3 = bst.findLargestSmallerKey(0);
		System.out.println("Largest smaller number is " + result3); // -1

	}
}
