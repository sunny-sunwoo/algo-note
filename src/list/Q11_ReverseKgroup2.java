package list;
/**
 * Q11. reverse k group (ver2)
 * Reverse except for the remaining part
 * 
 * e.g. 
 * input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8, k = 3
 * output: 3-2-1-6-5-4-7-8
 *                     <=> don't reverse the remaining part 
 *  
 * [approach] focus on public level!
 * 
 *   6-5-4-7-8
 *       ^
 *   
 *   preprocess! 
 *     => verify if curr group has k nodes
 *   
 *   if not(= no stride)? no need to reverse
 *      => rt head 
 *   
 *   otherwise,
 *      1) reverse curr group
 *      
 *      2) link with next if remaining exists
 *      remaining = stride.next
 *      reversedNext = reverseKgroup(remaining) 
 *      stride.next = reversedNext
 *      
 *   rt reversed curr group
 *   
 * @author sunnypark
 * 
 */
public class Q11_ReverseKgroup2 {
	public static ListNode reverseKgroupWithoutTail(ListNode head, int k) {
		ListNode stride = preprocess(head, k);
		if (stride == null) {
			return head;
		}
		
		ListNode remaining = stride.next;
		ListNode reversed = reverse(head, k);
		
		if (remaining != null) {
			ListNode reversedNext = reverseKgroupWithoutTail(remaining, k);
			head.next = reversedNext;
		}

		return reversed;
	}
	
	private static ListNode preprocess(ListNode node, int k) {
		int cnt = 0;
		ListNode curr = node;
		
		while (curr != null && cnt < k - 1) {
			curr = curr.next;
			cnt++;
		}
		
		return cnt == k - 1 ? curr : null;
	}
	
	private static ListNode reverse(ListNode head, int k) {
		ListNode prev = null;
		ListNode curr = head;
		
		int cnt = 0;
		while (curr != null && cnt < k) {
			ListNode tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
			cnt++;
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

		System.out.print(reverseKgroupWithoutTail(l0, 3)); // 2-1-0-5-4-3-6-7
	}
}
