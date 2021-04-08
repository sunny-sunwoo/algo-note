package practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * Q. Infix -> PostFix
 * e.g. (A-(B/C))*((A/D)-E) -> ABC/-AD/E-*
 *                              
 * [approach] stack
 *  iter thru string chars & build sb
 *  - if closing ) ? pop from stack, and append
 *  - it not sign? append to sb 
 *  - sign? push to stack
 *  return sb.toString
 *
 * @author sunnypark
 *
 */
public class PostFixConversion {
	private static final Set<Character> SIGNS = Set.of('+', '-', '*', '/');
	private static final char CLOSING_PAREN = ')';
	private static final char OPEN_PAREN = '(';
	public static String infixToPostfix(String input) {
		Deque<String> stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		// iter
		for (int i = 0; i < input.length(); i++) {
			char curr = input.charAt(i);
			
			if (curr == OPEN_PAREN) {
				continue;
			}

			if (curr == CLOSING_PAREN) {
				sb.append(stack.removeLast());
				continue;
			}
			
			if (!SIGNS.contains(curr)) {
				sb.append(curr);
				continue;
			}
			
			if (SIGNS.contains(curr)) {
				stack.addLast(String.valueOf(curr));
				continue;
			}
			
			throw new IllegalArgumentException("invalid input");
		}
		
		while (!stack.isEmpty()) {
			sb.append(stack.removeLast());
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(infixToPostfix("(A-(B/C))*((A/D)-E)")); // ABC/-AD/E-*
	}
}
