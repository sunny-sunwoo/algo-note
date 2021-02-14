package list;
/**
 * Q10. reverse k group (ver1)
 * 
 * e.g. 
 * input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8, k = 3
 * output: 3-2-1-6-5-4-8-7 
 *  
 *  
 * [approach] reverse recursively 
 * 1) reverse current group
 *    => while curr != null && cnt > 0
 * 
 * 2) reverse next group if remaining
 *    => if curr != null ? 
 *   	reversed = reverse(curr, k)
 *   	head.next = reversed 
 * 
 * 3) return prev
 * 
 * 
 * [note] reverse single group?
 *  null      7     8 
 *  prev <- curr
 *  
 * @author sunnypark
 *
 */
public class Q10_ReverseKgroup1 {
	public static ListNode reverseKgroup(ListNode head, int k) {
		ListNode prev = null;
		ListNode curr = head;
		
		int cnt = 0;
		// reverse curr group
		while (curr != null && cnt < k) {
			ListNode tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
			cnt++;
		}
		
		// link with next reversed group if remaining
		if (curr != null) {
			ListNode reversed = reverseKgroup(curr, k);
			head.next = reversed;
		}
		
		return prev;
	}
	
	public static void main(String[] args) {
		ListNode l0 = new ListNode();
		ListNode l1 = new ListNode();
		ListNode l2 = new ListNode();
		ListNode l3 = new ListNode();
		ListNode l4 = new ListNode();
		ListNode l5 = new ListNode();
		ListNode l6 = new ListNode();
		ListNode l7 = new ListNode();

		l0.setNext(l1).setNext(l2).setNext(l3).setNext(l4).setNext(l5).setNext(l6).setNext(l7);

		System.out.print(reverseKgroup(l0, 3)); // 2-1-0-5-4-3-7-6
	}
}
