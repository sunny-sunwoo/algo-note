package tree;

public class TreeNode {
	static int cnt = 1;
	int val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode() {
		this.val = cnt;
		cnt++;
	}
	
	public TreeNode(int val) {
		this.val = val;
	}
	
	public String getStringVal() {
		return String.valueOf(val);
	}
	
	@Override
	public String toString() {
		if (this.left == null && this.right == null) {
			return "NODE- " + this.val;
		}

		return this.left + " // " + "NODE- " + this.val + " // " + this.right;	
	}
	
	public static TreeNode generate() {
		TreeNode n1 = new TreeNode();
		TreeNode n2 = new TreeNode();
		TreeNode n3 = new TreeNode();
		TreeNode n4 = new TreeNode();
		TreeNode n5 = new TreeNode();
		
		n2.left = n1;
		n2.right = n3;
		n4.left = n2;
		n4.right = n5;
		
		return n4;
	}

}
