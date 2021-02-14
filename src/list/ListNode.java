package list;

public class ListNode {
	private static int id;
	int val;
	ListNode next;
	
	public ListNode(int val) {
		this.val = val;
	}
	
	public ListNode() {
		this.val = id;
		id++;
	}
	
	public ListNode setNext(ListNode next) {
		this.next = next;
		return next;
	}
	
	public ListNode getNext() {
		return next;
	}
	
	@Override
	public String toString() {
		if (next == null) {
			return String.valueOf(val);
		}
		return String.valueOf(val) + " -> " + next.toString();
	}
}
