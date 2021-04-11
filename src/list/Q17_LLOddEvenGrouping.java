package list;

/**
 * 
 * Q17. LLOddEvenGrouping
 * LL
 * head
 * evenHead = head.next
 * 
 * odd = head
 * even = evenHead
 * 
 * iter
 * while even(= odd.next) != null , even.next != null
 * 
 * odd.next = odd.next.next
 * even.next = even.next.next
 * 
 * odd = odd.next // 3
 * even = even.next // 4
 * 
 * odd.next = evenhead
 * rt head
 * 
 * 1 - 2 - 3 - 4 - 5 - null
 *         ^   ^
 * 
 * 1 - 3 - 5 - 2 - 4 - null
 * @author sunnypark
 *
 */
public class Q17_LLOddEvenGrouping {

}
