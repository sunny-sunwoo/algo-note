package dynamicProgramming;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Q15.max rectangle area
 * 
 * List<Integer> list => 
 * 
 * 2,1,5,6,2,3,0
 *         ^
 *  
 * stack (potential max heights) => 2
 * 
 * when stack.peek() > curr
 *    tallestIdx: pop from stack
 *    h: val of tallestIdx
 *    w: cur - stack.peek() - 1
 *    keep max area
 *    
 * => time complexity: O(n)
 *    
 * @author sunnypark
 *
 */
public class Q15_MaxRectangleArea {
	public static int getMaxRectangleArea(List<Integer> input) {
		List<Integer> heights = new ArrayList<>(input); // add all inputs + 0
		heights.add(0); // to consider min height
		
		Deque<Integer> stack = new ArrayDeque<>(); // keep max heights
		int res = 0;
		
		for (int i = 0; i < heights.size(); i++) {
			int potentialHeight = heights.get(stack.peekLast());
			int newHeight = heights.get(i);
			while (!stack.isEmpty() &&  potentialHeight > newHeight) {
				int tallestIdx = stack.removeLast();
				int h = heights.get(tallestIdx);
				int w = stack.isEmpty() ? i : (i - stack.peekLast() - 1); // stack can be empty!!!
				res = Math.max(res, w * h);
			}
			stack.addLast(i);
		}
		
		return res;
	}
}
