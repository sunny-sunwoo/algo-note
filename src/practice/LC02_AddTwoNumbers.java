package practice;
/**
 * 
 * LC02 Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * 
	2-4-3
	5-6-4
	-----
	7-0-8
 * 
 * - dummyHead for rt val (dummyHead.next)
 * - node ptr to proceed
 * - iter and calc carry and val while l1, l2, OR carry is valid
 * 
 * @author sunnypark
 *
 */
public class LC02_AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode node = head;
        
        int carry = 0;
        int val = 0;
        
        while (l1 != null || l2 != null || carry != 0) {
            int first = (l1 != null) ? l1.val : 0;
            int second = (l2 != null) ? l2.val : 0;
            
            // update val, carry, and next node
            val = (first + second + carry) % 10;
            carry = (first + second + carry) / 10;
            node.next = new ListNode(val);
            
            // pointer proceeding
            node = node.next;
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        
        return head.next;
    }

	class ListNode {
		ListNode next;
		int val;
		
		ListNode(int val) {
			this.val = val;
		}
	}
}
